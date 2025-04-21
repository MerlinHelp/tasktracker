package main;

import main.InputParser;


class TaskTracker {
    public static void main(String args[]) {
        InputParser inputParser = new InputParser(args);
        inputParser.runCommand();
        
    }
}
