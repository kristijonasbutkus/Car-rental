package sda.cars.carrental.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.StringJoiner;

@Entity
@Data
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id", nullable = false)

    private Long id;
    private Integer totalCount;
    private String businessName;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String businessCompID;
    private String invoiceNumber;
    private String contractNumber;
    private String serviceDescription;
    private BigDecimal expenseAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getBusinessCompID() {
        return businessCompID;
    }

    public void setBusinessCompID(String businessCompID) {
        this.businessCompID = businessCompID;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public BigDecimal getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(BigDecimal expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public String[] asRecord() {

        return
        new StringJoiner(";" , "", "")

                .add("" + totalCount)
                .add("" + businessName )
                .add("" + addressLine1 )
                .add("" + addressLine2 )
                .add("" + addressLine3 )
                .add("" + businessCompID )
                .add("" + invoiceNumber )
                .add("" + contractNumber )
                .add("" + serviceDescription )
                .add("" + expenseAmount).toString().split(";");

    }

}
