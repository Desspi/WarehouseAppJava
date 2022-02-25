package model;

public class Cart {
    private String products = "";
    private double totalValue;

    public String getProducts() {
        return products;
    }

    public void setProducts(String newProduct, int quantity) {
        products = products + newProduct + " " + quantity + "szt, ";
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double unitPrice, int quantity) {
        totalValue+= (unitPrice * quantity);
    }
}


