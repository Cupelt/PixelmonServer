package org.Kyoee01.pixelmon.User;

import org.Kyoee01.pixelmon.User.token.EnumToken;
import org.Kyoee01.pixelmon.User.token.TokenManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class UserData {
    private UUID uuid;
    private File file;
    private YamlConfiguration yml;
    private String token;
    private List<String> tokenlist;

    public UserData(UUID uuid) {
        this.uuid = uuid;
        this.file = new File("plugins/PixelmonServerData/User/"+uuid.toString()+".yml");
        this.yml = YamlConfiguration.loadConfiguration(this.file);
        this.token = this.yml.getString("token");
        this.tokenlist = this.yml.getStringList("tokenlist");
    }

    public boolean addToken(EnumToken token){ // true is successful, false is failed
        if (tokenlist.contains(token.name())){
            return false;
        }
        else{
            tokenlist.add(token.name());
            yml.set("tokenlist", tokenlist);
            try {
                yml.save(this.file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    public void removeToken(EnumToken token){
        tokenlist.remove(token.name());
    }

    public String getToken() {
        return token;
    }

    public List<String> getTokenlist() {
        return tokenlist;
    }

    public void setToken(EnumToken token) {
        this.token = token.name();
        yml.set("token", token.name());
        try {
            yml.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bukkit.getEntity(TokenManager.getTagEntities().get(this.uuid)).setCustomName(ChatColor.translateAlternateColorCodes('&',
                TokenManager.EnumtoString(EnumToken.valueOf(this.token))));
    }
}
