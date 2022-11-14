package org.kyoee01.pixelmon.visual.summon;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.config.PixelmonConfigProxy;
import com.pixelmonmod.pixelmon.api.events.legendary.ArceusEvent;
import com.pixelmonmod.pixelmon.api.events.legendary.TimespaceEvent;
import com.pixelmonmod.pixelmon.api.registries.PixelmonItems;
import com.pixelmonmod.pixelmon.api.util.helpers.BlockHelper;
;import com.pixelmonmod.pixelmon.api.util.helpers.RandomHelper;
import com.pixelmonmod.pixelmon.blocks.machines.TimespaceAltarBlock;
import com.pixelmonmod.pixelmon.blocks.tileentity.TimespaceAltarTileEntity;
import com.pixelmonmod.pixelmon.comm.ChatHandler;
import com.pixelmonmod.pixelmon.items.AzureFluteItem;
import com.pixelmonmod.pixelmon.items.heldItems.TimespaceOrbItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.kyoee01.pixelmon.visual.PixelmonVisualUpgrader;

import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

public class summonHendler{

    private static HashMap<BlockPos, TimespaceAltarTileEntity> TileEntites = new HashMap<>();
    private static double testTick = 0;
    public CompoundNBT NBTConnector(CompoundNBT nbt, TimespaceAltarTileEntity tile){
        if (PixelmonVisualUpgrader.IsEnabled){
            nbt.putBoolean("VisualChainIn", nbt.getBoolean("ChainIn"));
            if (nbt.getBoolean("ChainIn")){
                TileEntites.put(tile.getPos(), tile);
            }
            nbt.putBoolean("ChainIn", false);
        }
        else{
            if (nbt.contains("VisualChainIn")){
                nbt.putBoolean("ChainIn", nbt.getBoolean("VisualChainIn"));
                nbt.remove("VisualChainIn");
            }
        }
        return nbt;
    }

    @SubscribeEvent
    public void UpdateBlock(ChunkEvent.Load e){
        for (BlockPos pos : e.getChunk().getTileEntitiesPos()){
            TimespaceAltarTileEntity tile;
            if (e.getWorld().getBlockState(pos).getBlock() instanceof TimespaceAltarBlock) {
                tile = BlockHelper.getTileEntity(TimespaceAltarTileEntity.class, e.getWorld(), pos);
                CompoundNBT nbt = NBTConnector(tile.getUpdateTag(), tile);
                new SUpdateTileEntityPacket(tile.getPos(), 0, nbt);
            }
        }
    }

    @SubscribeEvent
    public void tick(TickEvent.ServerTickEvent e){
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        testTick += 1.0/20.0;
        server.sendMessage(new StringTextComponent(String.valueOf(testTick)), UUID.randomUUID());
    }

    @SubscribeEvent
    public void BlockClickCanceler(PlayerInteractEvent.RightClickBlock e) {
        if (e.getWorld().getBlockState(e.getPos()).getBlock() instanceof TimespaceAltarBlock)
            e.setCanceled(true);
    }

    @SubscribeEvent
    public void WorldSave(WorldEvent.Save e){

    }

