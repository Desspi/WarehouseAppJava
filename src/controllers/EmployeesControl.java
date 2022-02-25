package controllers;

import exceptions.AgeViolationException;
import io.ConsolePrinter;
import io.DataReader;
import managers.WarehouseManager;
import model.Employee;

import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

public class EmployeesControl {
    ConsolePrinter printer;
    DataReader reader;
    WarehouseManager warehouseManager;

    public EmployeesControl(DataReader reader, ConsolePrinter printer, WarehouseManager warehouseManager) {
        this.printer = printer;
        this.reader = reader;
        this.warehouseManager = warehouseManager;
    }

        public void displayMenu() {
            MenuOption option;
            String menuType = "EmployeeMenu";

            do {
                MenuOption.printOptions(menuType);
                option = reader.getOption(menuType);

                switch (option) {
                    case ADD_EMPLOYEE -> {
                        addEmployee();
                    }
                    case DISPLAY_EMPLOYEES-> {
                        displayEmployees();
                    }
                    case REMOVE_EMPLOYEE -> {
                        removeEmployee();
                    }
                    case EXIT_EMPLOYEE_MENU -> {

                    }
                }

            } while (option != MenuOption.EXIT_EMPLOYEE_MENU);

        }

    private void removeEmployee() {
        if (warehouseManager.getEmployees().isEmpty()) {
            printer.printLine("Brak elementów do usunięcia");
        }
        else {
            displayEmployees();
            int employeeIdToRemove = reader.readIdToRemove();

            try {
                Employee employeeToRemove = warehouseManager.getEmployeeById(employeeIdToRemove);
                boolean done = warehouseManager.removeEmployee(employeeToRemove);
                printer.printLine("Usunięto pracownika");
            } catch (IndexOutOfBoundsException | InputMismatchException e) {
                printer.printLine("Nie udało się usunąć pracownika. Wprowadzono błędne dane");
            }


        }
    }

    private void displayEmployees() {
        printer.displayEmployees(warehouseManager.getEmployees());
    }

    private void addEmployee() {
        try {
            Employee employee = reader.createEmployeeByUser();
            warehouseManager.addEmployee(employee);
        } catch (DateTimeParseException e) {
            printer.printLine("Wprowadzono nieprawidłowy format daty");
        } catch (AgeViolationException e) {
            printer.printLine(e.getMessage());
        }
    }
}



