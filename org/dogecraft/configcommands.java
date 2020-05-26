package org.dogecraft;

public class configcommands {
  String nme;
  
  String cmd;
  
  String des;
  
  int coin;
  
  public configcommands(String name, String description, String command, int coins) {
    this.nme = name;
    this.cmd = command;
    this.coin = coins;
    this.des = description;
  }
  
  public String getName() {
    return this.nme;
  }
  
  public String getDescription() {
    return this.des;
  }
  
  public String getCommand() {
    return this.cmd;
  }
  
  public int getCoins() {
    return this.coin;
  }
}
