package io.file;

import exceptions.NoSuchFileTypeException;
import io.ConsolePrinter;
import io.DataReader;

import java.util.Locale;

public class FileManagerBuilder {
    private ConsolePrinter printer;
    private DataReader reader;

    public FileManagerBuilder(ConsolePrinter printer, DataReader reader) {
        this.printer = printer;
        this.reader = reader;
    }

    public FileManager build() {
        printer.printLine("Wybierz format danych:");
        FileType fileType = getFileType();

        switch(fileType) {

            case SERIAL -> {
                return new SerializableFileManager();
            }
            case CSV -> {
                return new CsvFileManager();
            }
            default -> throw new NoSuchFileTypeException("Nieobsługiwany typ danych");
        }

    }

    private FileType getFileType() {
        boolean typeOk = false;
        FileType fileType = null;

        do {
            printTypes();
            String type = reader.getString().toUpperCase();
            try {
                fileType = FileType.valueOf(type);
                typeOk = true;
            } catch (IllegalArgumentException e) {
                printer.printLine("Nieobsługiwany typ danych, wybierz ponownie");
            }
        } while (!typeOk);
        return fileType;
    }

    private void printTypes() {
        for (FileType value : FileType.values()) {
            printer.printLine(value.name());
        }
    }


}
