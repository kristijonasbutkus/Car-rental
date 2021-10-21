package sda.cars.carrental.printout;

import sda.cars.carrental.entity.Invoice;

public interface InvoicePrintService {

    public Boolean printOutQR( final Invoice invoice );
}
