package org.Kyoee01.pixelmon.server.listeners;

import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import net.minecraft.entity.player.ServerPlayerEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class BukkitEventListener implements Listener {
    @EventHandler
    public void createTag(PlayerJoinEvent e){
        Player p = e.getPlayer();
        ServerPlayerEntity forgePlayer = StorageProxy.getParty(p.getUniqueId()).getPlayer();
    }
}
