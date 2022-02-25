package model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Employee extends Person {
    private final static AtomicInteger count = new AtomicInteger(0);
    private final int id;
    private String title;
    private LocalDate hireDate;

    public Employee(String address, String city, String region, String postalCode, String country, String firstName, String lastName, LocalDate birthDate, String phone, String email, String title, LocalDate hireDate) {
        super(address, city, region, postalCode, country, firstName, lastName, birthDate, phone, email);
        this.title = title;
        this.hireDate = hireDate;
        id = count.incrementAndGet();
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public static boolean checkIfAdult(LocalDate birthDate, LocalDate hireDate) {
        Period agePeriod = Period.between(birthDate, hireDate);
        int age = agePeriod.getYears();
        return age >= 18;
    }

    @Override
    public String toString() {
        return String.format("%-5d%-15s%-20s", id, title, hireDate) + super.toString();
    }

    @Override
    public String toCsv() {
        return id + ";" + getFirstName() + ";" + getLastName() + ";" + title + ";" + hireDate + ";" +
                getBirthDate() + ";" + getPhone() + ";" + getEmail() + ";" + getAddress() + ";" +
                getCity() + ";" + getPostalCode() + ";" + getRegion() + ";" + getCountry();
    }
}
