package managers;

import exceptions.AgeViolationException;
import exceptions.NotEnoughProductInStockException;
import model.*;

import java.io.Serializable;
import java.util.*;

public class WarehouseManager implements Serializable {
    private final Map<Integer, Company> companies = new HashMap<>();
    private final Map<Integer, Employee> employees = new HashMap<>();
    private final Map<Integer, Order> orders = new HashMap<>();
    private final Map<Integer, Product> products = new HashMap<>();

    public List<Product> getProducts() {
        return new ArrayList<>(products.values());
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders.values());
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees.values());
    }

    public Map<Integer, Company> getCompanies() {
        return companies;
    }

    public List<Supplier> getSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        for (Company value : companies.values()) {
            if (value instanceof Supplier)
                suppliers.add((Supplier)value);
        }
        return suppliers;
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        for (Company value : companies.values()) {
            if (value instanceof Customer)
                customers.add((Customer)value);
        }
        return customers;
    }

    public List<Shipper> getShippers() {
        List<Shipper> shippers = new ArrayList<>();
        for (Company value : companies.values()) {
            if (value instanceof Shipper)
                shippers.add((Shipper)value);
        }
        return shippers;
    }

    public void addCompany(Company company) {
        companies.put(company.getId(), company);

    }

    public boolean removeCompany(Company company) {
        if (companies.containsValue(company)) {
            companies.remove(company.getId());
            return true;
        } else {
            return false;
        }
    }

    public Company getCompanyById (int index) {
        if (companies.containsKey(index))
            return companies.get(index);
        else
            throw new NoSuchElementException("Brak firmy o wskazanym indeksie");
    }

    public void addEmployee(Employee employee) {
            employees.put(employee.getId(), employee);
    }

    public boolean removeEmployee(Employee employee) {
        if (employees.containsValue(employee)) {
            employees.remove(employee.getId());
            return true;
        } else {
            return false;
        }
    }

    public Employee getEmployeeById(int index) {
        if (employees.containsKey(index))
            return employees.get(index);
        else
            throw new NoSuchElementException("Brak pracownika o wskazanym indeksie");
    }

    public void addNewProduct(Product product) {
        products.put(product.getId(), product);
    }

    public void addNewOrder(Order order) {
        orders.put(order.getId(), order);
    }

    public boolean removeProduct(Product product) {
        if (products.containsValue(product)) {
            products.remove(product.getId());
            return true;
        } else {
            return false;
        }
    }

    public void decreaseProductQuantity(int index, int quantityOrdered) {
        Product product = products.get(index);
        int quantityInStock = product.getUnitsInStock();
        if (!products.containsKey(index))
            throw new NoSuchElementException("Brak produktu o wskazanym indeksie");
        if (quantityInStock < quantityOrdered) {
            throw new NotEnoughProductInStockException("Nie ma tylu produktów w magazynie");
        }
        product.setUnitsInStock(-quantityOrdered);

    }


    public Product getProductById(int index) {
        if (products.containsKey(index))
            return products.get(index);
        else
            throw new NoSuchElementException("Brak produktu o podanym indeksie");

    }

}
