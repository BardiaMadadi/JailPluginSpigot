package me.bardiagameowner.com.jailsmp.helper;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class jailBossbarHelper {

    // VARIABLES :

    private static BossBar bossBar;

    // ADD BOSSBAR JAIL :
    public static void bossbarAddJail(Player player, String msg, BarColor barColor){
        bossBar = Bukkit.createBossBar(
                ChatColor.RED + msg,
                barColor,
                BarStyle.SOLID);
        bossBar.setVisible(true);
        bossBar.addPlayer(player);
        bossBar.setProgress(1.0f);
    }

    // REMOVE BOSSBAR JAIL :
    public static void removeBossbar(Player player){

        bossBar.removePlayer(player);


    }

    // GET BOSSBAR JAIL :
    public static BossBar getBossBar(){

        return bossBar;
    }
}
