package sda.cars.carrental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.cars.carrental.entity.Invoice;
import sda.cars.carrental.error.CustomException;
import sda.cars.carrental.repository.InvoiceJpaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceJpaRepository invoiceJpaRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceJpaRepository invoiceJpaRepository) {
        this.invoiceJpaRepository = invoiceJpaRepository;
    }

    @Override
    public List<Invoice> findAll() {
        return invoiceJpaRepository.findAll();
    }

    @Override
    public Invoice findById(long theId) {
        Optional<Invoice> result = invoiceJpaRepository.findById(theId);
        Invoice theInvoice;
        if (result.isPresent()){
            theInvoice = result.get();
        } else {
            throw new CustomException("Invoice with ID=" + theId + " Not found");
        }
        return theInvoice;
    }

    @Override
    @Transactional
    public void save(Invoice theInvoice) {
        invoiceJpaRepository.save(theInvoice);
    }

    @Override
    @Transactional
    public void deleteById(long theId) {
        invoiceJpaRepository.deleteById(theId);
    }
}
