package org.dogecraft;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class commandwalletcopy extends commandmain {
  public commandwalletcopy(CommandSender send, String[] arg) {
    super(send, arg);
  }
  
  public void run() {
    String[] msg = { ChatColor.GOLD + "Your dogecoin address is: ", ChatColor.YELLOW + "http://" + Main.dogeapi.GetWallet(this.sender.getName()), "Click the address, press copy, and remove the 'http://' from the wallet address.", ChatColor.RED + "(Warning you cannot withdraw dogecoins)" };
    sendmessage(this.sender, msg);
  }
}
