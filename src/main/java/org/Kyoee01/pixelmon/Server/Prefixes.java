package org.Kyoee01.pixelmon.Server;

public class Prefixes {

    private final String Server;
    private final String Error;
    private final String Event;
    private final String Quit;
    private final String Join;

    public Prefixes(){
        this.Server = "&f&l[&6&lSERVER&f&l] &f";
        this.Error = "&c&l[&4&lERROR&c&l] &4";
        this.Event = "&f&l[&e&lEVENT&f&l] &f";
        this.Join = "&6&l[&a&lJoin&6&l] &f";
        this.Quit = "&6&l[&4&lQuit&6&l] &f";
    }

    public String getServer() {
        return Server;
    }
    public String getError() {
        return Error;
    }

    public String getEvent() {
        return Event;
    }

    public String getJoin() {
        return Join;
    }

    public String getQuit() {
        return Quit;
    }
}
