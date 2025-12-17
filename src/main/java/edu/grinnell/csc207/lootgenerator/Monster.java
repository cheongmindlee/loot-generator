package edu.grinnell.csc207.lootgenerator;

//This class should parse each monster and holds the class, level, type and treasure 
// class of a monster
public class Monster {

  //Initialize 
  String monsterClass;
  int level;
  String type;
  String treasure;

  public Monster(String monster){
    String[] parsedData = monster.split("\t");
    this.monsterClass = parsedData[0];
    this.type = parsedData[1];
    this.level = Integer.parseInt(parsedData[2]);
    this.treasure = parsedData[3];
  }

  /**
   * 
   * @return monsterClass
   */
  public String getMonsterClass(){
    return monsterClass;
  }

  /**
   * 
   * @return monster level
   */
  public int getLevel(){
    return level;
  }

  /**
   * 
   * @return monster type
   */
  public String getType(){
    return type;
  }

  /**
   * 
   * @return monster's treasure class
   */
  public String getTreasure(){
    return treasure;
  }
  
}
