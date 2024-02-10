package com.danclouddata.warpsv3.warpsv3.commands.warp;

import org.bukkit.Sound;
import com.danclouddata.warpsv3.warpsv3.util.WarpsUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;
public class Warp implements CommandExecutor, TabCompleter {
    private final WarpsUtil warps;

    public Warp(WarpsUtil warps){
        this.warps = warps;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        if(args.length != 1)
            return false;

        Player player = (Player) sender;
        Location warp = warps.get(args[0]);
        if(warp == null) {
            player.playSound(player.getLocation(), Sound.ENTITY_WITHER_BREAK_BLOCK, 1, 1);
            player.sendMessage(ChatColor.RED + "A warp with that name does not exist!");
            return true;
        }

        player.teleport(warp);
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        player.sendMessage(ChatColor.GREEN + "You successfully teleported to the warp: " + ChatColor.DARK_GREEN
                + args[0] + ChatColor.GREEN + "!");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) return null;
        return warps.getWarps();
    }
}
