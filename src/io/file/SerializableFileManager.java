package io.file;

import exceptions.DataExportException;
import exceptions.DataImportException;
import managers.WarehouseManager;

import java.io.*;

public class SerializableFileManager implements FileManager {
    private static final String FILE_NAME = "warehouse.obj";

    @Override
    public WarehouseManager importData() {
        try (
            FileInputStream fis = new FileInputStream(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis))
        {
            return (WarehouseManager) ois.readObject();
        } catch (IOException e) {
            throw new DataImportException("Błąd podczas wczytywania danych z pliku " + FILE_NAME);
        } catch (ClassNotFoundException e) {
            throw new DataImportException("Niezgodny typ danych w pliku " + FILE_NAME);
        }

    }

    @Override
    public void exportData(WarehouseManager warehouseManager) {
        try (
                FileOutputStream fos = new FileOutputStream(FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)
            ) {
            oos.writeObject(warehouseManager);
        } catch (FileNotFoundException e) {
            throw new DataExportException("Brak pliku " + FILE_NAME);
        } catch (IOException e) {
            throw new DataExportException("Błąd zapisu danych do pliku " + FILE_NAME);
        }

    }
}
