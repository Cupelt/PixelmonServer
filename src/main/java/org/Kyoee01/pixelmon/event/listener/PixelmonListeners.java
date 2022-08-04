package org.Kyoee01.pixelmon.event.listener;

import com.pixelmonmod.pixelmon.api.pokemon.stats.links.PokemonLink;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.util.text.TranslationTextComponent;

import com.pixelmonmod.pixelmon.api.events.RareCandyEvent;
import com.pixelmonmod.pixelmon.api.enums.ExperienceGainType;
import com.pixelmonmod.pixelmon.api.events.ExperienceGainEvent;
import com.pixelmonmod.pixelmon.api.events.battles.AttackEvent;

import org.Kyoee01.pixelmon.PixelmonServerManager;

import org.Kyoee01.pixelmon.prefix.Prefixes;
import org.Kyoee01.pixelmon.translation.Translator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.Console;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

public class PixelmonListeners {

    private static DecimalFormat format = new DecimalFormat("###,###");

    @SubscribeEvent
    public void onUseExpCandyEvent(ExperienceGainEvent e){
        Integer needExp = e.pokemon.getExpToNextLevel();
        Player player = Bukkit.getPlayer(e.pokemon.getPlayerOwner().getUUID());
        if (e.getType().name().contains("EXP_CANDY")){

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
