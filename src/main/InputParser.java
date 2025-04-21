package main;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.lang.String;


class InputParser {
    private final String correctUsage = "Correct Usage:\n" +
        "tasktracker <command> <command-arg1> <command-arg2> ...\n" +
        "Use 'tasktracker help' for general help or" + 
        "'tasktracker <command> help' for command-specific help";
    private final Set<String> validBaseArguments = new HashSet<>(
        List.of(
            "add",
            "update",
            "delete",
            "mark-in-progress",
            "mark-done",
            "list"
        ));
    private String[] input;


    public InputParser(String[] input) {
        this.input = input;
    }


    public void runCommand() {
        if (input.length == 0) {
            throw new IllegalArgumentException(
                    String.format(
                        "Incorrect usage: No Arguments Found\n%s",
                        correctUsage
                    ));
        } else if (!isValidArgument(input[0])) {
            String incorrectCommand = input[0].length() > 10 ? input[0].substring(0, 10) + "..." : input[0];
            throw new IllegalArgumentException(
                    String.format(
                        "Incorrect usage: Incorrect Argument '%s'\n%s",
                        incorrectCommand,
                        correctUsage
                    ));
        }

    }

    public boolean isValidArgument(String command) {
        return command != null && validBaseArguments.contains(command);
        
    }
    
    public String getValidArguments() {
        return "";
    }
    
}
