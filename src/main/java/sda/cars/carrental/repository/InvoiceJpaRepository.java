package sda.cars.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.cars.carrental.entity.Invoice;

public interface InvoiceJpaRepository extends JpaRepository<Invoice, Long> {

}
