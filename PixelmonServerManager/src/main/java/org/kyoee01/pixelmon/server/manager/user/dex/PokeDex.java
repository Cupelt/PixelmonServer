package org.Kyoee01.pixelmon.server.manager.user.dex;

import com.pixelmonmod.pixelmon.api.pokedex.PlayerPokedex;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonFactory;
import com.pixelmonmod.pixelmon.api.pokemon.species.Pokedex;
import com.pixelmonmod.pixelmon.api.pokemon.species.Species;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import com.pixelmonmod.pixelmon.api.util.helpers.SpriteItemHelper;
import net.minecraft.util.text.TranslationTextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.ChatPaginator;
import sun.security.krb5.internal.crypto.Des;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PokeDex {
    public static int page = 0;

    private static DecimalFormat df = new DecimalFormat("000");
    private static DecimalFormat round = new DecimalFormat("##0.0#");

    public static void openDex(Player p){
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&',
                "&4&lPokeDex"));
        inv.setContents(getWorldDex(p));
        p.openInventory(inv);
    }

    public static ItemStack[] getWorldDex(Player p){
        List<ItemStack> pokemon = new ArrayList<>();
        for (int i = 0; i < 45; i++) {
            Integer dexNum = page*45+i+1;
            Optional<Species> o = PixelmonSpecies.fromDex(dexNum);
            PlayerPokedex dex = StorageProxy.getParty(p.getUniqueId()).playerPokedex;
            ItemStack item;
            ItemMeta meta;

            List<String> Lore = new ArrayList<>();
            if (o.isPresent()){
                if(dex.hasSeen(o.get())){
                    item = CraftItemStack.asBukkitCopy(SpriteItemHelper.getPhoto(PokemonFactory.create(o.get())));
                    meta = item.getItemMeta();

                    meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                            "&eNo."+df.format(dexNum)+"&f, &6"+o.get().getName()));
                    if (dex.hasCaught(o.get())){
                        Lore.add(ChatColor.translateAlternateColorCodes('&',
                                "&f키 : &7"+ round.format(o.get().getDefaultForm().getDimensions().getHeight()) +"&f, 몸무게 : &7"+round.format(o.get().getDefaultForm().getWeight())));
                        Lore.add(ChatColor.translateAlternateColorCodes('&',"&f"));
                        Lore.add(ChatColor.translateAlternateColorCodes('&',"&f설명 -"));

                        String[] Description = o.get().getDescTranslation().getString().split("\\n");
                        for (String l : Description){
                            String[] w = ChatPaginator.wordWrap(ChatColor.translateAlternateColorCodes('&',
                                    l), 30);
                            for(String word : w){
                                if(word.equals(w[0]))
                                    word += ChatColor.WHITE +" • ";
                                else
                                    word += ChatColor.WHITE+"    ";
                                Lore.add(word);
                            }
                            Lore.add(ChatColor.translateAlternateColorCodes('&',"&f"));
                        }
                    }
                    else{
                        Lore.add(ChatColor.translateAlternateColorCodes('&',
                                "&f키 : &7???&f, 몸무게 : &7???"));
                        Lore.add(ChatColor.translateAlternateColorCodes('&',"&f"));
                        Lore.add(ChatColor.translateAlternateColorCodes('&',"&f설명 -"));
                        Lore.add(ChatColor.translateAlternateColorCodes('&',"&f • ???"));
                    }
                }
                else{
                    item = new ItemStack(Material.BARRIER);
                    meta = item.getItemMeta();

                    meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                            "&eNo."+df.format(dexNum)+"&f, &8???"));

                    Lore.add(ChatColor.translateAlternateColorCodes('&',
                            "&f키 : &7???&f, 몸무게 : &7???"));
                    Lore.add(ChatColor.translateAlternateColorCodes('&',"&f"));
                    Lore.add(ChatColor.translateAlternateColorCodes('&',"&f설명 -"));
                    Lore.add(ChatColor.translateAlternateColorCodes('&',"&f • ???"));
                    Lore.add(ChatColor.translateAlternateColorCodes('&',"&f"));
                    Lore.add(ChatColor.translateAlternateColorCodes('&',"&f서식지 -"));
                    Lore.add(ChatColor.translateAlternateColorCodes('&',"&f • ???"));
                }
                meta.setLore(Lore);
                item.setItemMeta(meta);
                pokemon.add(item);
            }
            else{
                pokemon.add(new ItemStack(Material.AIR));
            }
        }

        return pokemon.toArray(new ItemStack[0]);
    }
}
