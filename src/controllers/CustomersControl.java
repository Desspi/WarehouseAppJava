package controllers;

import io.ConsolePrinter;
import io.DataReader;
import managers.WarehouseManager;
import model.Company;

import java.util.InputMismatchException;

public class CustomersControl {
    public final static int CUSTOMER = 0;

    ConsolePrinter printer;
    DataReader reader;
    WarehouseManager warehouseManager;

    public CustomersControl(DataReader reader, ConsolePrinter printer, WarehouseManager warehouseManager) {
        this.reader = reader;
        this.printer = printer;
        this.warehouseManager= warehouseManager;
    }

    public void displayMenu() {
        MenuOption option;
        String menuType = "CustomerMenu";

        do {
            MenuOption.printOptions(menuType);
            option = reader.getOption(menuType);

            switch (option) {
                case ADD_CUSTOMER -> {
                    addCustomer();
                }
                case DISPLAY_CUSTOMERS -> {
                    displayCustomers();
                }
                case REMOVE_CUSTOMER -> {
                    removeCustomer();
                }
                case EXIT_CUSTOMER_MENU -> {

                }
            }

        } while (option != MenuOption.EXIT_CUSTOMER_MENU);
    }

    private void removeCustomer() {
        if (warehouseManager.getCustomers().isEmpty()) {
            printer.printLine("Brak elementów do usunięcia");
        }
        else {
            try {
                displayCustomers();
                int companyIdToRemove = reader.readIdToRemove();

                Company companyToRemove = warehouseManager.getCompanies().get(companyIdToRemove);
                boolean done = warehouseManager.removeCompany(companyToRemove);

                if (done)
                    printer.printLine("Udało się usunąc klienta");
                else
                    printer.printLine("Nie udało się usunąć klienta. Nie istnieje klient o wskazanej naziwe");
            } catch (InputMismatchException e) {
                printer.printLine("Wprowadzono nieprawidłową wartość");
            }
        }
    }

    private void displayCustomers() {
        printer.displayCustomers(warehouseManager.getCustomers());
    }

    private void addCustomer() {
            Company customer = reader.createCompanyByUser(DataReader.CUSTOMER_CREATE);
            warehouseManager.addCompany(customer);
    }
}
