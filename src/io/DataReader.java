package io;

import controllers.MenuOption;
import exceptions.*;
import managers.WarehouseManager;
import model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DataReader {
    public final static int CUSTOMER_CREATE = 0;
    public final static int SHIPPER_CREATE = 1;
    public final static int SUPPLIER_CREATE = 2;
    public final static int OTHER_COMPANY_CREATE = 3;
    private final static int CUSTOMER_ADRESS = 1;
    private final static int OTHER_ADRESS = 2;


    private final Scanner scanner = new Scanner(System.in);
    private final ConsolePrinter printer;

    public DataReader(ConsolePrinter printer) {
        this.printer = printer;
    }

    public int readOptionFromUser() {
        try {
            return scanner.nextInt();
        } finally {
            scanner.nextLine();
        }
    }

    public int readIdToRemove() {
        printer.print("Wprowadz ID które chcesz usunąć: ");
        return readOptionFromUser();
    }

    public int readId() {
        printer.print("Wybierz ID: ");
        return readOptionFromUser();
    }

    public int readQuantityToAdd() {
        printer.print("Wpisz liczbę produktu do dodania: ");
        return readOptionFromUser();
    }

    public String getString() {
        return scanner.nextLine();
    }


    public MenuOption getOption(String menuType) {
        boolean optionOk = false;
        MenuOption option = null;

        while (!optionOk) {
            try {
                option = MenuOption.createFromInt(readOptionFromUser(), menuType);
                optionOk = true;
            } catch (NoSuchOptionException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Niewprowadzono liczby. Spróbuj ponownie");
            }
        }
        return option;
    }

    public Company createCompanyByUser(int createOption) {
        printer.printLine("Podaj nazwę firmy");
        String companyName = scanner.nextLine();
        printer.printLine("Podaj imię i nazwisko osoby kontaktowej");
        String contactName = scanner.nextLine();
        printer.printLine("Podaj telefon");
        String phone = scanner.nextLine();
        printer.printLine("Podaj email");
        String email = scanner.nextLine();
        printer.printLine("Podaj miasto");
        String city = scanner.nextLine();
        printer.printLine("Podaj dokładny adres tj. nazwa ulicy, numer");
        String address = scanner.nextLine();
        printer.printLine("Podaj wojewodztwo");
        String region = scanner.nextLine();
        printer.printLine("Podaj kod pocztowy");
        String postalCode = scanner.nextLine();
        printer.printLine("Podaj kraj");
        String country = scanner.nextLine();

        if (createOption == DataReader.CUSTOMER_CREATE) {
            return new Customer(address, city, region, postalCode, country, contactName, phone, email,
                    companyName);
        } else if (createOption == DataReader.SUPPLIER_CREATE) {
            printer.printLine("Podaj adres strony internetowej, jeśli firma taką posiada");
            String page = scanner.nextLine();
            return new Supplier(address, city, region, postalCode, country, contactName, phone,
                    email,companyName, page);
        } else if (createOption == DataReader.SHIPPER_CREATE) {
            return new Shipper(address, city, region, postalCode, country, contactName, phone, email,
                    companyName);
        } else if (createOption == DataReader.OTHER_COMPANY_CREATE) {
            return new Company(address, city, region, postalCode, country, contactName, phone, email, companyName);
        }
        else {
            throw new NoSuchOptionException("Nie można utworzyć obiektu");
        }
    }

    private LocalDate readDateFromUser() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateInput = scanner.nextLine();
        return LocalDate.parse(dateInput, dtf);
    }

    public Employee createEmployeeByUser() {
        printer.printLine("Podaj imię");
        String firstName = scanner.nextLine();
        printer.printLine("Podaj nazwisko");
        String lastName = scanner.nextLine();
        printer.printLine("Podaj stanowisko");
        String title = scanner.nextLine();
        printer.printLine("Podaj datę urodzenia pracownika w formacie yyyy-MM-dd");
        LocalDate birthDate = readDateFromUser();
        printer.printLine("Podaj datę przyjęcia pracownika do firmy w formacie yyyy-MM-dd");
        LocalDate hireDate = readDateFromUser();
        if (!Employee.checkIfAdult(birthDate, hireDate))
            throw new AgeViolationException("Pracownik firmy musi mieć minimum 18 lat");
        printer.printLine("Podaj nr telefonu");
        String phone = scanner.nextLine();
        printer.printLine("Podaj adres e-mail");
        String email = scanner.nextLine();
        printer.printLine("Podaj dokładny adres tj. nazwa ulicy, numer");
        String address = scanner.nextLine();
        printer.printLine("Podaj miasto");
        String city = scanner.nextLine();
        printer.printLine("Podaj wojewodztwo");
        String region = scanner.nextLine();
        printer.printLine("Podaj kod pocztowy");
        String postalCode = scanner.nextLine();
        printer.printLine("Podaj kraj pracownika");
        String country = scanner.nextLine();


        return new Employee(address, city, region, postalCode, country, firstName, lastName, birthDate, phone,
                email, title, hireDate);
    }

    public Product createProductByUser(WarehouseManager warehouseManager) {
        List<Supplier> suppliers = warehouseManager.getSuppliers();
        Supplier supplier = null;
        printer.printLine("Podaj nazwę produktu");
        String name = scanner.nextLine();
        if (suppliers.size() != 0) {
            printer.printLine("Wybierz ID dostawcy z poniższej listy");
            printer.displaySuppliers(warehouseManager.getCompanies());
            printer.print("Wybierz ID dostawcy: ");
            supplier = getSupplier(warehouseManager);
        } else {
            throw new NoSuchElementException("Brak dostawców. Dodaj najpierw dostawcę");
        }
        printer.printLine("Wybierz ID kategorii z listy poniżej");
        Category.printOptions();
        Category category = Category.createFromInt(readOptionFromUser());
        printer.printLine("Wpisz liczbę produktu jaką chcesz dodać");
        int quantity = readOptionFromUser();
        printer.printLine("Wpisz cenę za sztukę");
        int unitPrice = readOptionFromUser();

        return new Product(name, supplier, category, quantity, unitPrice);
    }

    private Supplier getSupplier(WarehouseManager warehouseManager) {
        Supplier supplier;
        int id = readOptionFromUser();
        if (warehouseManager.getCompanyById(id) instanceof Supplier) {
            supplier = (Supplier) warehouseManager.getCompanyById(id);
        } else {
            throw new NoSuchElementException("Brak firmy o wskazanym indeksie");
        }
        return supplier;
    }

    private Company readPlaceToDeliver(Customer customer) {
        printer.printLine("Na jaki adres ma być wysłana przesyłka?");
        printer.printLine(CUSTOMER_ADRESS + ". Adres siedziby klienta: " + customer.getCompanyAdress());
        printer.printLine(OTHER_ADRESS + ". Inny adres");
        int userChoice = readOptionFromUser();
        if (userChoice == 1)
            return customer;
        else if (userChoice == 2) {
            return createCompanyByUser(DataReader.OTHER_COMPANY_CREATE);
        }
        else
            throw new NoSuchOptionException("Podałeś niewłaściwą opcję");
    }

    private Shipper chooseShipperFromList(WarehouseManager warehouseManager) {
        List<Shipper> shippers = warehouseManager.getShippers();
        if (shippers.size() != 0) {
            Shipper shipper = null;
            printer.printLine("Wybierz przewoźnika");
            printer.displayShippers(shippers);
            printer.print("Wpisz ID przewoźnika: ");
            return getShipper(warehouseManager);
        } else {
            throw new NoSuchElementException("Brak przewoźników w bazie. Dodaj najpierw przewoźnika");
        }
    }

    private Shipper getShipper(WarehouseManager warehouseManager) {
        Shipper shipper;
        int id = readOptionFromUser();
        if (warehouseManager.getCompanyById(id) instanceof Shipper) {
            shipper = (Shipper) warehouseManager.getCompanyById(id);
        } else {
            throw new NoSuchElementException("Brak firmy o wskazanym indeksie");
        }
        return shipper;
    }

    private Employee chooseEmployeeFromList(WarehouseManager warehouseManager) {
        List<Employee> employees = warehouseManager.getEmployees();
        if (employees.size() != 0) {
            printer.printLine("Wybierz pracownika realizującego zamówienie z listy");
            printer.displayEmployees(employees);
            printer.print("Wpisz ID pracownika: ");
            int empIndex = readOptionFromUser();
            return warehouseManager.getEmployeeById(empIndex);
        } else {
            throw new NoSuchElementException("Brak pracowników w bazie. Dodaj najpierw pracowników");
        }
    }

    private Customer chooseClientFromList(WarehouseManager warehouseManager) {
        List<Customer> customers = warehouseManager.getCustomers();
        if (customers.size() != 0) {
            Customer customer = null;
            printer.printLine("Wybierz klienta składającego zamówienie z listy");
            printer.displayCustomers(customers);
            printer.print("Wpisz ID klienta: ");
            return getCustomer(warehouseManager);
        } else {
            throw new NoSuchElementException("Brak klientów w bazie. Dodaj najpierw klienta");
        }

    }

    private Customer getCustomer(WarehouseManager warehouseManager) {
        Customer customer;
        int id = readOptionFromUser();
        if (warehouseManager.getCompanyById(id) instanceof Customer) {
            customer = (Customer) warehouseManager.getCompanyById(id);
        } else {
            throw new NoSuchElementException("Brak firmy o wskazanym indeksie");
        }
        return customer;
    }

    public Order readOrderInfoFromUser(WarehouseManager warehouseManager) {
        Customer customer = chooseClientFromList(warehouseManager);
        Employee emp = chooseEmployeeFromList(warehouseManager);
        Shipper ship = chooseShipperFromList(warehouseManager);
        printer.printLine("Wprowadź datę złożenia zamówienia w formacie yyyy-MM-dd");
        LocalDate orderDate = readDateFromUser();
        printer.printLine("Wprowadź liczbę dni potrzebnych na doręczenie przesyłki");
        int delTime = readOptionFromUser();
        printer.printLine("Wprowadź wysokość opłaty za przewóz");
        double freight = scanner.nextDouble();
        scanner.nextLine();
        Company companyToDeliver = readPlaceToDeliver(customer);
        Cart cart = readProductsToOrder(warehouseManager);
        String orderedProducts = cart.getProducts();
        double totalPrice = cart.getTotalValue();

        return new Order(customer, emp, ship, orderedProducts, totalPrice, orderDate, delTime, freight, companyToDeliver);
    }

    public Cart readProductsToOrder(WarehouseManager warehouseManager) {
        int option;
        Cart cart = new Cart();


        do {
            printer.displayNewProductOptions();
            option = readOptionFromUser();

            if (option == 0) {
                printer.printLine("Zakończono dodawanie produktów do koszyka");
            }
            else if (option == 1) {
                printer.displayProducts(warehouseManager.getProducts());
                try {
                    printer.print("Wybierz indeks produktu: ");
                    int choice = readOptionFromUser();
                    Product product = warehouseManager.getProductById(choice);
                    printer.print("Wybierz ilość produktu: ");
                    int quanChoice = readOptionFromUser();
                    if (product.getUnitsInStock() >= quanChoice) {
                        warehouseManager.decreaseProductQuantity(choice, quanChoice);
                        cart.setProducts(product.getName(), quanChoice);
                        cart.setTotalValue(product.getUnitPrice(), quanChoice);
                    } else
                        printer.printLine("Nie ma tyle produktu w magazynie");
                } catch (NoSuchOptionException | NotEnoughProductInStockException e) {
                    printer.printLine(e.getMessage());
                } catch (InputMismatchException e) {
                    printer.printLine("Wprowadzono błędne dane");
                }
            }
            else {
                throw new NoSuchOptionException("Brak takiej opcji w menu");
            }

        } while (option != 0);
        return cart;
    }

    public void close() {
        scanner.close();
    }
}
