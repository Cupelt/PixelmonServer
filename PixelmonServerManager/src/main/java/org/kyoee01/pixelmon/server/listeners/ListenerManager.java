package org.Kyoee01.pixelmon.server.listeners;

import com.pixelmonmod.pixelmon.Pixelmon;
import net.dv8tion.jda.api.JDABuilder;
import org.Kyoee01.pixelmon.server.listeners.discord.PingPong;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class ListenerManager {
    public static void registerForgeEvents(){
        /*
            Forge Event register here - evnet = Instance [Ex) Pixelmon.EVENT_BUS.register(new ExampleEvent())]
            Pixelmon.EVENT_BUS.register(event);
         */
    }

    public static void registerBukkitEvents(Plugin plugin){
        /*
            Bukkit Event register here - evnet = Listener [Ex) Bukkit.getPluginManager().registerEvents(new ExampleEvent(), plugin)]
            Bukkit.getPluginManager().registerEvents(event, plugin);
         */
    }

    public static JDABuilder registerDiscordEvents(JDABuilder builder){
        /*
            Bukkit Event register here - evnet = Instance [Ex) Bukkit.getPluginManager().registerEvents(new ExampleEvent(), plugin)]

         */
        builder.addEventListeners(new PingPong());
        return builder;
    }
}
