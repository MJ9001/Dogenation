package org.dogecraft;

import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class dogeifier implements Listener {
  @EventHandler(priority = EventPriority.NORMAL)
  public void onVotifierEvent(VotifierEvent event) {
    Vote vote = event.getVote();
    String user = vote.getUsername();
    Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), Main.config.getVoteCommand().replace("{player}", user));
    Bukkit.broadcastMessage(String.valueOf(commandmain.logo) + Main.config.getVoteBroadcast().replace("{player}", user).replace("&", "§"));
  }
}
