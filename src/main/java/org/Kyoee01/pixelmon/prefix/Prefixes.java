package org.Kyoee01.pixelmon.prefix;

public class Prefixes {

    private final String Server;
    private final String Error;

    public Prefixes(){
        this.Server = "&f&l[&6&lSERVER&f&l] &f";
        this.Error = "&c&l[&4&lERROR&f&l] &4";
    }

    public String getServer() {
        return Server;
    }

    public String getError() {
        return Error;
    }
}
