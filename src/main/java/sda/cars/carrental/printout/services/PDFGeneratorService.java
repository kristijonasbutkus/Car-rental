package sda.cars.carrental.printout.services;

import java.util.Map;

public interface PDFGeneratorService {

    public static final String XML_DATA_ATTR = "XML_DATA";

    byte[] generatePDF(String templateName, Map<String, Object> params) throws PDFGeneratorServiceException;

    public static class PDFGeneratorServiceException extends Exception {
        public PDFGeneratorServiceException() {
        }

        public PDFGeneratorServiceException(String message) {
            super(message);
        }

        public PDFGeneratorServiceException(String message, Throwable cause) {
            super(message, cause);
        }

        public PDFGeneratorServiceException(Throwable cause) {
            super(cause);
        }
    }
}
