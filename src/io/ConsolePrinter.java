package io;

import model.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class ConsolePrinter {
    public void printLine(String text) {
        System.out.println(text.toUpperCase());
    }

    public void print(String text) {
        System.out.print(text.toUpperCase());
    }


    public void displaySuppliers(Map<Integer, Company> companies) {
        int countSuppliers = 0;

        printLine(String.format("%-5s%-20s%-30s%-20s%-30s%-20s%-20s%-20s%-20s%-20s%-30s"
                , "ID", "Nazwa firmy", "Osoba kontaktowa", "Nr telefonu", "Adres e-mail",
                "Adres", "Miasto", "Województwo", "Kod pocztowy", "Państwo", "Strona internetowa"));
        for (Company company : companies.values()) {
            if (company instanceof Supplier) {
                printLine(company.toString());
                countSuppliers++;
            }
        }
        if (countSuppliers == 0)
            printLine("Brak dostawców w bazie");
    }

    public void displayCustomers(List<Customer> customers) {
        if (customers.size() == 0) {
            printLine("Brak klientów w bazie");
        }
        else {
            printLine(String.format("%-5s%-20s%-30s%-20s%-30s%-20s%-20s%-20s%-20s%-20s"
                    , "ID", "Nazwa firmy", "Osoba kontaktowa", "Nr telefonu", "Adres e-mail",
                    "Adres", "Miasto", "Województwo", "Kod pocztowy", "Państwo"));
            for (Customer customer : customers) {
                printLine(customer.toString());
            }
        }
    }

    public void displayShippers(List<Shipper> shippers) {
        if (shippers.size() == 0)
        {
            printLine("Brak przewoznikow w bazie");
        }
        else {
            printLine(String.format("%-5s%-20s%-30s%-20s%-30s%-20s%-20s%-20s%-20s%-20s"
                    , "ID", "Nazwa firmy", "Osoba kontaktowa", "Nr telefonu", "Adres e-mail",
                    "Adres", "Miasto", "Województwo", "Kod pocztowy", "Państwo"));
            for (Shipper shipper : shippers) {
                printLine(shipper.toString());
            }
        }
    }

    public void displayEmployees(List<Employee> employees) {
        if (employees.size() == 0)
            printLine("Brak pracownikow w bazie");
        else {
            printLine(String.format("%-5s%-15s%-20s%-15s%-15s%-15s%-15s%-30s%-20s%-20s%-20s%-20s%-20s", "ID",
                    "Stanowisko", "Data zatrudnienia", "Imie", "Nazwisko",
                    "Data urodzenia", "Nr telefonu", "Adres e-mail", "Adres",
                    "Miasto", "Kod pocztowy", "Województwo", "Kraj"));
            for (Employee employee : employees) {
                printLine(employee.toString());
            }
        }

    }

    public void displayProducts(List<Product> products) {
        if (products.size() == 0) {
            printLine("Brak produktów w bazie");
        } else {
            printLine(String.format("%-5s%-20s%-20s%-25s%-15s%-25s", "ID", "Nazwa",
                    "Nazwa dostawcy", "Kategoria", "Cena [szt.]", "Liczba sztuk w magazynie"));
            for (Product product : products) {
                printLine(product.toString());
            }
        }
    }

    public void displayNewProductOptions() {
        printLine("Wybierz co chcesz zrobić");
        printLine("0. Zakończ dodawanie produktów do zamówienia");
        printLine("1. Dodaj nowy produkt do zamówienia");
    }

    public void displayOrders(List<Order> orders) {
        if (orders.size() != 0) {
            printLine(String.format("%-5s%-30s%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-30s", "ID",
                    "Zamówione produkty", "Wartość zamówienia", "Nazwa klienta", "Zrealizowane przez",
                    "Nazwa przewoźnika", "Data złożenia zamówienia", "Wymagana data dostawy",
                    "Data wysyłki","Cena frachtu", "Adres wysyłki"));
            for (Order order : orders) {
                printLine(order.toString());
            }
        } else {
            printLine("Brak zamówień w bazie");
    }

    }
}
