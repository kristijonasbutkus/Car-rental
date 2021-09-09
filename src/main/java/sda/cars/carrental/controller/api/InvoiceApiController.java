package sda.cars.carrental.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sda.cars.carrental.entity.Invoice;
import sda.cars.carrental.printout.InvoicePrintService;
import sda.cars.carrental.printout.cli.ICommandLineGenerator;
import sda.cars.carrental.printout.csvio.CsvWriter;
import sda.cars.carrental.service.InvoiceService;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/invoice/api/")
@Slf4j
public class InvoiceApiController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    InvoicePrintService invoicePrintOutService;

    @Autowired
    ICommandLineGenerator commandLineGenerator;

    @GetMapping("/invoice-list")
    public List<Invoice> showEmployeeList(Invoice invoice) {
        List<Invoice> invoiceList = invoiceService.findAll();
        return invoiceList;
    }

    @GetMapping("/invoice/{id}")
    public Invoice getInvoice(@PathVariable Long id) {
        return invoiceService.findById(id);
    }


    @PutMapping("/invoice/{id}")
    public Invoice updateInvoice(@PathVariable(value = "id") Long id, @Valid @RequestBody Invoice invoiceDetails) {
        invoiceService.save(invoiceDetails);
        return invoiceService.findById(id);
    }

    @GetMapping("/invoice/print/{id}")
    public Invoice printInvoice(@PathVariable(value = "id") Long id)  {

        Invoice invoiceDetails = new Invoice();

        try {

            invoiceDetails= invoiceService.findById(id);
            invoiceService.save( invoiceDetails );
            log.info( invoiceDetails.toString() );

        } catch (Exception exception) {

            exception.printStackTrace();
            log.error( exception.getMessage() );
        }

        try {

            invoicePrintOutService.printOutQR(invoiceDetails);
            commandLineGenerator.generate( invoiceDetails );

            log.info( "Your invoice " + invoiceDetails.getInvoiceNumber() + " is ready.");

        } catch ( Exception e ) {

            log.error( e.getMessage() );
            e.printStackTrace();
        }
        return invoiceDetails;
    }

    @DeleteMapping ("/invoice/{id}")
    public Boolean deleteInvoice(@PathVariable Long id){
        try {
            invoiceService.deleteById(id);
            return true;
        }
        catch (Exception e){
            log.error(e.getMessage());
            return false;
        }

    }

    @PostMapping("/invoice/new")
    public Invoice newInvoice (@RequestBody Invoice newInvoice) {
        invoiceService.save(newInvoice);
        return  invoiceService.findById(newInvoice.getId());
    }
}
