package src.lib.test;

import src.lib.test.unit.TaskTests;

public class RunTests {
    public static void main(String[] args) {
         TaskTests taskTests = new TaskTests();

         taskTests.runAllTests();

         System.out.println("Tests have all passed!");
    }
}
