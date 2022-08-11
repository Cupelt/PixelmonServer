package org.Kyoee01.pixelmon.server.manager.user.token;

import java.util.HashMap;
import java.util.UUID;

public class TokenManager {
    private static HashMap<UUID, UUID> Tags = new HashMap<>();

    public static HashMap<UUID, UUID> getTagEntities() {
        return Tags;
    }

    public static String EnumtoString(TokenEnum token) {
        switch (token) {
            case NONE:
                return "&6";
            case OWNER:
                return "&5Owner";
            case ADMIN:
                return "&4Admin";
            case MORDERATOR:
                return "&eMORDERATOR";
        }
        return null;
    }
}