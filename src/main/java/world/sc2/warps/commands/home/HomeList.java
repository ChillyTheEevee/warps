package world.sc2.warps.commands.home;

import world.sc2.warps.util.HomesUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class HomeList implements CommandExecutor {
    private HomesUtil homesUtil;
    public HomeList(HomesUtil homesUtil) {
        this.homesUtil = homesUtil;
    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length != 0)
            return false;
        ArrayList<String> homes = homesUtil.getHomes(((Player) sender).getUniqueId());

        if(homes.size() == 0) {
            sender.sendMessage(ChatColor.RED + "You have not set any homes.");
            return true;
        }

        String message = "&2" + homes.toString();

        message = message.replace("[", "")
                        .replace("]", "")
                                .replace(",", "&a,&2");
        message += "&a.";
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        return true;
    }
}
