package me.bardiagameowner.com.jailsmp.helper;

import me.bardiagameowner.com.jailsmp.Jailsmp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.boss.BarColor;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import static me.bardiagameowner.com.jailsmp.Jailsmp.FileConf;
import static me.bardiagameowner.com.jailsmp.Jailsmp.file;
import static me.bardiagameowner.com.jailsmp.helper.jailBossbarHelper.bossbarAddJail;
import static me.bardiagameowner.com.jailsmp.helper.jailBossbarHelper.removeBossbar;

public class jailHelper {

    // VARIABLES :
    Jailsmp jailsmp;


    // CHECK IS JAIL FINISHED :
    public static void IsJailFinish(){

        for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {



            UUID offlinePlayerUUID = player.getUniqueId();

            long jailPlayerTimeStamp = FileConf.getLong("player." + offlinePlayerUUID);

            if(FileConf.contains("player." + offlinePlayerUUID)){

                long timestamp = System.currentTimeMillis();
                if(jailPlayerTimeStamp < timestamp-5){

                    FileConf.set("player." + offlinePlayerUUID , null);
                    Objects.requireNonNull(player.getPlayer()).sendMessage(ChatColor.GREEN + " [Police] : YOU ARE FREE NOW ");
                    removeBossbar((Player) player);

                }

            }



        }

        jailFileDataSET();

    }
    // SET CONFIG FILE :
    public static void jailFileDataSET(){
        try {
            FileConf.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // DO JAIL :
    public static void doJail(Player target , float time) {
        // IF YOU WHERE IN JAIL :
        if(time == 0){
            // ADD BOSSBAR :
            bossbarAddJail(target," YOU ARE IN JAIL ", BarColor.PURPLE);

        }else {
            // IF ITS FIRST TIME OF JAIL :
            long timestamp;
            float plusTime = time * 1200000;
            timestamp = (long) (System.currentTimeMillis() + plusTime);

            // ADD PLAYER TO JAIL :
            jailHelper.jailAddPlayer(target.getUniqueId(),timestamp);

            // ADD BOSSBAR :
            bossbarAddJail(target,"YOU ARE IN JAIL " + time + " DAYS", BarColor.PURPLE);

            jailHelper.jailFileDataSET();

        }

        //TP TO JAIL LOCATION :
        Location jailLocation;
        jailLocation = new Location(Bukkit.getWorld("world"),FileConf.getInt("jail-location-x"),FileConf.getInt("jail-location-y"),FileConf.getInt("jail-location-z"));
        bossbarAddJail(target," YOU ARE IN JAIL ", BarColor.PURPLE);
        target.teleport(jailLocation);

    }
    // ADD PLAYER TO JAIL :
    public static void jailAddPlayer(UUID uuid, long time){

        FileConf.set("player." + uuid,time);
        jailFileDataSET();

    }
    // CHECK IS IN JAIL :
    public static Boolean IsInJail(UUID uuid){

        if(FileConf.contains("player." + uuid)){
            return true;

        }else {
            return false;
        }


    }



}
