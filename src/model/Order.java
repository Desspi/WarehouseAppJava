package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Order implements Serializable {
    private final static AtomicInteger count = new AtomicInteger(0);
    private final int id;
    private String orderedProducts;
    private double totalPrice;
    private Customer customer;
    private Employee employee;
    private Shipper shipper;
    private LocalDate orderDate;
    private LocalDate requiredDate;
    private LocalDate shippedDate;
    private double freight;
    private Company companyToDeliver;

    public Order(Customer customer, Employee employee, Shipper shipper, String orderedProducts, double totalPrice, LocalDate orderDate, int timeForDelivery, double freight, Company companyToDeliver) {
        this.customer = customer;
        this.employee = employee;
        this.shipper = shipper;
        this.orderedProducts = orderedProducts;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        setRequiredDate(timeForDelivery);
        this.freight = freight;
        this.companyToDeliver = companyToDeliver;
        id = count.incrementAndGet();
    }

    public Order(String orderedProducts, double totalPrice, Customer customer, Employee employee, Shipper shipper, LocalDate orderDate, LocalDate requiredDate, LocalDate shippedDate, double freight, Company companyToDeliver) {
        id = count.incrementAndGet();
        this.orderedProducts = orderedProducts;
        this.totalPrice = totalPrice;
        this.customer = customer;
        this.employee = employee;
        this.shipper = shipper;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.freight = freight;
        this.companyToDeliver = companyToDeliver;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(String orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Shipper getShipper() {
        return shipper;
    }

    public void setShipper(Shipper shipper) {
        this.shipper = shipper;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(int days) {
        this.requiredDate = orderDate.plusDays(days);
    }

    public LocalDate getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(LocalDate shippedDate) {
        this.shippedDate = shippedDate;
    }

    public double getFreight() {
        return freight;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public Company getCompanyToDeliver() {
        return companyToDeliver;
    }

    public void setCompanyToDeliver(Company companyToDeliver) {
        this.companyToDeliver = companyToDeliver;
    }

    @Override
    public String toString() {
        return String.format("%-5d%-30s%-25.2f%-25s%-25s%-25s%-25s%-25s%-25s%-25.2f%-30s", id,
                orderedProducts, totalPrice, customer.getCompanyName(), employee.getFullName(), shipper.getCompanyName(),
                orderDate, requiredDate, shippedDate, freight, companyToDeliver.getCompanyAdress());
    }

    public String toCsv() {
        return id + ";" + orderedProducts + ";" + totalPrice + ";" + customer.getId() + ";" +
                employee.getId() + ";" + shipper.getId() + ";" + orderDate + ";" + requiredDate + ";" +
                shippedDate + ";" + freight + ";" + companyToDeliver.getId();
    }
}
