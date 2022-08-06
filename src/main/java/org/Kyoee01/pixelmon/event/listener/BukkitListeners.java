package org.Kyoee01.pixelmon.event.listener;

import org.Kyoee01.pixelmon.Server.Prefixes;
import org.Kyoee01.pixelmon.User.UserData;
import org.Kyoee01.pixelmon.User.token.EnumToken;
import org.Kyoee01.pixelmon.User.token.TokenManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class BukkitListeners implements Listener {

    HashMap<UUID, UUID> Tags = TokenManager.getTagEntities();

    @EventHandler
    public void TokenMove(PlayerMoveEvent e){
        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();
        Optional<ArmorStand> optional;

        Location loc = e.getFrom().clone();
        loc.setY(loc.getY()+2.0);

        if (Tags.get(player.getUniqueId()) != null){
            optional = Optional.ofNullable((ArmorStand)Bukkit.getEntity(Tags.get(player.getUniqueId())));
        }
        else{
            optional = Optional.empty();
        }
        if (Tags.get(player.getUniqueId()) == null || optional.isEmpty()){
            ArmorStand entity = (ArmorStand)player.getLocation().getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
            entity.setSilent(true);
            entity.setGravity(false);
            entity.setCustomNameVisible(true);
            entity.setSmall(true);
            entity.setInvisible(true);
            entity.setMarker(true);

            EnumToken token;
            try {
                token = EnumToken.valueOf(new UserData(uuid).getToken());
            }
            catch (NullPointerException error){
                token = EnumToken.NULL;
            }
            entity.setCustomName(ChatColor.translateAlternateColorCodes('&',
                    TokenManager.EnumtoString(token)));
            Tags.put(uuid, entity.getUniqueId());
            optional = Optional.ofNullable((ArmorStand)Bukkit.getEntity(Tags.get(player.getUniqueId())));
            System.out.println("Player Tags is created! "+Tags.get(uuid).toString());
        }
        Entity entity = optional.get();
        entity.teleport(loc);
    }

    @EventHandler
    public void ResetToken(PlayerQuitEvent e){
        Set<UUID> players = Tags.keySet();
        for(UUID uuid : players){
            if (Bukkit.getOfflinePlayer(uuid).isOnline()){
                Bukkit.getEntity(Tags.get(uuid)).remove();
                Tags.remove(uuid);
                System.out.println("Player Tags is deleted!");
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', new Prefixes().getJoin() + e.getPlayer().getName()+" &e등장☆"));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', new Prefixes().getQuit() + e.getPlayer().getName()+" &c퇴장..."));
    }
}
