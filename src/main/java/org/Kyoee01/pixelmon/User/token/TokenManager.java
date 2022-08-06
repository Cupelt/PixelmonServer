package org.Kyoee01.pixelmon.User.token;

import org.bukkit.entity.Entity;

import java.util.HashMap;
import java.util.UUID;

public class TokenManager {
    private static HashMap<UUID, UUID> Tags = new HashMap<>();

    public static HashMap<UUID, UUID> getTagEntities() {
        return Tags;
    }

    public static String EnumtoString(EnumToken token){
        switch (token){
            case NULL:
                return "&6";
        }
        return null;
    }
}
