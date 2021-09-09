package sda.cars.carrental.service;

import sda.cars.carrental.entity.Invoice;

import java.util.List;

public interface InvoiceService {

    List<Invoice> findAll();
    Invoice findById(long theId);
    void save(Invoice theInvoice);
    void deleteById(long theId);
}
