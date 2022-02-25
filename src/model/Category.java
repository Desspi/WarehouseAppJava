package model;

import controllers.MenuOption;
import exceptions.NoSuchCategoryException;
import exceptions.NoSuchOptionException;
import io.ConsolePrinter;

public enum Category {
    BEVERAGES(1, "Soft drinks, coffees, teas, beers, and ales"),
    CONDIMENTS(2, "Sweet and savory sauces, relishes, spreads, and seasonings"),
    CONFECTIONS(3, "Desserts, candies, and sweet breads"),
    DAIRY_PRODUCTS(4, "Cheeses"),
    GRAINS_CEREALS(5, "Breads, crackers, pasta, and cereal"),
    MEAT_POULTRY(6, "Prepared meats"),
    PRODUCE(7, "Dried fruit and bean curd"),
    SEAFOOD(8, "Seaweed and fish");

    private final int id;
    private final String description;
    private final static ConsolePrinter printer = new ConsolePrinter();

    Category(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static Category createFromInt(int number) {
        for (Category value : values()) {
            if (value.id == number)
                return value;
        }
        throw new NoSuchCategoryException("Wybrano opcję spoza zakresu. Dana kategoria nie istnieje");
    }

    public static void printOptions() {
        for (Category value : values()) {
            printer.printLine(value.toString());

        }
    }

    @Override
    public String toString() {
        return id + ". " + name() + " (" +  description + ")";
    }
}
