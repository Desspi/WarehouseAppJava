package controllers;

import exceptions.NoSuchOptionException;
import io.DataReader;

import java.util.InputMismatchException;

public enum MenuOption {
    EXIT(0,"Główne", "Wyjdź z programu"),
    MANAGE_ORDERS(1, "Główne","Zarządzanie zamówieniami"),
    MANAGE_PRODUCTS(2, "Główne","Zarządzaj produktami"),
    MANAGE_CUSTOMERS(3, "Główne","Zarządzaj klientami"),
    MANAGE_SUPPLIERS(4,"Główne", "Zarządzaj dostawcami"),
    MANAGE_SHIPPERS(5, "Główne","Zarządzaj przewoźnikami"),
    MANAGE_EMPLOYEES(6, "Główne","Zarządzaj pracownikami"),
    EXIT_SUPPLIER_MENU(0, "SupplierMenu", "Wróć do głównego menu"),
    ADD_SUPPLIER(1, "SupplierMenu", "Dodaj dostawcę"),
    DISPLAY_SUPPLIERS(2, "SupplierMenu", "Wyświetl dostawców"),
    REMOVE_SUPPLIER(3, "SupplierMenu", "Usuń dostawcę"),
    EXIT_CUSTOMER_MENU(0, "CustomerMenu", "Wróć do głównego menu"),
    ADD_CUSTOMER(1, "CustomerMenu", "Dodaj klienta"),
    DISPLAY_CUSTOMERS(2, "CustomerMenu", "Wyświetl klientów"),
    REMOVE_CUSTOMER(3, "CustomerMenu", "Usuń klinta"),
    EXIT_SHIPPER_MENU(0, "ShipperMenu", "Wróć do głównego menu"),
    ADD_SHIPPER(1, "ShipperMenu", "Dodaj przewoznika"),
    DISPLAY_SHIPPERS(2, "ShipperMenu", "Wyświetl przewoznikow"),
    REMOVE_SHIPPER(3, "ShipperMenu", "Usuń przewoznika"),
    EXIT_EMPLOYEE_MENU(0, "EmployeeMenu", "Wróć do głównego menu"),
    ADD_EMPLOYEE(1, "EmployeeMenu", "Dodaj pracownika"),
    DISPLAY_EMPLOYEES(2, "EmployeeMenu", "Wyświetl pracownikow"),
    REMOVE_EMPLOYEE(3, "EmployeeMenu", "Usuń pracownika"),
    EXIT_PRODUCT_MENU(0, "ProductMenu", "Wróć do głównego menu"),
    ADD_NEW_PRODUCT(1, "ProductMenu", "Dodaj nowy produkt"),
    DISPLAY_PRODUCTS(2, "ProductMenu", "Wyświetl informacje o produktach"),
    REMOVE_PRODUCT(3, "ProductMenu", "Usuń produkt z bazy"),
    ADD_PRODUCT(4, "ProductMenu", "Dodaj produkty"),
    EXIT_ORDER_MENU(0, "OrderMenu", "Wróć do głównego menu"),
    ADD_NEW_ORDER(1, "OrderMenu", "Dodaj nowe zamówienie"),
    DISPLAY_ORDERS(2, "OrderMenu", "Wyświetl informacje o zamówieniach");


    private final int number;
    private final String type;
    private final String description;

    MenuOption(int number, String type, String description) {
        this.number = number;
        this.type = type;
        this.description = description;
    }


    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return number + ". " + description;
    }

    public static MenuOption createFromInt(int number, String type) {
        for (MenuOption value : values()) {
            if(type.equals(value.getType()) && number == value.getNumber())
                return value;
        }
        throw new NoSuchOptionException("Brak opcji o wskazanym indeksie");
    }

    public static void printOptions(String type) {
        for (MenuOption value : values()) {
            if (type.equals(value.getType()))
                System.out.println(value.toString());

        }
    }


}
