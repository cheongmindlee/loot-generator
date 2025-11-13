package edu.grinnell.csc207.lootgenerator;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class LootGenerator {
    /** The path to the dataset (either the small or large set). */
    private static final String DATA_SET = "data/small";
    
    public static void main(String[] args) throws IOException{
        System.out.println("This program kills monsters and generates loot!");

        //Get the data set and sort each monster into an index inside MonstersList class
        MonstersList monsters = new MonstersList("/Users/david/csc207/loot-generator/data/large/monstats.txt");
    
        //Delete at the end
        // for(String monster: monsters.getMonsters()){
        //     System.out.println(monster);
        // }

        //Delete at end
        // Make every element an Monser 
        // test choose a random monster and get its name, value, level and treasure class
        //System.out.println(monsters.getMonsters().get(5).getLevel());

        TreasureClassHashMap treasureHM = 
            new TreasureClassHashMap("/Users/david/csc207/loot-generator/data/large/TreasureClassEx.txt");
        HashMap<String, String[]> checkHM = treasureHM.getTreasureHM();
        String[] check = checkHM.get("armo30");
        for(String item: check){
            System.out.println(item);
        }


        // TOOD: Implement me!
    }
}
