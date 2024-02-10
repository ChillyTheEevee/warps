package com.danclouddata.warpsv3.warpsv3.commands.home;

import com.danclouddata.warpsv3.warpsv3.util.HomesUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHome implements CommandExecutor {

    private final HomesUtil homes;

    public SetHome(HomesUtil homes) {
        this.homes = homes;
    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can execute this command!");
            return true;
        }

        if(args.length != 1)
            return false;

        String homeName = args[0].toLowerCase();
        Player player = (Player) sender;

        if(homes.contains(player.getUniqueId(), homeName)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cHome &4" + homeName + " &calready exists."));
            return true;
        }

        Location location = player.getLocation();
        homes.add(player.getUniqueId(), homeName, location);
        player.sendMessage(ChatColor.GREEN + "Successfully added a new home: " + ChatColor.DARK_GREEN + homeName + ChatColor.GREEN + "!");
        return true;
    }
}
