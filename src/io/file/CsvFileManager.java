package io.file;

import exceptions.DataExportException;
import exceptions.DataImportException;
import managers.WarehouseManager;
import model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.DAYS;

public class CsvFileManager implements FileManager{
    private final static String CUSTOMERS_FILE = "customers.csv";
    private final static String EMPLOYEES_FILE = "employees.csv";
    private final static String ORDERS_FILE = "orders.csv";
    private final static String PRODUCTS_FILE = "products.csv";
    private final static String SHIPPERS_FILE = "shippers.csv";
    private final static String SUPPLIERS_FILE = "suppliers.csv";

    @Override
    public WarehouseManager importData() {
        WarehouseManager warehouseManager = new WarehouseManager();
        importCustomers(warehouseManager);
        importShippers(warehouseManager);
        importSuppliers(warehouseManager);
        importEmployees(warehouseManager);
        importProducts(warehouseManager);
        importOrders(warehouseManager);
        return warehouseManager;
    }

    private void importOrders(WarehouseManager warehouseManager) {
        try (Scanner fileReader = new Scanner(new File(ORDERS_FILE))) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                Order order = createOrderFromStringLine(line, warehouseManager);
                warehouseManager.addNewOrder(order);
            }

        } catch (FileNotFoundException e) {
            throw new DataImportException("Nie znaleziono pliku " + ORDERS_FILE);
        }
    }

    private Order createOrderFromStringLine(String line, WarehouseManager warehouseManager) {
        LocalDate shippedDate = null;
        String[] data = line.split(";");
        String orderedProducts = data[1];
        double totalPrice = Double.parseDouble(data[2]);
        Customer customer = (Customer)warehouseManager.getCompanyById(Integer.parseInt(data[3]));
        Employee employee = warehouseManager.getEmployeeById(Integer.parseInt(data[4]));
        Shipper shipper = (Shipper)warehouseManager.getCompanyById(Integer.parseInt(data[5]));
        LocalDate orderDate = LocalDate.parse(data[6]);
        LocalDate requiredDate = LocalDate.parse(data[7]);
            try {
                shippedDate = LocalDate.parse(data[8]);
            } catch (DateTimeParseException e) {

            }
        double freight = Double.parseDouble(data[9]);
        Company company = warehouseManager.getCompanyById(Integer.parseInt(data[10]));

        return new Order(orderedProducts, totalPrice, customer, employee, shipper, orderDate, requiredDate,
                shippedDate, freight, company);

    }

    private void importProducts(WarehouseManager warehouseManager) {
        try (Scanner fileReader = new Scanner(new File(PRODUCTS_FILE))) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                Product product = createProductFromStringLine(line, warehouseManager);
                warehouseManager.addNewProduct(product);
            }

        } catch (FileNotFoundException e) {
            throw new DataImportException("Nie znaleziono pliku " + PRODUCTS_FILE);
        }
    }

    private Product createProductFromStringLine(String line, WarehouseManager warehouseManager) {
        String[] data = line.split(";");
        String name = data[1];
        Supplier supplier = (Supplier)warehouseManager.getCompanyById(Integer.parseInt(data[2]));
        Category category = Category.createFromInt(Integer.parseInt(data[3]));
        double unitPrice = Double.parseDouble(data[4]);
        int unitsInStock = Integer.parseInt(data[5]);

        return new Product(name, supplier, category, unitsInStock, unitPrice);
    }

    private void importEmployees(WarehouseManager warehouseManager) {
        try (Scanner fileReader = new Scanner(new File(EMPLOYEES_FILE))) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                Employee employee = createEmployeeFromStringLine(line);
                warehouseManager.addEmployee(employee);
            }

        } catch (FileNotFoundException e) {
            throw new DataImportException("Nie znaleziono pliku " + EMPLOYEES_FILE);
        }
    }

    private Employee createEmployeeFromStringLine(String line) {
        String[] data = line.split(";");
        String firstName = data[1];
        String lastName = data[2];
        String title = data[3];
        LocalDate hireDate = LocalDate.parse(data[4]);
        LocalDate birthDate = LocalDate.parse(data[5]);
        String phone = data[6];
        String email = data[7];
        String address = data[8];
        String city = data[9];
        String postalCode = data[10];
        String region = data[11];
        String country = data[12];

        return new Employee(address, city, region, postalCode, country, firstName, lastName,
                birthDate, phone, email, title, hireDate);
    }

    private void importSuppliers(WarehouseManager warehouseManager) {
        try (Scanner fileReader = new Scanner(new File(SUPPLIERS_FILE))) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                Supplier supplier = createSupplierFromStringLine(line);
                warehouseManager.addCompany(supplier);
            }

        } catch (FileNotFoundException e) {
            throw new DataImportException("Nie znaleziono pliku " + SUPPLIERS_FILE);
        }
    }

    private Supplier createSupplierFromStringLine(String line) {
        String[] data = line.split(";");
        int id = Integer.parseInt(data[0]);
        String companyName = data[1];
        String contactName = data[2];
        String phone = data[3];
        String email = data[4];
        String address = data[5];
        String city = data[6];
        String region = data[7];
        String postalCode = data[8];
        String country = data[9];
        String homePage = data[10];

        return new Supplier(id, address, city, region,postalCode, country, contactName, phone,
                email, companyName, homePage);
    }

    private void importShippers(WarehouseManager warehouseManager) {
        try (Scanner fileReader = new Scanner(new File(SHIPPERS_FILE))) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                Shipper shipper = createShipperFromStringLine(line);
                warehouseManager.addCompany(shipper);
            }

        } catch (FileNotFoundException e) {
            throw new DataImportException("Nie znaleziono pliku " + SHIPPERS_FILE);
        }
    }

    private Shipper createShipperFromStringLine(String line) {
        String[] data = line.split(";");
        int id = Integer.parseInt(data[0]);
        String companyName = data[1];
        String contactName = data[2];
        String phone = data[3];
        String email = data[4];
        String address = data[5];
        String city = data[6];
        String region = data[7];
        String postalCode = data[8];
        String country = data[9];

        return new Shipper(id, address, city, region,postalCode, country, contactName, phone,
                email, companyName);
    }

    private void importCustomers(WarehouseManager warehouseManager) {
        try (Scanner fileReader = new Scanner(new File(CUSTOMERS_FILE))) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                Customer customer = createCustomerFromStringLine(line);
                warehouseManager.addCompany(customer);
            }

        } catch (FileNotFoundException e) {
            throw new DataImportException("Nie znaleziono pliku " + CUSTOMERS_FILE);
        }
    }

    private Customer createCustomerFromStringLine(String line) {
        String[] data = line.split(";");
        int id = Integer.parseInt(data[0]);
        String companyName = data[1];
        String contactName = data[2];
        String phone = data[3];
        String email = data[4];
        String address = data[5];
        String city = data[6];
        String region = data[7];
        String postalCode = data[8];
        String country = data[9];

        return new Customer(id, address, city, region,postalCode, country, contactName, phone,
                email, companyName);

    }

    @Override
    public void exportData(WarehouseManager warehouseManager) {
          exportCustomers(warehouseManager);
          exportEmployees(warehouseManager);
          exportOrders(warehouseManager);
          exportProducts(warehouseManager);
          exportShippers(warehouseManager);
          exportSuppliers(warehouseManager);
    }

    private void exportCustomers(WarehouseManager warehouseManager) {
        List<Customer> customers = warehouseManager.getCustomers();
        try (
                var fs = new FileWriter(CUSTOMERS_FILE);
                var br = new BufferedWriter(fs);
                ) {
            for (Customer customer : customers) {
                br.write(customer.toCsv());
                br.newLine();
            }
        } catch (IOException e) {
            throw new DataExportException("Nie udało się zapisać danych do pliku " + CUSTOMERS_FILE);
        }

    }

    private void exportEmployees(WarehouseManager warehouseManager) {
        List<Employee> employees = warehouseManager.getEmployees();
        try (
                var fs = new FileWriter(EMPLOYEES_FILE);
                var br = new BufferedWriter(fs);
        ) {
            for (Employee employee : employees) {
                br.write(employee.toCsv());
                br.newLine();
            }
        } catch (IOException e) {
            throw new DataExportException("Nie udało się zapisać danych do pliku " + EMPLOYEES_FILE);
        }
    }

    private void exportOrders(WarehouseManager warehouseManager) {
        List<Order> orders = warehouseManager.getOrders();
        try (
                var fs = new FileWriter(ORDERS_FILE);
                var br = new BufferedWriter(fs);
        ) {
            for (Order order : orders) {
                br.write(order.toCsv());
                br.newLine();
            }
        } catch (IOException e) {
            throw new DataExportException("Nie udało się zapisać danych do pliku " + ORDERS_FILE);
        }

    }

    private void exportProducts(WarehouseManager warehouseManager) {
        List<Product> products = warehouseManager.getProducts();
        try (
                var fs = new FileWriter(PRODUCTS_FILE);
                var br = new BufferedWriter(fs);
        ) {
            for (Product product : products) {
                br.write(product.toCsv());
                br.newLine();
            }
        } catch (IOException e) {
            throw new DataExportException("Nie udało się zapisać danych do pliku " + PRODUCTS_FILE);
        }


    }

    private void exportShippers(WarehouseManager warehouseManager) {
        List<Shipper> shippers = warehouseManager.getShippers();
        try (
                var fs = new FileWriter(SHIPPERS_FILE);
                var br = new BufferedWriter(fs);
        ) {
            for (Shipper shipper : shippers) {
                br.write(shipper.toCsv());
                br.newLine();
            }
        } catch (IOException e) {
            throw new DataExportException("Nie udało się zapisać danych do pliku " + SHIPPERS_FILE);
        }
    }

    private void exportSuppliers(WarehouseManager warehouseManager) {
        List<Supplier> suppliers = warehouseManager.getSuppliers();
        try (
                var fs = new FileWriter(SUPPLIERS_FILE);
                var br = new BufferedWriter(fs);
        ) {
            for (Supplier supplier : suppliers) {
                br.write(supplier.toCsv());
                br.newLine();
            }
        } catch (IOException e) {
            throw new DataExportException("Nie udało się zapisać danych do pliku " + SUPPLIERS_FILE);
        }
    }
}