    @SubscribeEvent
    public void ItemUse(PlayerInteractEvent.RightClickItem e){
        World worldIn = e.getWorld();
        PlayerEntity playerIn = e.getPlayer();
        ItemStack stack = e.getItemStack();
        if (!(stack.getItem() == PixelmonItems.red_chain || stack.getItem() instanceof TimespaceOrbItem ||stack.getItem() instanceof AzureFluteItem)){
            return;
        }
        playerIn.sendMessage(new StringTextComponent("pass1"), Util.DUMMY_UUID);

        if (!worldIn.isRemote) {
            Iterator var5 = worldIn.loadedTileEntityList.iterator();

            while(var5.hasNext()) {
                TileEntity te = (TileEntity)var5.next();
                if (te instanceof TimespaceAltarTileEntity && te.getPos().distanceSq(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), true) <= 200.0) {
                    TimespaceAltarTileEntity altar = (TimespaceAltarTileEntity)te;
                    if (altar.timeSpent == 0 && !altar.chainIn && altar.orbIn == TimespaceAltarTileEntity.Orb.NONE) {
                        altar.summoningShiny = PixelmonConfigProxy.getSpawning().getShinyRate(worldIn.getDimensionKey()) > 0.0F && RandomHelper.getRandomChance(1.0F / PixelmonConfigProxy.getSpawning().getShinyRate(worldIn.getDimensionKey()));

                        stack.shrink(1);
                        altar.summoningPlayer = playerIn;
                        altar.summoningState = worldIn.getBlockState(te.getPos());
                        altar.flutePlayed = true;
                    }
                }
            }

            playerIn.sendMessage(new StringTextComponent(TextFormatting.GRAY + I18n.format("pixelmon.items.azure.fail")), Util.DUMMY_UUID);
        }
    }

    /*
    @SubscribeEvent
    public void TimespaceAltarChainEvent(PlayerInteractEvent.RightClickBlock e) {
        if (!e.getHand().equals(Hand.MAIN_HAND)) return;
        if (PixelmonVisualUpgrader.IsEnabled)
            e.setCanceled(true);
        else
            return;

        TimespaceAltarTileEntity tile;
        if (e.getWorld().getBlockState(e.getPos()).getBlock() instanceof TimespaceAltarBlock) {
            tile = BlockHelper.getTileEntity(TimespaceAltarTileEntity.class, e.getWorld(), e.getPos());
        } else {
            return;
        }

        PlayerEntity player = e.getPlayer();
        ItemStack item = e.getPlayer().getHeldItemMainhand();
        CompoundNBT nbt = NBTConnector(tile.getTileData(), tile);
        TimespaceAltarTileEntity.Orb orb = TimespaceAltarTileEntity.Orb.values()[nbt.getInt("OrbIn")];;

        if ((orb != TimespaceAltarTileEntity.Orb.NONE || nbt.getBoolean("VisualChainIn")) && player.isSneaking() && (orb == TimespaceAltarTileEntity.Orb.NONE || !nbt.getBoolean("VisualChainIn"))) {
            if (orb != TimespaceAltarTileEntity.Orb.NONE) {
                switch (orb) {
                    case PALKIA:
                        player.addItemStackToInventory(new ItemStack(PixelmonItems.lustrous_orb));
                        break;
                    case DIALGA:
                        player.addItemStackToInventory(new ItemStack(PixelmonItems.adamant_orb));
                        break;
                    case GIRATINA:
                        player.addItemStackToInventory(new ItemStack(PixelmonItems.griseous_orb));
                }

                orb = TimespaceAltarTileEntity.Orb.NONE;
            } else if (nbt.getBoolean("VisualChainIn")) {
                player.addItemStackToInventory(new ItemStack(PixelmonItems.red_chain));
                nbt.putBoolean("VisualChainIn", false);
            }
        } else {
            if (!tile.encounters.canEncounter(player)) {
                if (tile.encounters.getMode().isTimedAccess()) {
                    ChatHandler.sendChat(player, "pixelmon.blocks.shrine.today", new Object[0]);
                } else {
                    ChatHandler.sendChat(player, "pixelmon.blocks.shrine.encountered", new Object[0]);
                }
                return;
            }

            if (item.getItem() instanceof TimespaceOrbItem) {
                if (!nbt.getBoolean("VisualChainIn")) {
                    ChatHandler.sendChat(player, "pixelmon.blocks.timespace.needchain", new Object[0]);
                    return;
                }

                if (item.getItem() == PixelmonItems.lustrous_orb) {
                    orb = TimespaceAltarTileEntity.Orb.PALKIA;
                } else if (item.getItem() == PixelmonItems.adamant_orb) {
                    orb = TimespaceAltarTileEntity.Orb.DIALGA;
                } else if (item.getItem() == PixelmonItems.griseous_orb) {
                    orb = TimespaceAltarTileEntity.Orb.GIRATINA;
                } else {
                    orb = TimespaceAltarTileEntity.Orb.NONE;
                }
                nbt.putInt("OrbIn", orb.ordinal());
                item.shrink(1);
            } else if (item.getItem() == PixelmonItems.red_chain) {
                if (!nbt.getBoolean("VisualChainIn")) {
                    nbt.putBoolean("VisualChainIn", true);
                    item.shrink(1);
                } else {
                    ChatHandler.sendChat(player, "pixelmon.blocks.timespace.alreadychain", new Object[0]);
                    return;
                }
            } else {
                if (!nbt.getBoolean("VisualChainIn")) {
                    ChatHandler.sendChat(player, "pixelmon.blocks.timespace.needchain", new Object[0]);
                } else {
                    ChatHandler.sendChat(player, "pixelmon.blocks.timespace.notorb", new Object[0]);
                }
                return;
            }
        }
        nbt.putInt("OrbIn", orb.ordinal());
        new SUpdateTileEntityPacket(tile.getPos(), 0, nbt);
    }
     */
}