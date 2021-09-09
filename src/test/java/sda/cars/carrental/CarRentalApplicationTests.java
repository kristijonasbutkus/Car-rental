package sda.cars.carrental;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;
import sda.cars.carrental.entity.Reservations;
import sda.cars.carrental.repository.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Slf4j
@ActiveProfiles("test")
class CarRentalApplicationTests {

    @Autowired
    private static SessionFactory sessionFactory;

    @Autowired
    private EmployeesJpaRepository employeeJpaRepository;

    @Autowired
    private UsersJpaRepository usersJpaRepository;

    @Autowired
    private CarsJpaRepository carsJpaRepository;

    @Autowired
    private BranchesJpaRepository branchesJpaRepository;

    @Autowired
    private InvoiceJpaRepository invoiceJpaRepository;

    @Autowired
    private ReservationsJpaRepository reservationJpaRepository;

    private UsersJpaRepository userJpaRepository;

    @BeforeAll
    public static void setup() {
        if (sessionFactory != null)
            System.out.println("SessionFactory created");
    }

    @AfterAll
    public static void tearDown() {
        if (sessionFactory != null) sessionFactory.close();
        System.out.println("SessionFactory destroyed");
    }

    @BeforeEach
    public void openSession() {
        if (sessionFactory != null)
            sessionFactory.openSession();
        System.out.println("Session created");
    }

    @AfterEach
    public void closeSession() {
        System.out.println("Session closed\n");
    }

    @Test
    void contextLoads() {
    }

    @Test
    void jpaEmployeesFindAll() {
        Assert.notNull(employeeJpaRepository, "JPA repo is null");
        Assert.notNull(employeeJpaRepository.findAll(), "JPA repo find is null");
        Assert.notEmpty(employeeJpaRepository.findAll(), "Not empty");
    }

    @Test
    void jpaUsersFindAll() {
        Assert.notNull(usersJpaRepository, "JPA repo is null");
        Assert.notNull(usersJpaRepository.findAll(), "JPA repo find is null");
        Assert.notEmpty(usersJpaRepository.findAll(), "Not empty");
    }

    @Test
    void jpaCarsFindAll() {
        Assert.notNull(carsJpaRepository, "JPA repo is null");
        Assert.notNull(carsJpaRepository.findAll(), "JPA repo find is null");
        Assert.notEmpty(carsJpaRepository.findAll(), "Not empty");
    }

    @Test
    void jpaInvoiceFindAll() {
        Assert.notNull(invoiceJpaRepository, "JPA repo is null");
        Assert.notNull(invoiceJpaRepository.findAll(), "JPA repo find is null");
        Assert.notEmpty(invoiceJpaRepository.findAll(), "Not empty");
    }

    @Test
    void jpaBranchesFindAll() {
        Assert.notNull(employeeJpaRepository, "JPA repo is null");
        Assert.notEmpty(branchesJpaRepository.findAll(), "Not empty");
    }

    @Test
    void jpaReservationFindAndList() {
        Assert.notEmpty(reservationJpaRepository.findAll(), "Not empty");
        Reservations reservations = new Reservations();
        reservations.setId(1L);
        List reservationList = reservationJpaRepository.findAll(Example.of(reservations));
        Assert.notEmpty(reservationList, "By example not found");
        reservationList.forEach(System.out::println);
    }

    @Test
    void negativeJpaReservationFind() {
        Assert.notEmpty(reservationJpaRepository.findAll(), "Not empty");
        Reservations reservations = new Reservations();
        reservations.setId(33L);
        assertThrows(IllegalArgumentException.class, () -> {
            Assert.notEmpty(reservationJpaRepository.findAll(Example.of(reservations)), "By example not found");
        });
    }

}
