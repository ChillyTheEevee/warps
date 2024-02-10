package com.danclouddata.warpsv3.warpsv3;

import com.danclouddata.warpsv3.warpsv3.commands.pettycommands.ImTerribleAtVideoGamesAndFellIntoALavaLakeForTheFourthTimeTodayPleaseSaveMe;
import org.bukkit.plugin.java.JavaPlugin;

import com.danclouddata.warpsv3.warpsv3.commands.warp.*;
import com.danclouddata.warpsv3.warpsv3.commands.home.*;
import com.danclouddata.warpsv3.warpsv3.util.HomesUtil;
import com.danclouddata.warpsv3.warpsv3.util.WarpsUtil;

public final class WarpsV3 extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        //Warps
        WarpsUtil warpsUtil = new WarpsUtil(this);

        getCommand("hub").setExecutor(new Hub(this, warpsUtil));
        getCommand("setwarp").setExecutor(new SetWarp(warpsUtil));
        getCommand("delwarp").setExecutor(new DelWarp(warpsUtil));
        getCommand("warp").setExecutor(new Warp(warpsUtil));
        getCommand("warplist").setExecutor(new WarpList(warpsUtil));

        //Homes
        HomesUtil homesUtil = new HomesUtil(this);

        // Register commands
        getCommand("sethome").setExecutor(new SetHome(homesUtil));
        getCommand("delhome").setExecutor(new DelHome(homesUtil));
        getCommand("home").setExecutor(new Home(homesUtil));
        getCommand("homelist").setExecutor(new HomeList(homesUtil));

        //Extra
        getCommand("imterribleatvideogamesandfellintoalavalakeforthefourthtimetodaypleasesaveme")
                .setExecutor(new ImTerribleAtVideoGamesAndFellIntoALavaLakeForTheFourthTimeTodayPleaseSaveMe(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
