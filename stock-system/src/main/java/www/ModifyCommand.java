package www;

public class ModifyCommand implements Command{
    private Database database;

    public ModifyCommand(Database database) {
        this.database = database;
    }

    @Override
    public void doCommand() {
        database.modify_product();
    }
}
