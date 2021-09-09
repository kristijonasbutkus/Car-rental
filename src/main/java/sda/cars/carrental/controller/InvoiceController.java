package sda.cars.carrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sda.cars.carrental.service.InvoiceService;

@Controller
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/invoice")
    public String ShowInvoiceList(Model model) {
        model.addAttribute("invoiceList", invoiceService.findAll());
        return "invoice/invoice";
    }


    @GetMapping("/invoice/{id}")
    public String getInvoice (Model model, @PathVariable Long id){
        model.addAttribute ("invoice", invoiceService.findById(id));
        return "invoices/invoice-view";
    };


//    @PostMapping("/invoice/new")
//    public String newInvoice (@RequestBody Invoice newInvoice) {
//        invoiceService.save(newInvoice);
//        return  invoiceService.findById(newInvoice.getId());
//    }

    @DeleteMapping("/invoice/delete/{id}")
    public String deleteInvoice(@PathVariable("id") Long id) {
        invoiceService.deleteById(id);
        return "invoices/invoice-list";
    }

}
