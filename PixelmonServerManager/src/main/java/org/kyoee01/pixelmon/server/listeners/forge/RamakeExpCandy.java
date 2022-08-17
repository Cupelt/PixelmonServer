package org.Kyoee01.pixelmon.server.listeners.forge;

import com.pixelmonmod.pixelmon.api.enums.ExperienceGainType;
import com.pixelmonmod.pixelmon.api.events.ExperienceGainEvent;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.bukkit.ChatColor;

import java.text.DecimalFormat;
import java.util.UUID;

public class RamakeExpCandy {

    private static DecimalFormat format = new DecimalFormat("###,###");
    private static String prefix = "&f&l[&6&lSERVER&f&l] &f";

    @SubscribeEvent
    public void onUseExpCandyEvent(ExperienceGainEvent e) {
        Integer needExp = e.pokemon.getExpToNextLevel();
        ServerPlayerEntity player = e.pokemon.getPlayerOwner();
        ExperienceGainType ItemType = e.getType();
        if (ItemType.name().contains("EXP_CANDY")){
            float ExpPer = 0;
            if (ItemType.equals(ExperienceGainType.EXTRA_SMALL_EXP_CANDY)){
                ExpPer = 2.5f;
            } else if (ItemType.equals(ExperienceGainType.SMALL_EXP_CANDY)){
                ExpPer = 7.5f;
            } else if (ItemType.equals(ExperienceGainType.MEDIUM_EXP_CANDY)){
                ExpPer = 15.0f;
            } else if (ItemType.equals(ExperienceGainType.LARGE_EXP_CANDY)){
                ExpPer = 30.0f;
            } else if (ItemType.equals(ExperienceGainType.EXTRA_LARGE_EXP_CANDY)){
                ExpPer = 60.0f;
            }
            e.setExperience(Math.round(needExp*ExpPer/100.0f+1.0f));
        }
        else if (e.getType().name().contains("RARE_CANDY")){
            if(e.getExperience() != needExp){
                e.setCanceled(true);
            }
        }

        if (!e.isCanceled() && player != null){
            String name;
            if (e.pokemon.getPokemon().getNickname().equals("")){
                name = "pixelmon."+e.pokemon.getPokemon().getSpecies().getName().toLowerCase();
            } else {
                name = e.pokemon.getPokemon().getNickname();
            }
            player.sendMessage(new StringTextComponent(ChatColor.translateAlternateColorCodes('&',
                            prefix +"&a&l"+ name +" &f이(가) &e"+format.format(e.getExperience())+"EXP &f를 얻었습니다.")),
                    UUID.randomUUID());
        }
    }
}