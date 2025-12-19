package edu.grinnell.csc207.lootgenerator;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;

public class Tests {
    @Test
    public void thousandRuns() throws IOException {
        String[] temp = { "" };
        int count = 0;
        // Create my fake input
        String generatedInput = "";
        for (int i = 0; i < 1000; i++) {
            generatedInput += "y\n";
        }

        // After 1000 runs tell the program to stop
        generatedInput += "n\n";
        System.setIn(new ByteArrayInputStream(generatedInput.getBytes()));
        try {
            LootGenerator.main(temp);
            count++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
