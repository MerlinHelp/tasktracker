package src.main;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.lang.String;

import src.lib.main.TaskList;


public class InputParser {
    private final String correctUsage = "Correct Usage:\n" +
        "\ttasktracker <command> <command-arg1> <command-arg2> ...\n" +
        "\tUse 'tasktracker help' for general help or " + 
        "'tasktracker <command> help' for command-specific help";
    private final String incorrectCmdUsageFmt = "Incorrect Usage:\n" +
        "\tInvalid command argument to 'tasktracker %s %s'\n%s";
    private final Set<String> validBaseCmds = new HashSet<>(
        List.of(
            "add",
            "update",
            "delete",
            "mark-in-progress",
            "mark-done",
            "list"
        ));
    private final Set<String> validListCmdCmdArgs = new HashSet<>(
        List.of(
            "done",
            "todo",
            "in-progress"
        ));


    private String[] input;


    public InputParser(String[] input) {
        this.input = input;
    }


    public boolean isValidBaseCommand() {
        if (input.length == 0) {
            System.err.println(
                    String.format(
                        "Incorrect usage: No Arguments Found\n%s",
                        correctUsage
                    ));
        } else if (input[0] == null || !validBaseCmds.contains(input[0])) {
            String incorrectCommand = input[0].length() > 10 ? input[0].substring(0, 10) + "..." : input[0];
            System.err.println(
                    String.format(
                        "Incorrect usage: Invalid Argument '%s'\n%s",
                        incorrectCommand,
                        correctUsage
                    ));
        } else {
            return true;
        }

        return false;
    }

    public boolean isValidSubCommand() {
        switch(input[0]) {
            case "add":
                return input.length == 2;
            case "update":
                return input.length == 3 && isValidId(input[1]);

            case "delete":
            case "mark-in-progress":
            case "mark-done":
                return input.length == 2 && isValidId(input[1]);

            case "list":
                if (input.length == 1) {
                    return true;
                } else if (input.length == 2) {
                    return input[1] != null && validListCmdCmdArgs.contains(input[1]);
                }
        }
        return false;
    }

    public boolean isValidInt(String argument) {
        Integer idToUpdate = null;
        try {
            idToUpdate = Integer.valueOf(argument);
        } catch (NumberFormatException nfe) {
            System.err.println(
                    String.format(
                        incorrectCmdUsageFmt,
                        input[0],
                        argument,
                        correctUsage
                    ));
        }
        return idToUpdate != null;
    }

    public boolean isValidId(String argument) {
        return isValidInt(argument) &&
               Integer.valueOf(argument).intValue() <= 10;
    }
}
