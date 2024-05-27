package meetween.backend.appointment.application;

import jakarta.persistence.EntityManager;
import meetween.backend.appointment.domain.*;
import meetween.backend.member.domain.Member;
import meetween.backend.member.domain.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static meetween.backend.support.fixture.common.MemberFixtures.*;


@SpringBootTest
public class MultiThreadTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentUserService appointmentUserService;

    @Autowired
    private AppointmentUserRepository appointmentUserRepository;

    @Autowired
    private EntityManager entityManager;


    @BeforeEach
    public void before() {
        Appointment appointment = new Appointment("수현의 약속", 123456L, LocalDateTime.now().plusDays(1), 3L);
        Member member1 = 수현_유저();
        Member member2 = 주용_유저();
        AppointmentUser appointmentUser1 = new AppointmentUser(appointment, member1, MemberAuthority.ADMIN);
        AppointmentUser appointmentUser2 = new AppointmentUser(appointment, member2, MemberAuthority.ADMIN);

        appointmentRepository.save(appointment);
        memberRepository.save(member1);
        memberRepository.save(member2);
        appointmentUserRepository.save(appointmentUser1);
        appointmentUserRepository.save(appointmentUser2);
    }

    @Test
    void 동시성_테스트() throws InterruptedException {
        Logger logger = Logger.getLogger(MultiThreadTest.class.getName());
        logger.log(Level.INFO, "시작");

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(2);
        AtomicReference<Boolean> flag = new AtomicReference<>(false);

        executorService.execute(() -> {
            try {
                logger.log(Level.INFO, "첫 번째 요청 시작: member1 -> member2");
                appointmentUserService.updateAuthority(1L, 1L, 2L);
                logger.log(Level.INFO, "첫 번째 요청 완료: member1 -> member2");
            } catch (Exception e) {
                logger.log(Level.SEVERE, "첫 번째 요청 중 예외 발생", e);
                flag.set(true);
            } finally {
                latch.countDown();
            }
        });

        Thread.sleep(100);

        executorService.execute(() -> {
            try {
                logger.log(Level.INFO, "두 번째 요청 시작: member2 -> member1");
                appointmentUserService.updateAuthority(1L, 2L, 1L);
                logger.log(Level.INFO, "두 번째 요청 완료: member2 -> member1");
            } catch (Exception e) {
                logger.log(Level.SEVERE, "두 번째 요청 중 예외 발생", e);
                flag.set(true);
            } finally {
                latch.countDown();
            }
        });

        latch.await();

        Assertions.assertThat(flag.get()).isEqualTo(true);
    };

    @AfterEach
    void after() {
        appointmentUserRepository.deleteAll();
    }
}