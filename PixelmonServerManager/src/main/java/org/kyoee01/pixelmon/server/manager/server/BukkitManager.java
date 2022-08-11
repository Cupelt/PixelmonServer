package org.Kyoee01.pixelmon.server.manager.server;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class BukkitManager {
    public static void registerEvent(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, Bukkit.getPluginManager().getPlugin("ListenEventManager"));
    }
}
