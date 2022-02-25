package model;

import model.abstracts.ContactInfo;

import java.util.concurrent.atomic.AtomicInteger;

public class Company extends ContactInfo {

    private String companyName;
    private final static AtomicInteger count = new AtomicInteger(0);
    private final int id;

    public Company(String address, String city, String region, String postalCode, String country, String contactName, String phone, String email, String companyName) {
        super(address, city, region, postalCode, country, contactName, phone, email);
        this.companyName = companyName;
        id = count.incrementAndGet();
    }

    public Company(int id, String address, String city, String region, String postalCode, String country, String contactName, String phone, String email, String companyName) {
        super(address, city, region, postalCode, country, contactName, phone, email);
        this.companyName = companyName;
        this.id = id;
        count.incrementAndGet();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getId() {
        return id;
    }

    public String getCompanyAdress() {
        return getAddress() + " " + getCity() + ", " + getPostalCode() + " -- " + getCountry();
    }


    @Override
    public String toString() {
        return String.format("%-5d%-20s%-30s%-20s%-30s%-20s%-20s%-20s%-20s%-20s"
                , id, companyName, getContactName(), getPhone(), getEmail(), getAddress(), getCity(),
                getRegion(), getPostalCode(), getCountry());
    }


    @Override
    public String toCsv() {
        return id + ";" + companyName + ";" + getContactName() + ";" + getPhone() + ";" + getEmail() + ";" +
                getAddress() + ";" + getCity() + ";" + getRegion() + ";" + getPostalCode() + ";" + getCountry();
    }
}
