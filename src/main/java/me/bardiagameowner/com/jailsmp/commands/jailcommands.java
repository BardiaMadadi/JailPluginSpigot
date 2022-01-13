package me.bardiagameowner.com.jailsmp.commands;


import me.bardiagameowner.com.jailsmp.Jailsmp;
import me.bardiagameowner.com.jailsmp.helper.jailHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Timestamp;

import static me.bardiagameowner.com.jailsmp.Jailsmp.FileConf;

public class jailcommands implements CommandExecutor {

    jailHelper helper;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){

            if(sender.isOp()){

                if(args.length >= 1){
                    Player target = Bukkit.getPlayer(args[0]);

                    target.sendMessage(
                            ChatColor.RED +
                                    " Moteasefam , Shoma be dalil :  " +
                                    ChatColor.GREEN + args[1] +
                                    ChatColor.RED + " Be modate : " +
                                    ChatColor.GREEN + args[2] +
                                    ChatColor.RED + " Rooz Be  Zendan miravid");

                    doJail(target , Float.parseFloat(args[2]));


                }else {
                    ((Player) sender).chat(ChatColor.RED + "Set Yout Target : /<Commands> <target>" + args.length);

                }

            }else {

                ((Player) sender).chat(ChatColor.RED + "You Cant Excute this command");

            }

        }

        return true;
    }


    public void doJail(Player target , float time) {

        long timestamp;
        timestamp = System.currentTimeMillis() + ((long) time * 20 * 60 * 1000);


        Location jailLocation;
        jailLocation = new Location(Bukkit.getWorld("world"),FileConf.getInt("jail-location-x"),FileConf.getInt("jail-location-y"),FileConf.getInt("jail-location-z"));

        target.teleport(jailLocation);
        Jailsmp.jailAddPlayer(target.getUniqueId(),timestamp);
        Jailsmp.jailFileDataSET();


    }




}
