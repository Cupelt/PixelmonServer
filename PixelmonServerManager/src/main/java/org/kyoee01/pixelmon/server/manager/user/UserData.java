package org.kyoee01.pixelmon.server.manager.user;


import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.util.UUID;

public class UserData {
    public UUID uuid;
    public File file;
    public YamlConfiguration yml;

    public UserData(UUID uuid) {
        this.uuid = uuid;
        this.file = new File("plugins/PixelmonServerData/User/"+uuid.toString()+".yml");
        this.yml = YamlConfiguration.loadConfiguration(this.file);
    }

    /*
    if you use UserData, inherits UserData Class in Your Class
     */
}
