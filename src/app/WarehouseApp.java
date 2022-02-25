package app;

import controllers.WarehouseControl;

public class WarehouseApp {
    private final static String APP_NAME = "WarehouseApp version 1.0";
    public static void main(String[] args) {
        System.out.println(APP_NAME);
        WarehouseControl warehouseControl = new WarehouseControl();
        warehouseControl.controlLoop();

    }
}
