package sda.cars.carrental.printout.cli;

import com.ibm.icu.text.RuleBasedNumberFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.ULocale;
import org.springframework.stereotype.Component;
import sda.cars.carrental.entity.Invoice;
import sda.cars.carrental.printout.csvio.CsvWriter;
import sda.cars.carrental.printout.csvio.CvsExtractor;
import sda.cars.carrental.printout.profile.DocumentProfile;
import sda.cars.carrental.printout.services.PDFGeneratorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.javamoney.moneta.format.CurrencyStyle;
import org.springframework.util.StringUtils;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.NumberValue;
import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Locale;

@Slf4j
@Component
public class CliOptionsCustomGenerator extends CliCommandLineGenerator implements ICommandLineGenerator {

    final protected CurrencyUnit euro = Monetary.getCurrency("EUR");

    final protected ULocale enLocale = new ULocale("en_EN");  // english
    final protected ULocale ltLocale = new ULocale("lt_LT");  // lithuanian

    public CliOptionsCustomGenerator(final String... args) {

        try {

            getDocuments().addAll(new CvsExtractor().readCSVDocumentToProfile(DocumentType.INVOICE));

        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        }

    }

    public synchronized void generate(final Invoice invoice) throws Exception {

        try {
        final CsvWriter csvWriter = new CsvWriter(ICommandLineGenerator.INVOICE_CSV, ';',
                StandardCharsets.UTF_8);

        csvWriter.writeComment("Write record: " + invoice.getId() );
        csvWriter.writeRecord( invoice.asRecord(), true);
        csvWriter.flush();
        csvWriter.close();

        this.generate();

        } catch (IOException ioException) {

            ioException.printStackTrace();
            log.error( ioException.getMessage() );
        }
    }

    /* Split by generator */
    public void generate() throws Exception {

        final String businessManager = "Busines Manager: N.A.2";

        log.info(String.format("Documents to generate: %d of type %s", getDocuments().size(),
                getDocumentType()));

        // Iterate
        for (DocumentProfile documentProfile : getDocuments()) {

            final Integer BDAYS_TO_ADD = 4;

            MonetaryAmount amountAsMoney = amountAsMoney(documentProfile.getExpenseAmount());
            NumberValue numberValue = amountAsMoney.getNumber();

            MonetaryAmountFormat eurFormat = MonetaryFormats.getAmountFormat(
                    AmountFormatQueryBuilder.of(Locale.GERMANY)
                            .set(CurrencyStyle.SYMBOL)
                            .set("pattern", "#,##0.00### ¤")
                            .build());

            final SimpleDateFormat enFormat = new SimpleDateFormat("dd", enLocale);
            final SimpleDateFormat yyFormat = new SimpleDateFormat("yyyy", enLocale);
            final SimpleDateFormat mnFormat = new SimpleDateFormat("MMMM", enLocale);
            final SimpleDateFormat ltFormat = new SimpleDateFormat("yyyy MMMM d", ltLocale);

            System.out.println("Generating expenses for " + DocumentProfile.ACCOUNT_NR);

            RuleBasedNumberFormat formatter = new RuleBasedNumberFormat(ltLocale, RuleBasedNumberFormat.SPELLOUT);
            String euros = StringUtils.capitalize(
                    formatter.format(numberValue.getAmountFractionNumerator()));
            String cent = formatter.format(numberValue.getAmountFractionDenominator());

            final String amountInWords = (String.format("%s %s %s %s", euros,
                    amountAsMoney.getCurrency().getCurrencyCode(), cent, "ct."));

            log.info("Amount to be expensed: " + amountInWords);

            Calendar calendar = this.addBusinessDay(Calendar.getInstance(), BDAYS_TO_ADD);
            // calendar.add(Calendar.MONTH, -1);

            paramsFF.put("invoiceNr", documentProfile.getInvoiceNumber());
            paramsFF.put("hoursWorked", documentProfile.getTotalCount());

            paramsFF.put("serviceContractNr", documentProfile.getContractNumber());
            paramsFF.put("forExpensePeriod", documentProfile.getExpensePeriod());
            paramsFF.put("serviceDescription", documentProfile.getServiceDescription());

            paramsFF.put("businessName", documentProfile.getBusinessName());
            paramsFF.put("businessCompanyID", documentProfile.getBusinessCompID());
            paramsFF.put("businessManager", org.apache.commons.codec.binary.StringUtils.newStringUtf8(
                    org.apache.commons.codec.binary.StringUtils.getBytesUtf8(businessManager)));

            paramsFF.put("addressLine1", documentProfile.getAddressLine1());
            paramsFF.put("addressLine2", documentProfile.getAddressLine2());
            paramsFF.put("addressLine3", documentProfile.getAddressLine3());

            paramsFF.put("amountWords", amountInWords);
            paramsFF.put("amountSum", eurFormat.format(amountAsMoney));
            paramsFF.put("accountNr", DocumentProfile.ACCOUNT_NR);

            paramsFF.put("monthInWordsEN",
                    String.format("%s %s, %s",
                            mnFormat.format(calendar.getTime()),
                            dateAsOrdinal(Integer.parseInt(enFormat.format(calendar.getTime()))),
                            yyFormat.format(calendar.getTime())));

            paramsFF.put("monthInWordsLT", ltFormat.format(calendar.getTime()));

            super.pdfGeneratorServiceFF = new PDFGeneratorServiceImpl();

            try {
                byte[] pdfContentFF = pdfGeneratorServiceFF.generatePDF("voucher/opes_novant_customer", paramsFF);
                OutputStream outFF = new FileOutputStream(String.format("%s.pdf", documentProfile.getInvoiceNumber()));
                outFF.write(pdfContentFF);
                outFF.close();
            } catch (Exception e) {
                log.error(e.getMessage());
            }

            try {
                super.finalize(documentProfile);
            } catch (Throwable throwable) {

                log.error(throwable.getMessage());
            }
        }
    }

    @Override
    public DocumentType getDocumentType() {
        return this.documentType;
    }

    @Override
    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    private Calendar addBusinessDay(final Calendar cal, final Integer numBusinessDays) {
        if (cal == null || numBusinessDays == null || numBusinessDays.intValue() == 0) {
            return cal;
        }
        final int numDays = Math.abs(numBusinessDays.intValue());
        final int dateAddition = numBusinessDays.intValue() < 0 ? -1 : 1; //if numBusinessDays is negative
        int businessDayCount = 0;
        while (businessDayCount < numDays) {
            cal.add(Calendar.DATE, dateAddition);

            //check weekend
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                continue;//adds another day
            }

            businessDayCount++;
        }
        return cal;
    }

    private String dateAsOrdinal(int num) {
        String[] suffix = {"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th"};
        int m = num % 100;
        return num + suffix[(m > 3 && m < 21) ? 0 : (m % 10)];
    }

}