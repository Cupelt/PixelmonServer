package org.Kyoee01.pixelmon.server.listeners;

import com.pixelmonmod.pixelmon.Pixelmon;
import org.Kyoee01.pixelmon.server.listeners.bukkit.InOutMessage;
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
        Bukkit.getPluginManager().registerEvents(new InOutMessage(), plugin);
    }
}
