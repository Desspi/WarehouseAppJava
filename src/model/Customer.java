package model;

import java.util.concurrent.atomic.AtomicInteger;

public class Customer extends Company{

    public Customer(String address, String city, String region, String postalCode, String country, String contactName, String phone, String email, String companyName) {
        super(address, city, region, postalCode, country, contactName, phone, email, companyName);
    }

    public Customer(int id, String address, String city, String region, String postalCode, String country, String contactName, String phone, String email, String companyName) {
        super(id, address, city, region, postalCode, country, contactName, phone, email, companyName);
    }

    @Override
    public String toCsv() {
        return super.toCsv();
    }

}
