package meetween.backend.authentication.infrastructure.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import meetween.backend.authentication.domain.token.MemberToken;
import meetween.backend.authentication.domain.token.RefreshTokenRepository;
import meetween.backend.authentication.exception.InvalidTokenException;
import meetween.backend.authentication.exception.NoSuchRefreshTokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
    private final RefreshTokenRepository refreshTokenRepository;
    private final SecretKey key;
    private final long accessTokenExpireLength;
    private final long refreshTokenExpireLength;

    public JwtTokenProvider(@Value("${jwt.token.secret-key}") final String secretKey,
                            @Value("${jwt.access-token.expire-length}") final long accessTokenExpireLength,
                            @Value("${jwt.refresh-token.expire-length}") final long refreshTokenExpireLength,
                            final RefreshTokenRepository refreshTokenRepository) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpireLength = accessTokenExpireLength;
        this.refreshTokenExpireLength = refreshTokenExpireLength;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public MemberToken generateMemberToken(long memberId) {
        String accessToken = createAccessToken(memberId);
        String refreshToken = createRefreshToken(memberId);
        return new MemberToken(accessToken, refreshToken);
    }

    public String createAccessToken(long memberId) {
        return createToken(String.valueOf(memberId), refreshTokenExpireLength);
    }

    public String createRefreshToken(long memberId) {
        if(!refreshTokenRepository.existsById(memberId)) {
            String newRefreshToken = createToken(String.valueOf(memberId), refreshTokenExpireLength);
            refreshTokenRepository.save(memberId, newRefreshToken);
            return newRefreshToken;
        }
        return refreshTokenRepository.findById(memberId);
    }

    public String createToken(String payload, final long validityInMilliseconds) {
        Date now = new Date();
        Date validity = new Date(System.currentTimeMillis() + validityInMilliseconds);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setSubject(payload) // subject 는 payload 로 저장된다.
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getPayload(String token) {
        validateToken(token);

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public void validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            claims.getBody()
                    .getExpiration()
                    .before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidTokenException();
        }
    }

    public String generateRenewalAccessToken(String refreshToken) {
        validateToken(refreshToken);
        Long memberId = Long.valueOf(getPayload(refreshToken));

        if(!refreshTokenRepository.existsById(memberId)) {
            throw new InvalidTokenException();
        }

        return createAccessToken(memberId);
    }

    public void removeRefreshTokenByMemberId(final long memberId) {
        if(!refreshTokenRepository.existsById(memberId)) {
            throw new NoSuchRefreshTokenException();
        }
        refreshTokenRepository.existsById(memberId);
    }
}