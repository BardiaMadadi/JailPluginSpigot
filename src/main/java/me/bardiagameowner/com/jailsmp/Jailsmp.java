package me.bardiagameowner.com.jailsmp;

import me.bardiagameowner.com.jailsmp.commands.jailcommands;
import me.bardiagameowner.com.jailsmp.listeners.jailListeners;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

import static me.bardiagameowner.com.jailsmp.helper.jailHelper.IsJailFinish;
import static me.bardiagameowner.com.jailsmp.helper.jailHelper.jailFileDataSET;

public final class Jailsmp extends JavaPlugin {


    // [ FILE ] : JAIL CONFIG FILE Var

    public static File file;
    public static FileConfiguration FileConf;


    // [ FILE ] : JAIL CONFIG FILE END





    // ON PLUGIN LOADED
    @Override
    public void onEnable() {

        // REGISTERS :
        registerListeners();
        registerCommands();

        startConfigFile();


        // LOOP THIS FUNC EVERY SECCOND
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

            public void run() {

                IsJailFinish();

            }
        }, 0, 20);



    }

    // END OF ON-ENABLE ....



    // REGISTER COMMANDS / LISTENERS :

    public void registerListeners(){

                getServer().getPluginManager().registerEvents(new jailListeners(), this);

    }
    public void registerCommands(){

        this.getCommand("jail").setExecutor(new jailcommands());

    }
    public void startConfigFile(){
        // [ FILE ] : JAIL CONFIG FILE

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // [ FILE ] : MALE FILE

        file = new File(getDataFolder(),"JailData.yml");

        // [ FILE ] : IF FILE DOSE NOT EXIST : make file

        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // LOAD CONFIG FILE
        FileConf = YamlConfiguration.loadConfiguration(file);

        if(!FileConf.contains("jail-location-x") || !FileConf.contains("jail-location-y") || !FileConf.contains("jail-location-z")){
            FileConf.set("jail-location-x", 1);
            FileConf.set("jail-location-y",1);
            FileConf.set("jail-location-z",1);
        }

        jailFileDataSET();
    }

}

