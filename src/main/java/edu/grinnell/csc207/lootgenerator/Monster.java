package edu.grinnell.csc207.lootgenerator;

/**
 * This class should parse each monster and holds the class, level, type and
 * treasure class of a monster
 */
public class Monster {

    // Initialize
    String monsterClass;
    int level;
    String type;
    String treasure;

    /**
     * Takes a monster name and sets all its fields
     * 
     * @param monster String name of the monster
     */
    public Monster(String monster) {
        String[] parsedData = monster.split("\t");
        this.monsterClass = parsedData[0];
        this.type = parsedData[1];
        this.level = Integer.parseInt(parsedData[2]);
        this.treasure = parsedData[3];
    }

    /**
     * Returns the monsters class
     * 
     * @return monsterClass
     */
    public String returnMonsterClass() {
        return monsterClass;
    }

    /**
     * Rretyrns the level of the monster
     * 
     * @return monster level
     */
    public int returnLevel() {
        return level;
    }

    /**
     * Returns the monsters type
     * 
     * @return monster type
     */
    public String returnType() {
        return type;
    }

    /**
     * Returns the treasure
     * 
     * @return monster's treasure class
     */
    public String returnTreasure() {
        return treasure;
    }

}
