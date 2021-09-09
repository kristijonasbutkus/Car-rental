package sda.cars.carrental.printout.profile;

import ch.qos.logback.classic.Level;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DocumentProfile implements IDocumentProfile {

    private Boolean debugOption   = false;

    protected Float  totalCount   = 0f;
    protected String businessName = "";
    protected String addressLine1 = "";
    protected String addressLine2 = "";
    protected String addressLine3 = "";
    protected String businessCompID = "";

    protected String invoiceNumber = "";
    protected String contractNumber = "";

    protected String expenseAmount = "0";
    protected String expensePeriod  = "";
    protected String serviceDescription = "";

    public static final String ACCOUNT_NR = "LT397300010134605284";

    public DocumentProfile() {
    }

    @Override
    public String toString() {

        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append( this.getBusinessName() )
                .append( "\n" )
                .append( this.getAddressLine1() )
                .append( "\n" )
                .append( this.getAddressLine2() )
                .append( "\n" )
                .append( this.getAddressLine3() )
                .append( "\n" )
                .append( this.getBusinessCompID() )
                .append( "\n" )
                .append( this.getInvoiceNumber() )
                .append( "\n" )
                .append( this.getContractNumber() )
                .append( "\n" )
                .append( this.getTotalCount() )
                .append( "\n" )
                .append( this.getExpensePeriod() )
                .append( "\n" )
                .append( this.getExpenseAmount() )
                .append( "\n" )
                .append( this.getServiceDescription() );

        return stringBuilder.toString();
    };

    public String getExpenseAmount() {
        return expenseAmount;
    }

    public DocumentProfile setExpenseAmount(String expenseAmount) {
        this.expenseAmount = expenseAmount;
        return this;
    }

    public String getBusinessName() {
        return businessName;
    }

    public DocumentProfile setBusinessName(String businessName) {
        this.businessName = businessName;
        return this;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public DocumentProfile setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public DocumentProfile setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public DocumentProfile setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
        return this;
    }

    public String getBusinessCompID() {
        return businessCompID;
    }

    public DocumentProfile setBusinessCompID(String businessCompID) {
        this.businessCompID = businessCompID;
        return this;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public DocumentProfile setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        return this;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public DocumentProfile setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
        return this;
    }

    public String getExpensePeriod() {
        return expensePeriod;
    }

    public DocumentProfile setExpensePeriod(String expensePeriod) {
        this.expensePeriod = expensePeriod;
        return this;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public DocumentProfile setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
        return this;
    }

    @Override
    public Boolean isDebugOn() {
        return this.debugOption;
    }

    public DocumentProfile setDebugOption(boolean setOption) {

        this.debugOption = setOption;
        new ChangeLogLevel().toLevel( Level.INFO.toString() );

        if ( debugOption ) {
            new ChangeLogLevel().toLevel( Level.DEBUG.toString() );
        }
        return this;
    }

    public Float getTotalCount() {
        return totalCount;
    }

    public DocumentProfile setTotalCount(Float totalHours) {
        this.totalCount = totalHours;
        return this;
    }

}
