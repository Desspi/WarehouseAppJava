package model;

import java.util.concurrent.atomic.AtomicInteger;

public class Supplier extends Company {
    private String homePage;

    public Supplier(String address, String city, String region, String postalCode, String country,
                    String contactName, String phone, String email, String companyName, String homePage) {
        super(address, city, region, postalCode, country, contactName, phone, email, companyName);
        this.homePage = homePage;
    }

    public Supplier(int id, String address, String city, String region, String postalCode, String country, String contactName, String phone, String email, String companyName, String homePage) {
        super(id, address, city, region, postalCode, country, contactName, phone, email, companyName);
        this.homePage = homePage;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("%-30s", homePage);
    }

    @Override
    public String toCsv() {
        return super.toCsv() + ";" + homePage;
    }
}
