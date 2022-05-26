package saffchen.command;

import saffchen.database.FileConnection;
import saffchen.database.GSheetConnector;
import saffchen.product.Product;
import saffchen.reports.PDFReportFromFile;
import saffchen.utils.FileStorageUtils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Receiver {

    void addProduct(Product product) {
        FileConnection fileConnection = FileConnection.getInstance("stock_import_csv.csv");
        FileStorageUtils fileStorageUtils = new FileStorageUtils(fileConnection);
        fileStorageUtils.addProduct(new Product("Test record",
                        "Description for test record",
                        1200d,
                        Arrays.asList("test1 tag", "test2 tag"),
                        "test category",
                        6,
                        "Ekaterinburg"
                )
        );
    }

    public void modifyProduct() {
        System.out.println("Modified the product...");
    }

    public void deleteProduct() {
        System.out.println("Deleting the product...");
    }

    public void showAll() {

    }

    public void importFromGsheet() {
        //nop
    }

    public void createReport() {

    }
}
