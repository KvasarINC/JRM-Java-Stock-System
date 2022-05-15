package www;

public class DeleteCommand implements Command{
    private Database database;

    public DeleteCommand(Database database) {
        this.database = database;
    }

    @Override
    public void doCommand() {
        database.delete_product();
    }
}
