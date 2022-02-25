package model;

import model.abstracts.AddressInfo;

import java.time.LocalDate;
import java.util.Date;

public abstract class Person extends AddressInfo {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String phone;
    private String email;


    public Person(String address, String city, String region, String postalCode, String country, String firstName, String lastName, LocalDate birthDate, String phone, String email) {
        super(address, city, region, postalCode, country);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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

    @Override
    public String toString() {
        return String.format("%-15s%-15s%-15s%-15s%-30s%-20s%-20s%-20s%-20s%-20s", firstName, lastName, birthDate, phone, email,
                getAddress(), getCity(), getPostalCode(),getRegion(), getCountry());
    }
}
