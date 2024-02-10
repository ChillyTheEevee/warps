package world.sc2.warps.commands.warp;

import org.bukkit.Sound;
import world.sc2.warps.util.WarpsUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarp implements CommandExecutor{

    private final WarpsUtil warps;

    public SetWarp(WarpsUtil warps) {
        this.warps = warps;
    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can execute this command!");
            return true;
        }

        Player player = (Player) sender;

        if(args.length != 1)
            return false;

        String warpName = args[0].toLowerCase();

        if(warps.contains(warpName)) {
            player.playSound(player.getLocation(), Sound.ENTITY_WITHER_BREAK_BLOCK, 1, 1);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cWarp &4" + warpName + " &calready exists."));
            return true;
        }

        Location location = player.getLocation();
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        warps.add(warpName, location);
        player.sendMessage(ChatColor.GREEN + "Successfully added a new warp: " + ChatColor.DARK_GREEN + warpName + ChatColor.GREEN + "!");
        return true;
    }
}
