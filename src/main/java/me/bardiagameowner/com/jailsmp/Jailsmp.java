package me.bardiagameowner.com.jailsmp;

import me.bardiagameowner.com.jailsmp.commands.jailcommands;
import me.bardiagameowner.com.jailsmp.helper.jailHelper;
import me.bardiagameowner.com.jailsmp.listeners.jailListeners;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public final class Jailsmp extends JavaPlugin {


    // [ FILE ] : JAIL CONFIG FILE Var

    public static File file;
    public static FileConfiguration FileConf;


    // [ FILE ] : JAIL CONFIG FILE END
    public static jailcommands jailCmd;

    @Override
    public void onEnable() {

        registerListeners();
        registerCommands();


        // [ FILE ] : JAIL CONFIG FILE

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        file = new File(getDataFolder(),"JailData.yml");


        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileConf = YamlConfiguration.loadConfiguration(file);

        if(!FileConf.contains("jail-location-x") || !FileConf.contains("jail-location-y") || !FileConf.contains("jail-location-z")){
            FileConf.set("jail-location-x", 1);
            FileConf.set("jail-location-y",1);
            FileConf.set("jail-location-z",1);
        }

        jailFileDataSET();


        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

            public void run() {
                IsJailFinish();
            }
        }, 0, 20);



    }


    public void IsJailFinish(){

        for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {



            UUID offlinePlayerUUID = player.getUniqueId();

            long jailPlayerTimeStamp = FileConf.getLong("player." + offlinePlayerUUID);

            if(FileConf.contains("player." + offlinePlayerUUID)){

                long timestamp = System.currentTimeMillis();
                if(jailPlayerTimeStamp < timestamp-5){

                    FileConf.set("player." + offlinePlayerUUID , null);
                    Objects.requireNonNull(player.getPlayer()).sendMessage(ChatColor.GREEN + " [Police] : YOU ARE FREE NOW ");
                    jailHelper.removeBossbar((Player) player);

                }

            }



        }

        jailFileDataSET();

    }

    public void registerListeners(){

                getServer().getPluginManager().registerEvents(new jailListeners(), this);

    }
    public void registerCommands(){

        this.getCommand("jail").setExecutor(new jailcommands());

    }

    public static void jailFileDataSET(){
        try {
            FileConf.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void jailAddPlayer(UUID uuid, long time){

        FileConf.set("player." + uuid,time);
        jailFileDataSET();

    }

    public static Boolean IsInJail(UUID uuid){

        if(FileConf.contains("player." + uuid)){
            return true;

        }else {
            return false;
        }


    }



}

