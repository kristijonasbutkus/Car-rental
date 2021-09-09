package sda.cars.carrental.printout.cli;

import sda.cars.carrental.entity.Invoice;
import sda.cars.carrental.printout.profile.DocumentProfile;

public interface ICommandLineGenerator {

    String INVOICE_CSV = "./csvdata/invoice.csv";

    void generate() throws Exception;
    void generate(Invoice invoice) throws Exception;
    DocumentType getDocumentType();

    void finalize(final DocumentProfile documentProfile) throws Throwable;

    abstract void setDocumentType(DocumentType type);
    enum DocumentType {INVOICE, EXPENSE, UNDEFINED}

}
