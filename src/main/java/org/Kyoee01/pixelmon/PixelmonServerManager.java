package org.Kyoee01.pixelmon;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class PixelmonServerManager extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info(ChatColor.translateAlternateColorCodes('&', "&4Pixelmon &bServer &aPlugin Enabled"));
    }
}