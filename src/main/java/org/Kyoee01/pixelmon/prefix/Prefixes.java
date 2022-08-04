package org.Kyoee01.pixelmon.prefix;

public class Prefixes {

    private final String Server;
    private final String Error;
    private final String Event;

    public Prefixes(){
        this.Server = "&f&l[&6&lSERVER&f&l] &f";
        this.Error = "&c&l[&4&lERROR&c&l] &4";
        this.Event = "&f&l[&e&lEVENT&f&l] &f";
    }

    public String getServer() {
        return Server;
    }

    public String getError() {
        return Error;
    }
}
