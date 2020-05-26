package org.dogecraft;

import org.bukkit.command.CommandSender;

public class commandsenddoge extends commandmain {
  public commandsenddoge(CommandSender send, String[] arg) {
    super(send, arg);
  }
  
  public void run() {
    sendmessage(this.sender, "Running command...");
    if (this.args.length < 2) {
      sendmessage(this.sender, "The command syntax is: /senddoge (player) (ammount)");
    } else {
      sendmessage(this.sender, Main.dogeapi.senddoge(this.sender.getName(), this.args[0], Integer.parseInt(this.args[1])));
    } 
  }
}
