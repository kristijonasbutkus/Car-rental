package sda.cars.carrental;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Example;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import sda.cars.carrental.controller.PrintOutController;
import sda.cars.carrental.entity.Invoice;
import sda.cars.carrental.printout.cli.ICommandLineGenerator;
import sda.cars.carrental.printout.csvio.CsvWriter;
import sda.cars.carrental.printout.csvio.CsvWriter.*;
import sda.cars.carrental.repository.InvoiceJpaRepository;
import sda.cars.carrental.service.InvoiceService;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebAppConfiguration("classpath:resources")
@EnableWebMvc
@ExtendWith(MockitoExtension.class)
@ComponentScan("sda.cars.carrental")
@Slf4j
@SpringBootTest

public class CarInvoiceFileWriteTest {

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
    private PrintOutController printOutController;

    @Autowired
    private InvoiceJpaRepository invoiceJpaRepository;

    @Autowired
    private InvoiceService invoiceService;

    // Don't autowire
    private CsvWriter csvWriter;

    @BeforeTestMethod
    public void setup() {

        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        try {

            assertNotNull(this.invoiceJpaRepository);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Test
    public void noSuchInvoiceFoundTest() {

        assertNotNull(this.invoiceJpaRepository);

        assertThrows( java.util.NoSuchElementException.class, () -> {

            Invoice invoice = new Invoice();
            invoice.setId( 100L );

            invoice.setInvoiceNumber("86595433");
            invoiceService.save( invoice );

            Optional<Invoice> findInvoice = invoiceJpaRepository.findOne(Example.of( invoice ));

            assertNotNull( findInvoice );
            assertNotNull( findInvoice.get() );

            log.info( findInvoice.toString() );

            }
        );
    }

    @Test
    public void addInvoiceForPrintOutTest() {

        assertNotNull(this.invoiceJpaRepository);
        assertDoesNotThrow( () -> {

                    Invoice invoice = new Invoice();
                    invoice.setId( 1L );

                    Optional<Invoice> findInvoice =
                            invoiceJpaRepository.findOne(Example.of( invoice ));

                    assertNotNull( findInvoice );
                    assertNotNull( findInvoice.get() );

                    log.info( findInvoice.toString() );

                    csvWriter = new CsvWriter(ICommandLineGenerator.INVOICE_CSV, ';',
                            StandardCharsets.UTF_8);

                    csvWriter.writeComment("Prepare for write: test");
                    csvWriter.writeRecord(findInvoice.get().asRecord(), true);
                    csvWriter.flush();
                    csvWriter.close();

                }
        );
    }

}
