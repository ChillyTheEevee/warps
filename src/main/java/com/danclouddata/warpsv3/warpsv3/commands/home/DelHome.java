package com.danclouddata.warpsv3.warpsv3.commands.home;

import com.danclouddata.warpsv3.warpsv3.util.HomesUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class DelHome implements CommandExecutor, TabCompleter {
    private final HomesUtil homes;

    public DelHome(HomesUtil homes) {
        this.homes = homes;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length != 1)
            return false;
        Player player = (Player) sender;
        String homeName = args[0].toLowerCase();
        if(!homes.contains(player.getUniqueId(), homeName)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cHome &4" + homeName + "&c doesn't exist."));
            return true;
        }

        homes.remove(player.getUniqueId(), homeName);
        sender.sendMessage(ChatColor.GREEN + "Successfully deleted home: " + ChatColor.DARK_GREEN + homeName + ChatColor.GREEN + "!");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(args.length != 1)
            return null;
        return homes.getHomes(((Player) sender).getUniqueId());
    }
}
