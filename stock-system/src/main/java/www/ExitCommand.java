package www;

public class ExitCommand implements Command{
    @Override
    public void doCommand() {
        System.exit(1);
    }
}
