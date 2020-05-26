package org.dogecraft;

import code.husky.mysql.MySQL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.net.ssl.HttpsURLConnection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class DogeAPI extends JavaPlugin {
  private String key;
  
  private long timestamp = 0L;
  
  private float dogeperusd = 0.0F;
  
  public Statement statement;
  
  DogeAPI(String apikey) {
    this.key = apikey;
    connect();
    checkdatabase();
  }
  
  private void checkdatabase() {
    try {
      System.out.println(this.statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (name VARCHAR(16), wallet VARCHAR(255), balance int);"));
    } catch (SQLException e) {
      e.printStackTrace();
    } 
  }
  
  private void checkconnection() {
    connect();
  }
  
  private void connect() {
    try {
      String[] info = Main.config.getSQLINFO();
      MySQL MySQL = new MySQL((Plugin)this, info[0], info[1], info[2], info[3], info[4]);
      Connection c = MySQL.openConnection();
      this.statement = c.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    } 
  }
  
  public String senddoge(String fromplayer, String toplayer, int amt) {
    checkconnection();
    if (amt < 1)
      return "Bad shibe, send at least one dogecoin."; 
    int balance = PlayerBalance(fromplayer);
    if (amt > balance)
      return "Your trying to send more dogecoin than you have. Who are ya trying to fool?"; 
    try {
      ResultSet res = this.statement.executeQuery("SELECT * FROM users WHERE name = '" + toplayer + "';");
      if (!res.next())
        return "Player either doesn't exist, or hasn't made a wallet yet. Ask them to do /wallet."; 
      GiveDoge(fromplayer, -amt);
      GiveDoge(toplayer, amt);
      commandmain.sendmessage((CommandSender)Bukkit.getServer().getPlayer(toplayer), "Player " + ChatColor.YELLOW + fromplayer + ChatColor.GOLD + " has sent you " + ChatColor.YELLOW + amt + ChatColor.GOLD + " dogecoins!");
      return "You have succesfuly sent " + ChatColor.YELLOW + amt + ChatColor.GOLD + " dogecoins to " + ChatColor.YELLOW + toplayer + ChatColor.GOLD + ".";
    } catch (SQLException e) {
      e.printStackTrace();
      return "What have you done?!?!?!?";
    } 
  }
  
  public String GetWallet(String label) {
    checkconnection();
    try {
      ResultSet res = this.statement.executeQuery("SELECT * FROM users WHERE name = '" + label + "';");
      if (!res.next()) {
        String wal = web("&a=get_new_address&address_label=" + label, true);
        NewPlayer(label, wal.replace("\"", ""));
        return wal.replace("\"", "");
      } 
      return res.getString("wallet");
    } catch (SQLException e) {
      e.printStackTrace();
      return "";
    } 
  }
  
  public void GiveDoge(String label, int amt) {
    checkconnection();
    try {
      this.statement.executeUpdate("Update users SET balance = balance + " + amt + " WHERE name = '" + label + "';");
    } catch (SQLException e) {
      e.printStackTrace();
    } 
  }
  
  private void NewPlayer(String name, String wallet) {
    try {
      this.statement.executeUpdate("INSERT INTO users VALUES ('" + name + "', '" + wallet + "', '0')");
    } catch (SQLException e) {
      e.printStackTrace();
    } 
  }
  
  public int PlayerBalance(String label) {
    checkconnection();
    try {
      ResultSet res = this.statement.executeQuery("SELECT * FROM users WHERE name = '" + label + "';");
      if (!res.next())
        GetWallet(label); 
      String bal = web("&a=get_address_received&payment_address=" + res.getString("wallet"), true);
      return Integer.parseInt(bal) + res.getInt("balance");
    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    } 
  }
  
  public int USDtoDoge(int dollars) {
    if (System.currentTimeMillis() - this.timestamp > 5400000L) {
      this.dogeperusd = Float.valueOf(web("a=get_current_price&convert_to=USD&amount_doge=1", false).replace("\"", "")).floatValue();
      this.timestamp = System.currentTimeMillis();
    } 
    return (int)(dollars / this.dogeperusd);
  }
  
  private String web(String html, boolean usekey) {
    try {
      URL url = new URL("https://www.dogeapi.com/wow/?" + (usekey ? ("api_key=" + this.key) : "") + html);
      try {
        HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String s = br.readLine();
        br.close();
        return s;
      } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Website may be down!");
      } 
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } 
    return null;
  }
  
  public String buy(String label, int cost) {
    checkconnection();
    int balance = PlayerBalance(label);
    if (cost > balance)
      return "You do not have enough dogecoin!"; 
    try {
      ResultSet res = this.statement.executeQuery("SELECT * FROM users WHERE name = '" + label + "';");
      res.next();
      GiveDoge(label, -cost);
      return "You have succesfuly bought your item!";
    } catch (SQLException e) {
      e.printStackTrace();
      return "What have you done?!?!?!?";
    } 
  }
}
