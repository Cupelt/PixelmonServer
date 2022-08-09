package org.Kyoee01.PixelmonServer.sever;

import org.lwjgl.system.CallbackI;

public class Prefixes {
    private static String Server = "&f&l[&6&lSERVER&f&l] &f";
    private static String Error = "&c&l[&4&lERROR&c&l] &4";
    private static String Event = "&f&l[&e&lEVENT&f&l] &f";
    private static String Join = "&6&l[&a&lJoin&6&l] &f";
    private static String Quit = "&6&l[&4&lQuit&6&l] &f";

    public static String getServer(){
        return Server;
    }

    public static String getError() {
        return Error;
    }

    public static String getEvent() {
        return Event;
    }

    public static String getJoin() {
        return Join;
    }

    public static String getQuit() {
        return Quit;
    }
}
