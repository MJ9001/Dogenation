package org.dogecraft;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class configapi extends JavaPlugin {
  public Plugin plugin;
  
  public Configuration config;
  
  public configapi(Plugin p) {
    this.plugin = p;
    File file = new File("plugins" + File.separator + "Dogenation" + File.separator + "config.yml");
    file.mkdir();
    try {
      this.config = (Configuration)this.plugin.getConfig();
    } catch (Exception ex) {
      ex.printStackTrace();
    } 
  }
  
  public List<configcommands> getCommands() {
    ArrayList<configcommands> cmd = new ArrayList<>();
    List<String> lst = this.config.getStringList("commands");
    for (int i = 0; lst.size() > i; i++) {
      List<String> lst1 = this.config.getStringList(lst.get(i));
      cmd.add(new configcommands(lst1.get(0), lst1.get(1), lst1.get(2), Integer.parseInt(lst1.get(3))));
    } 
    return cmd;
  }
  
  public String getAPIKEY() {
    return this.config.getString("apikey");
  }
  
  public String getVoteCommand() {
    return this.config.getString("vote-command");
  }
  
  public String getVoteBroadcast() {
    return this.config.getString("vote-broadcast");
  }
  
  public String[] getSQLINFO() {
    String[] itm = { this.config.getString("ip"), this.config.getString("port"), this.config.getString("database"), this.config.getString("username"), this.config.getString("pass") };
    return itm;
  }
}
