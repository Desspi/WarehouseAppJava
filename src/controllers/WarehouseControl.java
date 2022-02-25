package controllers;

import exceptions.DataExportException;
import exceptions.DataImportException;
import io.ConsolePrinter;
import io.DataReader;
import io.file.FileManager;
import io.file.FileManagerBuilder;
import managers.WarehouseManager;

public class WarehouseControl {
    ConsolePrinter printer = new ConsolePrinter();
    DataReader reader = new DataReader(printer);
    WarehouseManager warehouseManager;
    FileManager fileManager;
    SuppliersControl suppliersControl;
    CustomersControl customersControl;
    ShippersControl shippersControl;
    EmployeesControl employeesControl;
    ProductsControl productsControl;
    OrdersControl ordersControl;


    public WarehouseControl() {
        fileManager = new FileManagerBuilder(printer, reader).build();
        try {
            warehouseManager = fileManager.importData();
        } catch (DataImportException e) {
            printer.printLine(e.getMessage());
            warehouseManager = new WarehouseManager();
            printer.printLine("Zainicjowano nową bazę");
        }
    }

    public void controlLoop() {
        MenuOption option;
        String menuType = "Główne";

        do {
            MenuOption.printOptions(menuType);
            printer.print("Wprowadź wybraną opcję: ");
            option = reader.getOption(menuType);

            switch (option) {
                case MANAGE_ORDERS -> {
                    ordersControl = new OrdersControl(reader, printer, warehouseManager);
                    ordersControl.displayMenu();
                }
                case MANAGE_PRODUCTS -> {
                    productsControl = new ProductsControl(reader, printer, warehouseManager);
                    productsControl.displayMenu();
                }
                case MANAGE_CUSTOMERS -> {
                    customersControl = new CustomersControl(reader, printer, warehouseManager);
                    customersControl.displayMenu();
                }
                case MANAGE_SUPPLIERS -> {
                    suppliersControl = new SuppliersControl(reader, printer, warehouseManager);
                    suppliersControl.displayMenu();
                }
                case MANAGE_SHIPPERS -> {
                    shippersControl = new ShippersControl(reader, printer, warehouseManager);
                    shippersControl.displayMenu();
                }
                case MANAGE_EMPLOYEES -> {
                    employeesControl = new EmployeesControl(reader, printer, warehouseManager);
                    employeesControl.displayMenu();
                }
                case EXIT -> {
                    exit();
                }
                default -> System.out.println("Wybrałeś opcję spoza zakresu. Spróbuj ponownie");
            }

        } while (option != MenuOption.EXIT);
    }

    private void exit() {
        try {
            fileManager.exportData(warehouseManager);
            printer.printLine("Export danych do pliku zakończony powodzeniem");
        } catch (DataExportException e) {
            printer.printLine(e.getMessage());
        }
        printer.printLine("Żegnaj. Zapraszamy ponownie");
        reader.close();
    }


}
