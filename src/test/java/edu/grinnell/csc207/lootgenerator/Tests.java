package edu.grinnell.csc207.lootgenerator;

import java.util.Random;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class Tests {

    /**
     * Generates a random monster from the dataset
     * 
     * @param monsters a MonsterList object
     * @return a Monster object
     */
    public static Monster pickMonster(MonstersList monsters) {
        Random rand = new Random();
        int size = monsters.returnMonsters().size();
        int randomInt = rand.nextInt(size);

        return monsters.returnMonster(randomInt);
    }

    /**
     * Randomly selects an item from the treasureclass the corresponds to a monster,
     * and returns it
     * 
     * @param treasureHM    A TreasureClassHashMap object
     * @param treasureClass a String representation of a treasure class
     * @return String representation of baseItem randomly selected
     */
    public static String generateBaseItem(TreasureClassHashMap treasureHM, String treasureClass) {
        Random rand = new Random();
        int randomInt = rand.nextInt(3);
        String[] items = treasureHM.get(treasureClass);

        String baseItem = items[randomInt];

        // Now check to see if this base item is itself another treasure class. If it
        // isn't generate another item
        if (treasureHM.contains(baseItem)) {
            return generateBaseItem(treasureHM, baseItem);
        } else {
            return baseItem;
        }

    }

    /**
     * Returns a random integer between the min and max values of a piece of armor
     * from
     * the armor class
     * 
     * @param armorHM an Armor object
     * @param armor   String reprsentation of armor
     * @return a positive integer
     */
    public static int generateBaseStat(Armor armorHM, String armor) {
        // Take in the min and max values of the given armor
        Integer[] minMax = armorHM.returnArmorMinMax(armor);
        int min = minMax[0];
        int max = minMax[1];

        // Generate a random stat between min and max
        Random rand = new Random();
        int randomInt = rand.nextInt(min, max) + 1;
        return randomInt;
    }

    /**
     * Returns the list holding the prefix, attribute, and random int between the
     * attributes min and max
     * 
     * @param prefix a prefix object
     * @return a non empty string list
     */
    public static String[] generatePrefix(Prefix prefix) {

        // Generate a random attribute
        Random rand = new Random();
        int randomInt = rand.nextInt(prefix.returnSize());

        // take in the data of the prefix
        String[] tempPrefix = prefix.returnPrefix(randomInt);
        int min = Integer.parseInt(tempPrefix[2]);
        int max = Integer.parseInt(tempPrefix[3]);

        // Generate a random stat value between min and max
        String randomStat = String.valueOf(min + rand.nextInt(max - min + 1));

        String[] returnPrefix = { tempPrefix[0], tempPrefix[1], randomStat };

        return returnPrefix;
    }

    /**
     * Returns the list holding the affix, attribute, and random int between the
     * attributes min and max
     * 
     * @param prefix an affix object
     * @return a non empty string list
     */
    public static String[] generateAffix(Affix affix) {

        // Generate a random attribute
        Random rand = new Random();
        int randomInt = rand.nextInt(affix.returnSize());

        // take in the data of the affix
        String[] tempAffix = affix.returnAffix(randomInt);
        int min = Integer.parseInt(tempAffix[2]);
        int max = Integer.parseInt(tempAffix[3]);

        // Generate a random stat value between min and max
        String randomStat = String.valueOf(min + rand.nextInt(max - min + 1));

        String[] returnAffix = { tempAffix[0], tempAffix[1], randomStat };

        return returnAffix;
    }

    @Test
    public void thousandRuns() {

        // Get the data set and put inside a MonstersList object
        MonstersList monsters = new MonstersList("/Users/david/csc207/loot-generator/data/large/monstats.txt");

        // Get data set of every treasure class and put them into TreasureClassHashMap
        // object
        TreasureClassHashMap treasureHM = new TreasureClassHashMap(
                "/Users/david/csc207/loot-generator/data/large/TreasureClassEx.txt");

        // Get data set of every armor item into am Armor object
        Armor armorHM = new Armor("/Users/david/csc207/loot-generator/data/large/armor.txt");

        // Get data set of every prefix into an Prefix object
        Prefix prefix = new Prefix("/Users/david/csc207/loot-generator/data/large/MagicPrefix.txt");

        // Get data set of every affix into an Affix object
        Affix affix = new Affix("/Users/david/csc207/loot-generator/data/large/MagicSuffix.txt");

        // Initialize scanner for user input
        Scanner input = new Scanner(System.in);

        int count = 0;
        // Program loop, keep running until user responds with n or N
        while (count < 1000) {
            // Generate a monster to fight
            Monster monster = pickMonster(monsters);
            String monsterName = monster.returnMonsterClass();
            String monsterClass = monster.returnTreasure();

            // generate a base item
            String baseItem = generateBaseItem(treasureHM, monsterClass);

            // generate stat of base item
            int itemStat = generateBaseStat(armorHM, baseItem);

            // Generate a random prefix and affix
            String prefixName = "";
            String prefixAttribute = "";
            String prefixStat = "";
            Random rand = new Random();
            int createPrefix = rand.nextInt(2);
            if (createPrefix == 0) {
                String[] prefixData = generatePrefix(prefix);
                prefixName = prefixData[0] + " ";
                prefixAttribute = prefixData[1];
                prefixStat = prefixData[2];
            }

            String affixName = "";
            String affixAttribute = "";
            String affixStat = "";
            int createAffix = rand.nextInt(2);
            if (createAffix == 0) {
                String[] affixData = generateAffix(affix);
                affixName = " " + affixData[0];
                affixAttribute = affixData[1];
                affixStat = affixData[2];
            }

            // Give output to user
            System.out.println("Fighting " + monsterName);
            System.out.println("You have slain " + monsterName + "!");
            System.out.println(monsterName + " dropped:\n");
            System.out.println(prefixName + baseItem + affixName);
            System.out.println("Defense: " + itemStat);

            // Output the attribute values if prefix and affix exist
            if (createPrefix == 0) {
                System.out.println(prefixStat + " " + prefixAttribute);
            }

            if (createAffix == 0) {
                System.out.println(affixStat + " " + affixAttribute);
            }
            // Formatting newline
            System.out.println();
            count++;

        }
    }
}
