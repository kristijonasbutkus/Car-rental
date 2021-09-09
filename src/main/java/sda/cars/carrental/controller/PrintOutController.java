package sda.cars.carrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sda.cars.carrental.entity.Invoice;
import sda.cars.carrental.service.InvoiceService;

@RestController
public class PrintOutController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/invoice/print/{id}")
    public Invoice printInvoice (@PathVariable Long id){
        return invoiceService.findById(id);

    };

}

