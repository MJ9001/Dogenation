package org.dogecraft;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
  public static DogeAPI dogeapi;
  
  public static configapi config;
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("funds")) {
    
    } else if (cmd.getName().equalsIgnoreCase("wallet")) {
    
    } else if (cmd.getName().equalsIgnoreCase("walletcopy")) {
    
    } else if (cmd.getName().equalsIgnoreCase("senddoge")) {
    
    } else if (cmd.getName().equalsIgnoreCase("dogebuy")) {
    
    } else if (cmd.getName().equalsIgnoreCase("doge")) {
    
    } else if (cmd.getName().equalsIgnoreCase("vote")) {
      String[] message = { ChatColor.GOLD + "Hello fellow shibe. If you are enjoying this server we would", ChatColor.GOLD + "love for you to vote. This will help us grow the community,", ChatColor.GOLD + "and for each server you'll get" + ChatColor.YELLOW + " 1,000 dogepoints!", ChatColor.RED + "(Not withdrawable)", ChatColor.GOLD + "-------------", ChatColor.GOLD + "Click the link:" + ChatColor.YELLOW + " http://dogecraft.org/vote", ChatColor.GOLD + "-------------" };
      sender.sendMessage(message);
    } 
    return true;
  }
  
  public void onEnable() {
    getLogger().info("Dogenation Dogeing!");
    config = new configapi((Plugin)this);
    dogeapi = new DogeAPI(config.getAPIKEY());
    try {
      getServer().getPluginManager().registerEvents(new dogeifier(), (Plugin)this);
    } catch (Exception exception) {}
  }
}
