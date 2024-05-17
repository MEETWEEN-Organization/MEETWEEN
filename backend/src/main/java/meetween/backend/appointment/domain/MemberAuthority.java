package meetween.backend.appointment.domain;

public enum MemberAuthority {
    NORMAL, ADMIN;

    public static MemberAuthority updateAuthority(MemberAuthority memberAuthority) {
        if (memberAuthority == ADMIN) {
            return NORMAL;
        }
        return ADMIN;
    }
}
