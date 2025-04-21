package main;

import main.InputParser;


public class TaskTracker {
    public static void main(String args[]) {
        InputParser inputParser = new InputParser(args);
        inputParser.isValidBaseCommand();
        System.out.println(inputParser.isValidSubCommand());
    }
}
