package edu.grinnell.csc207.lootgenerator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

// Take monsters dataset and make and arraylist with each monster as an element
public class MonstersList {
  ArrayList<Monster> Monsters = new ArrayList<>();

  //Take in a dataset and then make each monster in the data set a Monster element
  public MonstersList(String stringPath){
    Path path = Path.of(stringPath);

    try{
      String data = Files.readString(path);
      String[] dataList = data.split("\\R");

      for(String monster: dataList){
        Monsters.add(new Monster(monster));
      }

    } catch(IOException e){
      e.printStackTrace();
    }
  }

  /**
   * 
   * @return an arraylist containing every monster in the dataset as a Monster object
   */
  public ArrayList<Monster> getMonsters(){
    return Monsters;
  }

  /**
   * Returns a monster object inside Monsters arraylist at the given index
   * @param i the index of the monster object
   * @return a Monster object
   */
  public Monster getMonster(int i){
    return Monsters.get(i);
  }
}
