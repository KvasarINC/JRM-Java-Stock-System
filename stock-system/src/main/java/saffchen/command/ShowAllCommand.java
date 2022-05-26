package saffchen.command;

import saffchen.database.FileConnection;
import saffchen.utils.FileStorageUtils;

public class ShowAllCommand implements Command {
    @Override
    public void doCommand() {
        FileConnection fileConnection = FileConnection.getInstance("stock_import_csv.csv");
        FileStorageUtils fileStorageUtils = new FileStorageUtils(fileConnection);
        fileStorageUtils.showAllProducts();
    }
}
