package org.dogecraft;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class commanddogebuy extends commandmain {
  public commanddogebuy(CommandSender send, String[] arg) {
    super(send, arg);
  }
  
  public void run() {
    if (this.args.length > 0) {
      boolean worked = false;
      for (configcommands c : Main.config.getCommands()) {
        if (c.getName().equalsIgnoreCase(this.args[0])) {
          sendmessage(this.sender, "Running command...");
          worked = true;
          try {
            String rsp = Main.dogeapi.buy(this.sender.getName(), Main.dogeapi.USDtoDoge(c.getCoins()));
            if (rsp.equals("You have succesfuly bought your item!")) {
              Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), c.getCommand().replace("{player}", this.sender.getName()));
              Bukkit.broadcastMessage(String.valueOf(commandmain.logo) + ChatColor.YELLOW + this.sender.getName() + ChatColor.GOLD + " has bought the item " + 
                  ChatColor.YELLOW + c.getName() + ChatColor.GOLD + " for " + ChatColor.YELLOW + Main.dogeapi.USDtoDoge(c.getCoins()) + ChatColor.GOLD + " dogecoins!");
            } 
            sendmessage(this.sender, rsp);
          } catch (Exception ex) {
            sendmessage(this.sender, "The command syntax is: /dogebuy (rank)");
          } 
        } 
      } 
      if (!worked)
        sendmessage(this.sender, "Invalid item!"); 
    } else {
      this.sender.sendMessage(ChatColor.RED + "(Warning prices fluctuate)");
      boolean did = false;
      for (configcommands c : Main.config.getCommands()) {
        if (did)
          this.sender.sendMessage(""); 
        this.sender.sendMessage(ChatColor.GOLD + "Rank: " + ChatColor.YELLOW + c.getName());
        this.sender.sendMessage(ChatColor.GOLD + "Description: " + ChatColor.YELLOW + c.getDescription());
        this.sender.sendMessage(ChatColor.GOLD + "Costs: " + ChatColor.YELLOW + Main.dogeapi.USDtoDoge(c.coin) + " Dogecoin");
        did = true;
      } 
    } 
  }
}
