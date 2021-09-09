package sda.cars.carrental.printout.cli;

import lombok.extern.slf4j.Slf4j;
import org.javamoney.moneta.Money;
import sda.cars.carrental.printout.profile.DocumentProfile;
import sda.cars.carrental.printout.services.PDFGeneratorServiceImpl;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Slf4j
public abstract class CliCommandLineGenerator implements ICommandLineGenerator {

    protected final Map<String, Object> paramsFF = new HashMap<String, Object>();
    protected LinkedList<DocumentProfile> documentProfileList = new LinkedList<>();
    protected PDFGeneratorServiceImpl pdfGeneratorServiceFF = null;
    protected DocumentType documentType;

    protected CliCommandLineGenerator() {
    }

    public static Money amountAsMoney(String amount) {

        final CurrencyUnit euro = Monetary.getCurrency("EUR");

        System.out.println("Amount to parse: " + amount);
        return Money.of(Float.parseFloat(amount), euro);
    }

    public LinkedList<DocumentProfile> getDocuments() {
        return documentProfileList;
    }

    public void finalize(final DocumentProfile documentProfile) throws Throwable {

        final Path pathToResultFile = Paths.get(documentProfile.getInvoiceNumber() + ".pdf");

        if (!Files.isReadable(pathToResultFile)) {
            throw new FileNotFoundException("PDF file could not be produced. Check error logs.");

        } else {
            log.info(String.format("Finished. Result in file: '%s' ",
                    documentProfile.getInvoiceNumber() + ".pdf"));

        }

        log.debug(documentProfile.getInvoiceNumber() + " is readable: " +
                Files.isReadable(pathToResultFile));
    }

    @Override
    public DocumentType getDocumentType() {
        return this.documentType;
    }

}

