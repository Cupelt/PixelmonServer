package org.Kyoee01.pixelmon.event.listener;

import org.Kyoee01.pixelmon.Server.Prefixes;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BukkitListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', new Prefixes().getJoin() + e.getPlayer()+" &e등장☆"));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', new Prefixes().getQuit() + e.getPlayer()+" &c퇴장..."));
    }
}
