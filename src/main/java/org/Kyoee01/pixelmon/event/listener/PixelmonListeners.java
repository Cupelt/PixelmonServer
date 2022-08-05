package org.Kyoee01.pixelmon.event.listener;

import net.minecraftforge.eventbus.api.SubscribeEvent;

import com.pixelmonmod.pixelmon.api.enums.ExperienceGainType;
import com.pixelmonmod.pixelmon.api.events.ExperienceGainEvent;

import org.Kyoee01.pixelmon.Server.Prefixes;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class PixelmonListeners {

    private static DecimalFormat format = new DecimalFormat("###,###");

    @SubscribeEvent
    public void onUseExpCandyEvent(ExperienceGainEvent e){
        Integer needExp = e.pokemon.getExpToNextLevel();
        Player player = Bukkit.getPlayer(e.pokemon.getPlayerOwner().getUUID());
        ExperienceGainType ItemType = e.getType();
        if (ItemType.name().contains("EXP_CANDY")){
            int ExpPer = 0;
            if (ItemType.equals(ExperienceGainType.EXTRA_SMALL_EXP_CANDY)){
                ExpPer = 1;
            } else if (ItemType.equals(ExperienceGainType.SMALL_EXP_CANDY)){
                ExpPer = 5;
            } else if (ItemType.equals(ExperienceGainType.MEDIUM_EXP_CANDY)){
                ExpPer = 10;
            } else if (ItemType.equals(ExperienceGainType.LARGE_EXP_CANDY)){
                ExpPer = 20;
            } else if (ItemType.equals(ExperienceGainType.EXTRA_LARGE_EXP_CANDY)){
                ExpPer = 40;
            }
            e.setExperience(needExp*ExpPer/100+1);
        }
        else if (e.getType().name().contains("RARE_CANDY")){
            if(e.getExperience() != needExp){
                e.setCanceled(true);
            }
        }

        if (!e.isCanceled()){
            String name = "pixelmon."+e.pokemon.getPokemon().getSpecies().getName();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    new Prefixes().getServer()+"&a"+ name.toLowerCase() +" &f이(가) &e"+format.format(e.getExperience())+"EXP &f를 얻었습니다."));
        }
    }
}
