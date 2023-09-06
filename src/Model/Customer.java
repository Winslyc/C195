package Model;

public class Customer {
    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdateBy;

    public int getCustomerId() {
        return customerId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public String getAddress() {
        return address;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getCreateDate() {
        return createDate;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public String getLastUpdate() {
        return lastUpdate;
    }
    public String getLastUpdateBy() {
        return lastUpdateBy;
    }
}
