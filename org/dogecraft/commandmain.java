package org.dogecraft;

import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class commandmain implements Runnable {
  public CommandSender sender;
  
  public String[] args;
  
  public static String logo = ChatColor.YELLOW + "[" + ChatColor.WHITE + "Dogenation" + ChatColor.YELLOW + "] ";
  
  public commandmain(CommandSender send, String[] arg) {
    this.args = arg;
    this.sender = send;
    Thread t = new Thread(this, "dogenation" + (new Random()).nextInt());
    t.start();
  }
  
  public void run() {}
  
  public static void sendmessage(CommandSender sender, String text) {
    sender.sendMessage(String.valueOf(logo) + ChatColor.GOLD + text);
  }
  
  public static void sendmessage(CommandSender sender, String[] text) {
    for (int i = 0; text.length > i; i++)
      sender.sendMessage(String.valueOf((i == 0) ? logo : "") + ChatColor.GOLD + text[i]); 
  }
}
