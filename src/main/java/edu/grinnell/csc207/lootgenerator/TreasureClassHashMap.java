package edu.grinnell.csc207.lootgenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Takes in the treasure classes data
 */
public class TreasureClassHashMap {
    // Hashmap that stores each treasure class, and its elements
    HashMap<String, String[]> treasureHM;

    /**
     * Creates a hashmap of all the treasure class elements
     * 
     * @param stringPath the string representation of the path to the treasureclass
     *                   txt file
     */
    public TreasureClassHashMap(String stringPath) {
        treasureHM = new HashMap<>();

        Path path = Path.of(stringPath);
        try {
            // Take the file data and hash each element into a hashmap
            String data = Files.readString(path);
            String[] dataList = data.split("\\R");

            // Put every treasure into a hashmap, where its values are the items the
            // treasure class calls
            for (String treasure : dataList) {
                String[] splitTreasureString = treasure.split("\t");
                treasureHM.put(splitTreasureString[0], Arrays.copyOfRange(splitTreasureString, 1,
                        4));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a hashmap containing all the tresures and items
     * 
     * @return a hashmap containg all of the treasures and their associated items
     */
    public HashMap<String, String[]> returnTreasureHM() {
        return treasureHM;
    }

    /**
     * Returns the items that are associated with the treasure class
     * 
     * @param treasureClass the value of a key
     * @return the values associated with the key
     */
    public String[] get(String treasureClass) {
        return treasureHM.get(treasureClass);
    }

    /**
     * Returns true if the key exists in the hashmap
     * 
     * @param treasureClass the value of a key
     * @return true if the key exists false if it does not exist
     */
    public boolean contains(String treasureClass) {
        return treasureHM.containsKey(treasureClass);
    }

}
