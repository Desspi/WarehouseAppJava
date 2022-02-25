package controllers;

import exceptions.NoSuchCategoryException;
import io.ConsolePrinter;
import io.DataReader;
import managers.WarehouseManager;
import model.Product;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class ProductsControl {
    ConsolePrinter printer;
    DataReader reader;
    WarehouseManager warehouseManager;

    public ProductsControl(DataReader reader,ConsolePrinter printer, WarehouseManager warehouseManager) {
        this.printer = printer;
        this.reader = reader;
        this.warehouseManager = warehouseManager;
    }

    public void displayMenu() {
        MenuOption option;
        String menuType = "ProductMenu";

        do {
            MenuOption.printOptions(menuType);
            option = reader.getOption(menuType);

            switch (option) {
                case ADD_NEW_PRODUCT -> {
                    addNewProduct();
                }
                case DISPLAY_PRODUCTS-> {
                    displayProducts();
                }
                case REMOVE_PRODUCT -> {
                    removeProduct();
                }
                case ADD_PRODUCT -> {
                    addProduct();
                }
                case EXIT_PRODUCT_MENU -> {

                }
            }

        } while (option != MenuOption.EXIT_PRODUCT_MENU);
    }

    private void addProduct() {
        try {
            if (warehouseManager.getProducts().size() > 0) {
            displayProducts();
            Product product = warehouseManager.getProductById(reader.readId());
            int quantityToAdd = reader.readQuantityToAdd();
            product.setUnitsInStock(quantityToAdd);
            } else {
                printer.printLine("Brak produktów w bazie");
            }

        } catch (IllegalArgumentException | NoSuchElementException e) {
            printer.printLine(e.getMessage());
        }
    }

    private void removeProduct() {
        if (warehouseManager.getProducts().isEmpty()) {
            printer.printLine("Brak produktów. Nie ma czego usunąć");
        }
        else {
            try {
                displayProducts();
                int productIdToRemove = reader.readIdToRemove();

                Product productToRemove = warehouseManager.getProductById(productIdToRemove);
                boolean done = warehouseManager.removeProduct(productToRemove);

                if (done)
                    printer.printLine("Udało się usunąc produkt z bazy");
                else
                    printer.printLine("Nie udało się usunąć produktu z bazy");
            } catch (InputMismatchException  | IndexOutOfBoundsException e) {
                printer.printLine("Wprowadzono niewłaściwą wartość");
            }

        }
    }

    private void displayProducts() {
           printer.displayProducts(warehouseManager.getProducts());
    }

    private void addNewProduct() {
        try {
            Product product = reader.createProductByUser(warehouseManager);
            warehouseManager.addNewProduct(product);
        } catch (InputMismatchException e) {
            printer.printLine("Wprowadzono nieprawidłowe dane. Spróbuj ponownie");
        } catch (NoSuchElementException | NoSuchCategoryException e) {
            printer.printLine(e.getMessage());
        }
    }
}
