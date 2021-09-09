package sda.cars.carrental;

/* REF:
 * Spring Boot testing with JUnit5
 * https://developer.okta.com/blog/2019/03/28/test-java-spring-boot-junit5
 *
 * The report about tests is to be found in
 * ../SpringMVC/target/site/surefire-report.html
 *
 * mvn surefire-report:report
 *
 * */

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.query.Param;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import sda.cars.carrental.controller.CarsController;
import sda.cars.carrental.entity.Cars;
import sda.cars.carrental.repository.CarsJpaRepository;
import sda.cars.carrental.service.CarService;

import javax.transaction.Transactional;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebAppConfiguration("classpath:resources")
@EnableWebMvc
@ExtendWith(MockitoExtension.class)
@ComponentScan("sda.cars.carrental")
@Slf4j
public class CarControllerTest {

    protected MockMvc mockMvc;
    @Autowired
    MockHttpServletRequest mockHttpServletRequest;
    @Value("localhost")
    String serverName;
    @Value("8080")
    Integer serverPort;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @InjectMocks
    private CarsController carsController;
    @Autowired
    private CarsJpaRepository carsJpaRepository;
    @Autowired
    private CarService carServiceImpl;

    @BeforeTestMethod
    public void setup() {

        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        try {

            assertNotNull(this.carsJpaRepository);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @ParameterizedTest
    @Tag("selects")
    @DisplayName("Retrieve all cars from end-point")
    @Rollback(false)
    @Transactional
    @ValueSource(longs = { 1,2 })
    public void testFindID(@Param("id") Long carId) {

        final ArrayList<Cars> allCars = new ArrayList<>(carServiceImpl.findAll());
        Assertions.assertFalse(allCars.isEmpty());

        log.info(allCars.size() + " cars were found!");
        for (Cars car : allCars) {
            log.info(car.toString());
        }

        Cars carFound = carServiceImpl.findById(carId);
        assertNotNull(carFound);

        if (carId == 1L) {
            assertEquals(carFound.getBrand(), "VW");
            assertEquals(carFound.getModel(), "Passat");
        } else {
            assertEquals(carFound.getBrand(), "VW");
            assertTrue(carFound.getModel().startsWith("Golf"));
        }
    }
}