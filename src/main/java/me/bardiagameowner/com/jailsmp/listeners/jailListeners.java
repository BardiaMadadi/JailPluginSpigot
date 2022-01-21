package me.bardiagameowner.com.jailsmp.listeners;

import me.bardiagameowner.com.jailsmp.Jailsmp;
import me.bardiagameowner.com.jailsmp.helper.jailHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.bardiagameowner.com.jailsmp.Jailsmp.FileConf;

public class jailListeners implements Listener {

    private jailHelper helper;


    @EventHandler

    public void OnPlayerJoined(PlayerJoinEvent ev){
        ev.setJoinMessage(ChatColor.YELLOW + ev.getPlayer().getDisplayName() + ChatColor.AQUA + " Be Server Khosh Oomadi");

        Player target = ev.getPlayer();

        if(Jailsmp.IsInJail(target.getUniqueId())){
            doJail(target);
        }





    }



    public void doJail(Player target) {



        Location jailLocation;
        jailLocation = new Location(Bukkit.getWorld("world"),FileConf.getInt("jail-location-x"),FileConf.getInt("jail-location-y"),FileConf.getInt("jail-location-z"));
        jailHelper.bossbarAddJail(target," YOU ARE IN JAIL ", BarColor.PURPLE);
        target.teleport(jailLocation);


    }


}
