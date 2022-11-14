package org.kyoee01.pixelmon.visual;

import com.pixelmonmod.pixelmon.Pixelmon;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kyoee01.pixelmon.visual.summon.summonHendler;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("piexlmonvisualupgrader")
public class PixelmonVisualUpgrader {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "piexlmonvisualupgrader";
    public static boolean IsEnabled = true;

    public PixelmonVisualUpgrader() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(PixelmonVisualUpgrader::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(PixelmonVisualUpgrader::postInit);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new summonHendler());
    }

    @SubscribeEvent
    public static void postInit(FMLLoadCompleteEvent event) {

    }


    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {

    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {

    }
}
