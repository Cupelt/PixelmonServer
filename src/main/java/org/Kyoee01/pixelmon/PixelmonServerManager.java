package org.Kyoee01.pixelmon;

import com.pixelmonmod.pixelmon.Pixelmon;

import io.izzel.arclight.common.mod.server.api.DefaultArclightServer;

import org.Kyoee01.pixelmon.event.listener.BukkitListeners;
import org.Kyoee01.pixelmon.event.listener.PixelmonListeners;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class PixelmonServerManager extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info(ChatColor.translateAlternateColorCodes('&', "&4Pixelmon &bServer &aPlugin Enabled"));

        DefaultArclightServer api = new DefaultArclightServer();

        api.registerForgeEvent(this, Pixelmon.EVENT_BUS, new PixelmonListeners());
        getServer().getPluginManager().registerEvents(new BukkitListeners(), this);

        //getCommand("test").setExecutor(new TestCommand());
    }
}