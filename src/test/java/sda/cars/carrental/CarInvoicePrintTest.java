package sda.cars.carrental;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import sda.cars.carrental.entity.Invoice;
import sda.cars.carrental.printout.InvoicePrintService;
import sda.cars.carrental.printout.cli.ICommandLineGenerator;
import sda.cars.carrental.printout.csvio.CsvReader;
import sda.cars.carrental.printout.profile.DocumentProfile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebAppConfiguration("classpath:resources")
@EnableWebMvc
@ExtendWith(MockitoExtension.class)
@ComponentScan("sda.cars.carrental")
@Slf4j
@SpringBootTest
public class CarInvoicePrintTest {

    @Autowired
    InvoicePrintService invoicePrintOutService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ICommandLineGenerator commandLineGenerator;

    @Test
    @DisplayName( "Test invoice QR code print")
    public void testInvoicePrints() {

        assertNotNull(invoicePrintOutService);
        assertNotNull(objectMapper);

        Invoice invoice = new Invoice();
        invoice.setId(1393L);
        invoice.setInvoiceNumber("95939843");
        invoice.setAddressLine1( "Laisvės al. 37");
        invoice.setAddressLine1( "Kaunas" );
        invoice.setExpenseAmount( new BigDecimal( 198.37 ) );

        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(invoice);
        } catch (JsonProcessingException e) {

            log.error(e.getMessage());
            e.printStackTrace();
        }

        System.out.println(invoice);
        System.out.println(jsonString);

        invoicePrintOutService.printOutQR(invoice);
    }

    @Test
    @DisplayName( "Read document profile test")
    void documentProfileTest() {

        assertNotNull(commandLineGenerator);
        final String[] invoice_format =
                "setTotalCount,setBusinessName,setAddressLine1,setAddressLine2,setAddressLine3,setBusinessCompID,setInvoiceNumber,setContractNumber".split(",");

        try {
            CsvReader csvReader = new CsvReader(ICommandLineGenerator.INVOICE_CSV,
                    ';', StandardCharsets.UTF_8);

            assertNotNull(csvReader);

            try {

                csvReader.setSkipEmptyRecords(true);
                csvReader.setUseComments(true);
                csvReader.setHeaders(invoice_format);

                assertTrue("Record present", csvReader.readRecord());
                assertTrue("Columns present", csvReader.getColumnCount() > 0);
                assertTrue("Headers present", csvReader.getHeaderCount() > 0);

                Class<?> aClass = null;
                try {
                    aClass = Class.forName("sda.cars.carrental.printout.profile.DocumentProfile");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                assertNotNull(aClass);
                System.out.println("Found class: " + aClass.getCanonicalName());

                final DocumentProfile documentProfile = new DocumentProfile();
                for (int i = 0; i < invoice_format.length; i++) {

                    assertNotNull(invoice_format[i]);
                    assertNotNull(csvReader.get(i));
                    Method method = null;

                    try {
                        method = aClass.getDeclaredMethod(invoice_format[i], (i > 0) ? String.class : Float.class);
                        System.out.println( "Select test method: " + method);

                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }

                    System.out.println(invoice_format[i] + "::" + csvReader.get(i));

                    assertNotNull(method);
                    assertNotNull(documentProfile);

                    try {
                        method.invoke(documentProfile, (i > 0) ? csvReader.get(i) : Float.parseFloat(csvReader.get(i)));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("Get Document: \n" + documentProfile);

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName( "Print PDF document from profile test")
    void pdfPrintOutTest() {

        assertNotNull( commandLineGenerator );

        try {
            commandLineGenerator.generate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

