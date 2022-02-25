package controllers;

import io.ConsolePrinter;
import io.DataReader;
import managers.WarehouseManager;
import model.Company;

import java.util.InputMismatchException;

public class SuppliersControl {
    ConsolePrinter printer;
    DataReader reader;
    WarehouseManager warehouseManager;

    public SuppliersControl(DataReader reader, ConsolePrinter printer, WarehouseManager warehouseManager) {
        this.reader = reader;
        this.printer = printer;
        this.warehouseManager = warehouseManager;
    }

    public void displayMenu() {
    MenuOption option;
    String menuType = "SupplierMenu";

    do {
        MenuOption.printOptions(menuType);
        option = reader.getOption(menuType);

        switch (option) {
            case ADD_SUPPLIER -> {
                addSupplier();
            }
            case DISPLAY_SUPPLIERS -> {
                displaySuppliers();
            }
            case REMOVE_SUPPLIER -> {
                removeSupplier();
            }
            case EXIT_SUPPLIER_MENU -> {

            }
        }

    } while (option != MenuOption.EXIT_SUPPLIER_MENU);

    }

    private void removeSupplier() {
        if (warehouseManager.getSuppliers().isEmpty()) {
            printer.printLine("Brak elementów do usunięcia");
        }
        else {
            try {
                displaySuppliers();
                int companyIdToRemove = reader.readIdToRemove();

                Company companyToRemove = warehouseManager.getCompanies().get(companyIdToRemove);
                boolean done = warehouseManager.removeCompany(companyToRemove);

                if (done)
                    printer.printLine("Udało się usunąc dostawcę");
                else
                    printer.printLine("Nie udało się usunąć dostawcy. Brak dostawcy o wskazanej nazwie");
            } catch (InputMismatchException e) {
                printer.printLine("Wprowadzono niewłaściwą wartość");
            }

        }

    }


    private void displaySuppliers() {
        printer.displaySuppliers(warehouseManager.getCompanies());
    }

    private void addSupplier() {
            Company supplier = reader.createCompanyByUser(DataReader.SUPPLIER_CREATE);
            warehouseManager.addCompany(supplier);

    }
}
