package controllers;

import exceptions.*;
import io.ConsolePrinter;
import io.DataReader;
import managers.WarehouseManager;
import model.Order;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class OrdersControl {

    private final DataReader reader;
    private final ConsolePrinter printer;
    private final WarehouseManager warehouseManager;


    public OrdersControl(DataReader reader, ConsolePrinter printer, WarehouseManager warehouseManager) {
        this.reader = reader;
        this.printer = printer;
        this.warehouseManager = warehouseManager;
    }


    public void displayMenu() {
        MenuOption option;
        String menuType = "OrderMenu";

        do {
            MenuOption.printOptions(menuType);
            option = reader.getOption(menuType);

            switch (option) {
                case ADD_NEW_ORDER -> {
                    addOrder();
                }
                case DISPLAY_ORDERS -> {
                    displayOrders();
                }
                case EXIT_ORDER_MENU -> {

                }
            }

        } while (option != MenuOption.EXIT_ORDER_MENU);
    }

    private void displayOrders() {
        printer.displayOrders(warehouseManager.getOrders());
    }

    private void addOrder() {
        try {
            Order orderInfo = reader.readOrderInfoFromUser(warehouseManager);
            warehouseManager.addNewOrder(orderInfo);
        } catch (NoSuchElementException e) {
            printer.printLine(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            printer.printLine("Wprowadzono błędne dane");
        }
    }
}
