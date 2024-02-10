package world.sc2.warps.commands.warp;

import world.sc2.warps.util.WarpsUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class WarpList implements CommandExecutor {
    private WarpsUtil warpsUtil;
    public WarpList(WarpsUtil warpsUtil) {
        this.warpsUtil = warpsUtil;
    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length != 0)
            return false;

        ArrayList<String> warps = warpsUtil.getWarps();

        if(warps.size() == 0) {
            sender.sendMessage(ChatColor.RED + "You have not set any warps.");
            return true;
        }

        String message = "&2" + warps.toString();

        message = message.replace("[", "")
                        .replace("]", "")
                                .replace(",", "&a,&2");
        message += "&a.";
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        return true;
    }
}
