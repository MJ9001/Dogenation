package org.dogecraft;

import org.bukkit.command.CommandSender;

public class commanddoge extends commandmain {
  public commanddoge(CommandSender send, String[] arg) {
    super(send, arg);
  }
  
  public void run() {
    String[] help = { "Current commands:", "/funds - Get your dogecoin funds.", "/wallet - Get your wallet code.", "/walletcopy - Get your wallet code, and click it to copy.", "/senddoge - Send doge to another player.", "/dogebuy - Buy and view dogecoin ranks." };
    sendmessage(this.sender, help);
  }
}
