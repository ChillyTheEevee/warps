package com.danclouddata.warpsv3.warpsv3.commands.warp;

import com.danclouddata.warpsv3.warpsv3.WarpsV3;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.danclouddata.warpsv3.warpsv3.util.WarpsUtil;

import java.util.Random;

public class Hub implements CommandExecutor {

    private WarpsUtil warps;
    private WarpsV3 plugin;

    public Hub(WarpsV3 plugin, WarpsUtil warps) {
        this.plugin = plugin;
        this.warps = warps;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        if(args.length != 0)
            return false;

        Player player = (Player) sender;
        Location hub = warps.get("hub");
        if(hub == null) {
            player.sendMessage(ChatColor.RED + "The hub has not been set! Tell an Op to set it up in the config file!");
            return true;
        }
        Location originalLocation = player.getLocation();
        CountDownToTeleport(player, originalLocation, hub, 5);
        return true;
    }

    private void CountDownToTeleport(Player player, Location originalLocation, Location hub, int count) {
        Location playerLocation = player.getLocation();
        if (playerLocation.getX() != originalLocation.getX() || playerLocation.getY() != originalLocation.getY() ||
                playerLocation.getZ() != originalLocation.getZ()) {
            player.playSound(player.getLocation(), Sound.ENTITY_WITHER_BREAK_BLOCK, 1, 1);
            player.sendMessage(ChatColor.RED + "Unable to teleport to the hub! You moved!");
            Random random = new Random();
            if (random.nextInt(1, 10) == 7)
                player.sendMessage(ChatColor.RED + "Try not to be a cringe loser next time L");
            return;
        }
        if (count == 0) {
            player.teleport(hub);
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            player.sendMessage(ChatColor.GREEN + "You successfully teleported to the hub!");
        } else {
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(
                    ChatColor.RED + "Teleporting! Do not move. " + ChatColor.GREEN +
                            "Teleporting in... " + ChatColor.DARK_GREEN + count));
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                CountDownToTeleport(player, originalLocation, hub, count-1);
            }, 20L); // 60 L == 3 sec, 20 ticks == 1 sec
        }
    }
}
