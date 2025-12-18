package edu.grinnell.csc207.lootgenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Takes in the affix txt file and gets the data
 */
public class Affix {
    ArrayList<String[]> affixes = new ArrayList<>();

    /**
     * Take in a dataset and then make each monster in the data set a Monster
     * element
     * 
     * @param stringPath String path of thefile
     */
    public Affix(String stringPath) {
        Path path = Path.of(stringPath);

        try {
            String data = Files.readString(path);
            String[] dataList = data.split("\\R");

            // Add each prefix split into its parts
            for (String affix : dataList) {
                affixes.add(affix.split("\t"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return a string list containing the affix, attribute, min, max
     * 
     * @param i integer within size of array
     * @return a string list
     */
    public String[] returnAffix(int i) {
        return affixes.get(i);
    }

    /**
     * Returns the number of elements in the arraylist
     * 
     * @return a non negative integer
     */
    public int returnSize() {
        return affixes.size();
    }

}
