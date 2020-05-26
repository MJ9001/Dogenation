package org.dogecraft;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class commandfunds extends commandmain {
  public commandfunds(CommandSender send, String[] arg) {
    super(send, arg);
  }
  
  public void run() {
    sendmessage(this.sender, "Running command...");
    sendmessage(this.sender, "You currently have " + ChatColor.YELLOW + Main.dogeapi.PlayerBalance(this.sender.getName()) + ChatColor.GOLD + " dogecoins.");
  }
}
