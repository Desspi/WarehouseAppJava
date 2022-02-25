package controllers;

import io.ConsolePrinter;
import io.DataReader;
import managers.WarehouseManager;
import model.Company;

import java.util.InputMismatchException;

public class ShippersControl {
    ConsolePrinter printer;
    DataReader reader;
    WarehouseManager warehouseManager;

    public ShippersControl(DataReader reader, ConsolePrinter printer, WarehouseManager warehouseManager) {
        this.reader = reader;
        this.printer = printer;
        this.warehouseManager = warehouseManager;
    }

    public void displayMenu() {
        MenuOption option;
        String menuType = "ShipperMenu";

        do {
            MenuOption.printOptions(menuType);
            option = reader.getOption(menuType);

            switch (option) {
                case ADD_SHIPPER -> {
                    addShipper();
                }
                case DISPLAY_SHIPPERS -> {
                    displayShippers();
                }
                case REMOVE_SHIPPER -> {
                    removeShipper();
                }
                case EXIT_SHIPPER_MENU -> {

                }
            }

        } while (option != MenuOption.EXIT_SHIPPER_MENU);
    }

    private void removeShipper() {
        if (warehouseManager.getShippers().isEmpty()) {
            printer.printLine("Brak elementów do usunięcia");
        }
        else {
            try {
                displayShippers();
                int companyIdToRemove = reader.readIdToRemove();

                Company companyToRemove = warehouseManager.getCompanies().get(companyIdToRemove);
                boolean done = warehouseManager.removeCompany(companyToRemove);

                if (done)
                    printer.printLine("Udało się usunąc przewoźnika");
                else
                    printer.printLine("Nie udało się usunąć przewoźnika. Nie istnieje przewożnik o wskazanej naziwe");
            } catch (InputMismatchException e) {
                printer.printLine("Wprowadzono nieprawidłową wartość");
            }
        }
    }

    private void displayShippers() {
        printer.displayShippers(warehouseManager.getShippers());
    }

    private void addShipper() {
            Company shipper = reader.createCompanyByUser(DataReader.SHIPPER_CREATE);
            warehouseManager.addCompany(shipper);
    }
}
