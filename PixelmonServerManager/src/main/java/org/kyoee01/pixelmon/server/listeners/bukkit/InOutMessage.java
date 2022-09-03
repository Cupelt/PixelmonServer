package org.Kyoee01.pixelmon.server.listeners.bukkit;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class InOutMessage implements Listener {
    @EventHandler
    public void Join(PlayerJoinEvent e){
        e.setJoinMessage(ChatColor.translateAlternateColorCodes('&',
                "&f[&aJoin&f] &a"+e.getPlayer().getName()+" 등장☆"));
    }

    @EventHandler
    public void Quit(PlayerQuitEvent e){
        e.setQuitMessage(ChatColor.translateAlternateColorCodes('&',
                "&f[&cQuit&f] &c"+e.getPlayer().getName()+" 퇴장..."));
    }
}
