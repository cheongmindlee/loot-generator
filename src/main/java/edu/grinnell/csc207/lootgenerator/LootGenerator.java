package edu.grinnell.csc207.lootgenerator;


import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class LootGenerator {
    /** The path to the dataset (either the small or large set). */
    private static final String DATA_SET = "data/small";
    
    public static void main(String[] args) throws IOException{
        System.out.println("This program kills monsters and generates loot!");


        //Get the data set and put inside a MonstersList object
        MonstersList monsters = new MonstersList("/Users/david/csc207/loot-generator/data/small/monstats.txt");

        //Get data set of every treasure class and put them into TreasureClassHashMap object
        TreasureClassHashMap treasureHM = 
            new TreasureClassHashMap("/Users/david/csc207/loot-generator/data/small/TreasureClassEx.txt");
    
        //Get data set of every armor item into am Armor object
        Armor armorHM = new Armor("/Users/david/csc207/loot-generator/data/small/armor.txt");
        //Delete at the end
        // for(String monster: monsters.getMonsters()){
        //     System.out.println(monster);
        // }

        //Delete at end
        // Make every element an Monser 
        // test choose a random monster and get its name, value, level and treasure class
        //System.out.println(monsters.getMonsters().get(5).getLevel());

        //Delete at end
        // TreasureClassHashMap treasureHM = 
        //     new TreasureClassHashMap("/Users/david/csc207/loot-generator/data/large/TreasureClassEx.txt");
        // HashMap<String, String[]> checkHM = treasureHM.getTreasureHM();
        // String[] check = checkHM.get("armo30");
        // for(String item: check){
        //     System.out.println(item);
        // }

        //Initialize scanner for user input
        Scanner input = new Scanner(System.in);

        //Set game state to true until user responds with n or N
        boolean gameState = true;
        //Program loop, keep running until user responds with n or N
        while(gameState){
            //Generate a monster to fight
            Monster monster = pickMonster(monsters);
            String monsterName = monster.getMonsterClass();
            String monsterClass = monster.getTreasure();

            //generate a base item
            String baseItem = generateBaseItem(treasureHM, monsterClass);

            //generate stat of base item
            int itemStat = generateBaseStat(armorHM, baseItem);
            //Give output to user
            System.out.println("Fighting " + monsterName);
            System.out.println("You have slain " + monsterName + "!");
            System.out.println(monsterName + " dropped:\n");
            System.out.println(baseItem);
            System.out.println("Defense: " + itemStat);

            //Once one round has been complete ask user if they wish to play again
            while(true){
                System.out.println("Fight again [y\\n]?");
                
                //Take in user input and end the game if they input n, continue if Y, for incorrect input keep prompting 
                //  for a response
                String response = input.nextLine();

                if(response.equals("y") || response.equals("Y")){
                    break;
                } else if(response.equals("n") || response.equals("N")){
                    gameState = false;
                    break;
                } else{
                    continue;
                }
            }

        }
        // TOOD: Implement me!
    }

    /**
     * Generates a random monster from the dataset
     * @param monsters a MonsterList object
     * @return a Monster object
     */
    public static Monster pickMonster(MonstersList monsters){
        Random rand = new Random();
        int size = monsters.getMonsters().size();
        int randomInt = rand.nextInt(size);

        return monsters.getMonster(randomInt);
    }


    /**
     * Randomly selects an item from the treasureclass the corresponds to a monster, and returns it
     * @param treasureHM A TreasureClassHashMap object
     * @param treasureClass a String representation of a treasure class
     * @return String representation of baseItem randomly selected
     */
    public static String generateBaseItem(TreasureClassHashMap treasureHM, String treasureClass){
        Random rand = new Random();
        int randomInt = rand.nextInt(3);
        String[] items = treasureHM.get(treasureClass);

        String baseItem = items[randomInt];

        //Now check to see if this base item is itself another treasure class. If it isn't generate another item
        if(treasureHM.contains(baseItem)){
            return generateBaseItem(treasureHM, baseItem);
        } else {
            return baseItem;
        }
        
    }

    /**
     * Returns a random integer between the min and max values of a piece of armor from
     *  the armor class 
     * @param armorHM an Armor object
     * @param armor String reprsentation of armor
     * @return a positive integer
     */
    public static int generateBaseStat(Armor armorHM, String armor){
        //Take in the min and max values of the given armor
        Integer[] minMax = armorHM.getArmorMinMax(armor);
        int min = minMax[0];
        int max = minMax[1];

        //Generate a random stat between min and max
        Random rand = new Random();
        int randomInt = rand.nextInt(min, max) + 1;
        return randomInt;
    }
}
