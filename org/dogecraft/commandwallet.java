package org.dogecraft;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class commandwallet extends commandmain {
  public commandwallet(CommandSender send, String[] arg) {
    super(send, arg);
  }
  
  public void run() {
    String[] msg = { ChatColor.GOLD + "Your dogecoin address is: ", ChatColor.YELLOW + Main.dogeapi.GetWallet(this.sender.getName()), ChatColor.RED + "(Warning you cannot withdraw dogecoins)" };
    sendmessage(this.sender, msg);
  }
}
