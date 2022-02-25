package io.file;


import managers.WarehouseManager;

public interface FileManager{
    WarehouseManager importData();
    void exportData(WarehouseManager warehouseManager);
}
