package src.main;

import src.main.InputParser;


public class TaskTracker {
    public static void main(String args[]) {
        InputParser inputParser = new InputParser(args);
        inputParser.isValidBaseCommand();
        inputParser.isValidSubCommand();
    }
}
