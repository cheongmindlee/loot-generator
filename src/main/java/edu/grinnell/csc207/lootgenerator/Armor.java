package edu.grinnell.csc207.lootgenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

//Creates a Hashmap where armor name is the key and the values are the min and max values
//  of the armor
public class Armor {
  HashMap<String, Integer[]> armorHM;
  public Armor(String pathString){
    //Initalize the armor hashmap
    armorHM = new HashMap<>();
    try{
      //Read the file from the given path
      Path path = Path.of(pathString);
      String data = Files.readString(path);

      //Sort each armor and it's min/max values as single elements in a lsit
      String[] dataList = data.split("\\R");
      
      //Take each element and make it a key value pair of armor(key) and its min-max values(values)
      for(String armor: dataList){
        String[] splitArmor= armor.split("\t");
        int min = Integer.parseInt(splitArmor[1]);
        int max = Integer.parseInt(splitArmor[2]);
        Integer[] minMax = {min, max};
        armorHM.put(splitArmor[0], minMax);
      }

    } catch(IOException e){
      e.printStackTrace();
    }

  }

  /**
   * Returns the min and max ac values of a given armor
   * @param armor string 
   * @return Integer[] containing min, max in that order 
   */
  public Integer[] getArmorMinMax(String armor){
    return armorHM.get(armor);
  }
}
