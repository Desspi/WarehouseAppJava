package model;

import exceptions.NotEnoughProductInStockException;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class Product implements Serializable {
    private static final AtomicInteger count = new AtomicInteger(0);
    private final int id;
    private String name;
    private Supplier supplier;
    private Category category;
    private double unitPrice;
    private int unitsInStock;

    public Product(String name, Supplier supplier, Category category, int unitsInStock, double unitPrice) {
        this.name = name;
        this.supplier = supplier;
        this.category = category;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        id = count.incrementAndGet();
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setUnitsInStock(int quantityToAdd) {
        int quantity = this.unitsInStock + quantityToAdd;
        if (quantity >= 0) {
            this.unitsInStock = quantity;
        } else {
            throw new NotEnoughProductInStockException("Nie ma tylu produktów w magazynie");
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public Category getCategory() {
        return category;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }


    @Override
    public String toString() {
        return String.format("%-5d%-20s%-20s%-25s%-15.2f%-25d", id, name,
                supplier.getCompanyName(), category.name(), unitPrice, unitsInStock);
    }

    public String toCsv() {
        return id + ";" + name + ";" + supplier.getId() + ";" + category.getId() + ";" +
                unitPrice + ";" + unitsInStock;
    }
}
