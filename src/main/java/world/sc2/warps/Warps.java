package world.sc2.warps;

import world.sc2.warps.commands.warp.*;
import world.sc2.warps.commands.home.DelHome;
import world.sc2.warps.commands.home.Home;
import world.sc2.warps.commands.home.HomeList;
import world.sc2.warps.commands.home.SetHome;
import world.sc2.warps.commands.pettycommands.ImTerribleAtVideoGamesAndFellIntoALavaLakeForTheFourthTimeTodayPleaseSaveMe;
import org.bukkit.plugin.java.JavaPlugin;

import world.sc2.warps.util.HomesUtil;
import world.sc2.warps.util.WarpsUtil;

public final class Warps extends JavaPlugin {

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
