package me.bardiagameowner.com.jailsmp.listeners;

import me.bardiagameowner.com.jailsmp.helper.jailHelper;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.bardiagameowner.com.jailsmp.helper.jailHelper.doJail;

public class jailListeners implements Listener {


    @EventHandler

    // ON PLAYER JOINED GAME ;
    public void OnPlayerJoined(PlayerJoinEvent ev){

        // SET NEW WELCOME MESSAGE

        ev.setJoinMessage(ChatColor.YELLOW + ev.getPlayer().getDisplayName() + ChatColor.AQUA + " Be Server Khosh Oomadi");

        // GET PLAYER :
        Player target = ev.getPlayer();

        //IF PLAYER WAS IN JAIL :

        if(jailHelper.IsInJail(target.getUniqueId())){
            doJail(target,0);
        }





    }






}
