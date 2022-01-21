package me.bardiagameowner.com.jailsmp.commands;


import me.bardiagameowner.com.jailsmp.Jailsmp;
import me.bardiagameowner.com.jailsmp.helper.jailHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.bardiagameowner.com.jailsmp.Jailsmp.FileConf;

public class jailcommands implements CommandExecutor {

    jailHelper helper;
    Jailsmp jailsmp;
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
        float plusTime = time * 1200000;
        timestamp = (long) (System.currentTimeMillis() + plusTime);


        Location jailLocation;
        jailLocation = new Location(Bukkit.getWorld("world"),FileConf.getInt("jail-location-x"),FileConf.getInt("jail-location-y"),FileConf.getInt("jail-location-z"));

        target.teleport(jailLocation);
        Jailsmp.jailAddPlayer(target.getUniqueId(),timestamp);

        jailHelper.bossbarAddJail(target,"YOU ARE IN JAIL " + time + " DAYS", BarColor.PURPLE);

        Jailsmp.jailFileDataSET();






    }





}
