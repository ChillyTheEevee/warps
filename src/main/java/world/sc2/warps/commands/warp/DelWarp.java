package world.sc2.warps.commands.warp;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import world.sc2.warps.util.WarpsUtil;

import java.util.List;

public class DelWarp implements CommandExecutor, TabCompleter {
    private final WarpsUtil warps;

    public DelWarp(WarpsUtil warps) {
        this.warps = warps;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length != 1)
            return false;

        String warpName = args[0].toLowerCase();
        if(!warps.contains(warpName)) {
            if (sender instanceof Player player)
                player.playSound(player.getLocation(), Sound.ENTITY_WITHER_BREAK_BLOCK, 1, 1);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cWarp &4" + warpName + "&c doesn't exist."));
            return true;
        }

        if (sender instanceof Player player)
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);

        warps.remove(warpName);
        sender.sendMessage(ChatColor.GREEN + "Successfully deleted warp: " + ChatColor.DARK_GREEN + warpName + ChatColor.GREEN + "!");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(args.length != 1)
            return null;
        return warps.getWarps();
    }
}
