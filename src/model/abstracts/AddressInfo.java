package model.abstracts;

import java.io.Serializable;

public abstract class AddressInfo implements Serializable {
    private String address;
    private String city;
    private String region;
    private String postalCode;
    private String country;

    public AddressInfo(String address, String city, String region, String postalCode, String country) {
        this.address = address;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
    }

    public AddressInfo() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public abstract String toCsv();
}
