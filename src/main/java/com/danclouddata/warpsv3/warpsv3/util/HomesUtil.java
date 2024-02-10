package com.danclouddata.warpsv3.warpsv3.util;

import com.danclouddata.warpsv3.warpsv3.WarpsV3;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class HomesUtil {
    final private ConfigUtil config;

    public HomesUtil(WarpsV3 plugin) {
        config = new ConfigUtil(plugin, "homes.yml");
    }

    public Location get(UUID player, String homeName) {
        if(!this.contains(player, homeName))
            return null;

        World world = Bukkit.getWorld(config.getConfig().getString("homes." + player + "." + homeName + ".world"));
        double x = config.getConfig().getDouble("homes." + player + "." + homeName + ".x");
        double y = config.getConfig().getDouble("homes." + player + "." + homeName + ".y");
        double z = config.getConfig().getDouble("homes." + player + "." + homeName + ".z");
        float yaw = (float) config.getConfig().getDouble("homes." + player + "." + homeName + ".yaw");
        float pitch = (float) config.getConfig().getDouble("homes." + player + "." + homeName + ".pitch");

        return new Location(world, x, y, z, yaw, pitch);
    }

    public void add(UUID player, String homeName, Location home) {

        String worldName = home.getWorld().getName();
        double x = home.getX();
        double y = home.getY();
        double z = home.getZ();
        float yaw = home.getYaw();
        float pitch = home.getPitch();

        config.getConfig().set("homes." + player + "." + homeName + ".world", worldName);
        config.getConfig().set("homes." + player + "." + homeName + ".x", x);
        config.getConfig().set("homes." + player + "." + homeName + ".y", y);
        config.getConfig().set("homes." + player + "." + homeName + ".z", z);
        config.getConfig().set("homes." + player + "." + homeName + ".yaw", yaw);
        config.getConfig().set("homes." + player + "." + homeName + ".pitch", pitch);
        config.save();
    }

    public void remove(UUID player, String homeName) {
        config.getConfig().set("homes." + player + "." + homeName, null);
        config.save();
    }

    public boolean contains(UUID player, String warpName) {
        return config.getConfig().contains("homes." + player + "." + warpName);
    }

    public ArrayList<String> getHomes(UUID player) {
        if (config.getConfig().isConfigurationSection("homes." + player))
            return new ArrayList<>(Objects.requireNonNull(config.getConfig().getConfigurationSection("homes." + player)).getKeys(false));
        return null;
    }
}
