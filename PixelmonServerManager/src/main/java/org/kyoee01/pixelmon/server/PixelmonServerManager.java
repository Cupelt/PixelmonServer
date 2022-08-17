package org.Kyoee01.pixelmon.server;


import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.Kyoee01.pixelmon.server.listeners.ListenerManager;
import org.Kyoee01.pixelmon.server.manager.server.commands.CommandManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.plugin.Plugin;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("pixelmonservermanager")
public class PixelmonServerManager {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public PixelmonServerManager() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(PixelmonServerManager::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(PixelmonServerManager::postInit);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) { //이벤트 등록
        ListenerManager.registerForgeEvents();
    }

    @SubscribeEvent
    public static void postInit(FMLLoadCompleteEvent event) { //이벤트 등록뒤 사용하는 이벤트
        LOGGER.info(ChatColor.translateAlternateColorCodes('&',
                "\n"+
                        "&4__________.__              .__                         &b   _________                                \n" +
                        "&4\\______   \\__|__  ___ ____ |  |   _____   ____   ____&b    /   _____/ ______________  __ ___________ \n" +
                        "&4 |     ___/  \\  \\/  // __ \\|  |  /     \\ /  _ \\ /    \\&b   \\_____  \\_/ __ \\_  __ \\  \\/ // __ \\_  __ \\\n" +
                        "&4 |    |   |  |>    <\\  ___/|  |_|  Y Y  (  <_> )   |  \\&b  /        \\  ___/|  | \\/\\   /\\  ___/|  | \\/\n" +
                        "&4 |____|   |__/__/\\_ \\\\___  >____/__|_|  /\\____/|___|  /&b /_______  /\\___  >__|    \\_/  \\___  >__|   \n" +
                        "&4                   \\/    \\/           \\/            \\/&b          \\/     \\/                 \\/       "));
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) { //레시피 등 기타 활동 정의

    }

    @SubscribeEvent
    public void onServerStarted(FMLServerStartedEvent event){
        Plugin plugin = Bukkit.getPluginManager().getPlugin("ListenEventManager");

        ListenerManager.registerBukkitEvents(plugin);

    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) { // 명령어 등록
        CommandManager.registerCommands(event);
    }
}