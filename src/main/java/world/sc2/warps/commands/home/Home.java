package world.sc2.warps.commands.home;

import world.sc2.warps.util.HomesUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class Home implements CommandExecutor, TabCompleter {
    private final HomesUtil homes;

    public Home(HomesUtil homes){
        this.homes = homes;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        if(args.length != 1)
            return false;

        Player player = (Player) sender;
        Location home = homes.get(player.getUniqueId(), args[0]);
        if(home == null) {
            player.sendMessage(ChatColor.RED + "A warp with that name does not exist!");
            return true;
        }
        player.teleport(homes.get(player.getUniqueId(), args[0]));
        player.sendMessage(ChatColor.GREEN + "You successfully teleported to the warp: " + ChatColor.DARK_GREEN + args[0] + ChatColor.GREEN + "!");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(args.length != 1)
            return null;
        List<String> warpList = homes.getHomes(((Player) sender).getUniqueId());
        return warpList;
    }
}
