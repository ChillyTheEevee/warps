package world.sc2.warps.commands.pettycommands;

import world.sc2.warps.Warps;
import world.sc2.warps.util.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class ImTerribleAtVideoGamesAndFellIntoALavaLakeForTheFourthTimeTodayPleaseSaveMe implements CommandExecutor {
    private final ConfigUtil configUtil;

    public ImTerribleAtVideoGamesAndFellIntoALavaLakeForTheFourthTimeTodayPleaseSaveMe(Warps plugin) {
        configUtil = new ConfigUtil(plugin, "teleportUsage.yml");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length != 0)
            return false;

        if (!(sender instanceof Player player)) {
            Bukkit.getLogger().info("Only Players can use this Command!");
            return true;
        }

        int numUsed = getNumTimesUsed(player.getUniqueId());

        if (numUsed >= 3) {
            player.sendMessage(ChatColor.DARK_RED + "You can only use this command three times! sorry bro. Sucks. L.");
            return true;
        }

        Location lastDeathLocation = player.getLastDeathLocation();
        if (lastDeathLocation == null) {
            player.sendMessage(ChatColor.DARK_RED + "You have no stored deaths.");
            return true;
        }

        teleportToLastDeath(player, lastDeathLocation);

        switch (numUsed) {
            case 0 -> Bukkit.broadcastMessage(ChatColor.DARK_RED + player.getName() + ChatColor.RED +
                    " sucks at video games! LLLL! You may now laugh at them in chat. What a shame. " +
                    "They have now used " + ChatColor.DARK_RED + "1/3" + ChatColor.RED + " of their teleports.");
            case 1 -> Bukkit.broadcastMessage(ChatColor.DARK_RED + player.getName() + ChatColor.RED + " " +
                    "is REALLY bad at this game! At this point it's just kind of a skill issue tbh. " +
                    "They have now used " + ChatColor.DARK_RED + "2/3" + ChatColor.RED + " of their teleports!");
            case 2 -> Bukkit.broadcastMessage(ChatColor.DARK_RED + player.getName() + ChatColor.RED +
                    " has used their last teleport! " + ChatColor.DARK_RED + player.getName() + ChatColor.RED +
                    ", you better be careful from now on! You will not be given any more teleports.");
        }

        increaseNum(player.getUniqueId());
        return true;
    }

    // Main command
    private void teleportToLastDeath(Player player, Location lastDeath) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1200, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 5));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 5));
        player.teleport(lastDeath);
    }

    // Data Storage
    private int getNumTimesUsed(UUID player) {
        if (!(configUtil.getConfig().contains(player + ".numTimesUsed")))
            return 0;
        return configUtil.getConfig().getInt(player + ".numTimesUsed");
    }

    private void increaseNum(UUID player) {
        configUtil.getConfig().set(player + ".numTimesUsed", getNumTimesUsed(player)+1);
        configUtil.save();
    }

}
