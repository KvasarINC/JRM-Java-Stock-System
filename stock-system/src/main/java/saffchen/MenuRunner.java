package saffchen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import saffchen.command.*;
import saffchen.utils.*;

import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class MenuRunner {
    public static void main(String[] args) {
        SpringApplication.run(MenuRunner.class, args);

        CommandHolder holder = new CommandHolder();

        Scanner scanner = new Scanner(System.in);
        System.out.println(FileUtils.getParticipants());

        holder.printCommandInfo();
        String inputCommand = "";
        while (true) {
            try {
                System.out.print("Enter the command: ");
                inputCommand = scanner.next().trim().toUpperCase();
                for (Map.Entry<String, Command> entry : holder.getCommandHolder().entrySet()) {
                    if (inputCommand.equals(entry.getKey())) {
                        entry.getValue().doCommand();
                    }
                }
            } catch (Exception e) {
                System.out.println("Unsupported operation \"" + inputCommand + "\". Try again.");
            }
        }
    }
}
