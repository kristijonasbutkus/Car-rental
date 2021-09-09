package sda.cars.carrental.printout.csvio;

import sda.cars.carrental.printout.cli.ICommandLineGenerator;
import sda.cars.carrental.printout.profile.DocumentProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class CvsExtractor {

    private final Logger log = LoggerFactory.getLogger(CvsExtractor.class);

    public CvsExtractor() {
    }

    public List<DocumentProfile> readCSVDocumentToProfile(final ICommandLineGenerator.DocumentType typed)
            throws FileNotFoundException {

        final List<DocumentProfile> allDocuments = new LinkedList<>();

        String[] reader_header = new String[]{};
        CsvReader csvReader = null;

        switch (typed) {

            case INVOICE:

                reader_header = "setTotalCount,setBusinessName,setAddressLine1,setAddressLine2,setAddressLine3,setBusinessCompID,setInvoiceNumber,setContractNumber,setServiceDescription,setExpenseAmount"
                        .split(",");

                csvReader = new CsvReader(ICommandLineGenerator.INVOICE_CSV,
                        ';', StandardCharsets.UTF_8);
                break;
        }

        try {

            csvReader.setSkipEmptyRecords(true);
            csvReader.setUseComments(true);
            csvReader.setHeaders(reader_header);

            while (csvReader.readRecord()) {

                final DocumentProfile documentProfile = new DocumentProfile();

                log.debug("Columns present", csvReader.getColumnCount() > 0);
                log.debug("Headers present", csvReader.getHeaderCount() > 0);

                Class<?> aClass = null;
                try {
                    aClass = Class.forName("sda.cars.carrental.printout.profile.DocumentProfile");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < reader_header.length; i++) {

                    Method method = null;
                    try {
                        method = aClass.getDeclaredMethod(reader_header[i],
                                (i > 0) ? String.class : Float.class);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }

                    log.debug(reader_header[i] + "::" + csvReader.get(i));

                    try {
                        method.invoke(documentProfile, (i > 0) ? csvReader.get(i) :
                                Float.parseFloat(csvReader.get(i)));

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }

                allDocuments.add(documentProfile);
            } // end while

        } catch (IOException e) {
            e.printStackTrace();
        }

        return allDocuments;
    }
}
