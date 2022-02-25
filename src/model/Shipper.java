package model;

public class Shipper extends Company{


    public Shipper(String address, String city, String region, String postalCode, String country, String contactName, String phone, String email, String companyName) {
        super(address, city, region, postalCode, country, contactName, phone, email, companyName);
    }

    public Shipper(int id, String address, String city, String region, String postalCode, String country, String contactName, String phone, String email, String companyName) {
        super(id, address, city, region, postalCode, country, contactName, phone, email, companyName);
    }

    @Override
    public String toCsv() {
        return super.toCsv();
    }
}
