package model.abstracts;

import model.abstracts.AddressInfo;

public abstract class ContactInfo extends AddressInfo {
    private String contactName;
    private String phone;
    private String email;

    public ContactInfo(String address, String city, String region, String postalCode, String country, String contactName, String phone, String email) {
        super(address, city, region, postalCode, country);
        this.contactName = contactName;
        this.phone = phone;
        this.email = email;
    }

    public ContactInfo() {
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
