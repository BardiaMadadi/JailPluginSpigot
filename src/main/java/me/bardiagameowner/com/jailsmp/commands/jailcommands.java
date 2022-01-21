package me.bardiagameowner.com.jailsmp.commands;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.bardiagameowner.com.jailsmp.helper.jailHelper.doJail;

public class jailcommands implements CommandExecutor {


    @Override
    // ON PLAYER COMMAND :
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // IF SENDER WAS PLAYER ( NOT CONSOLE )

        if(sender instanceof Player){

            // IF PLAYER IS OP :

            if(sender.isOp()){

                // IF ARG AREVALID :
                if(args.length >= 1){
                    // GET PLAYER :
                    Player target = Bukkit.getPlayer(args[0]);

                    assert target != null;

                    // SEND MESSAGE TO JAILED PLAYER :
                    target.sendMessage(
                            ChatColor.RED +
                                    " Moteasefam , Shoma be dalil :  " +
                                    ChatColor.GREEN + args[1] +
                                    ChatColor.RED + " Be modate : " +
                                    ChatColor.GREEN + args[2] +
                                    ChatColor.RED + " Rooz Be  Zendan miravid");

                    // DO JAIL :

                    doJail(target , Float.parseFloat(args[2]));

                }else {
                    ((Player) sender).chat(ChatColor.RED + "Set Your Target : /<Commands> <target>" + args.length);

                }

            }else {
                ((Player) sender).chat(ChatColor.RED + "You Cant Excute this command");
            }

        }
        return true;
    }

}
