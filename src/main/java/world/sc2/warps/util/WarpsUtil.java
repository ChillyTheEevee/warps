package world.sc2.warps.util;

import world.sc2.warps.Warps;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;

public class WarpsUtil {
    private final ConfigUtil config;

    public WarpsUtil(Warps plugin) {
        config = new ConfigUtil(plugin, "warps.yml");
    }

    public Location get(String warpName) {
        if(!this.contains(warpName))
            return null;

        World world = Bukkit.getWorld(config.getConfig().getString("warps." + warpName + ".world"));
        double x = config.getConfig().getDouble("warps." + warpName + ".x");
        double y = config.getConfig().getDouble("warps." + warpName + ".y");
        double z = config.getConfig().getDouble("warps." + warpName + ".z");
        float yaw = (float) config.getConfig().getDouble("warps." + warpName + ".yaw");
        float pitch = (float) config.getConfig().getDouble("warps." + warpName + ".pitch");

        return new Location(world, x, y, z, yaw, pitch);
    }
    public void add(String warpName, Location warp) {

        String worldName = warp.getWorld().getName();
        double x = warp.getX();
        double y = warp.getY();
        double z = warp.getZ();
        float yaw = warp.getYaw();
        float pitch = warp.getPitch();

        config.getConfig().set("warps." + warpName + ".world", worldName);
        config.getConfig().set("warps." + warpName + ".x", x);
        config.getConfig().set("warps." + warpName + ".y", y);
        config.getConfig().set("warps." + warpName + ".z", z);
        config.getConfig().set("warps." + warpName + ".yaw", yaw);
        config.getConfig().set("warps." + warpName + ".pitch", pitch);
        config.save();
    }

    public void remove(String warpName) {
        config.getConfig().set("warps." + warpName, null);
        config.save();
    }

    // x + 65
    // y - 20
    // z - 65

    public boolean contains(String warpName) {
        if (config.getConfig().contains("warps." + warpName))
            return true;
        return false;
    }

    public ArrayList<String> getWarps() {
        ConfigurationSection configurationSection = config.getConfig().getConfigurationSection("warps");
        if (configurationSection == null) {
            return null;
        }
        return new ArrayList<>(configurationSection.getKeys(false));
    }
}
