package org.Kyoee01.pixelmon.server.manager.server.language;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.pixelmonmod.pixelmon.Pixelmon;
import net.minecraft.util.JSONUtils;
import org.Kyoee01.pixelmon.server.PixelmonServerManager;
import org.lwjgl.system.CallbackI;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;

public class LanguageManager {

    private final HashMap<String, String> LanguageMap = new HashMap<>();
    private LanguageManager(String lang){
        Pattern NUMERIC_VARIABLE_PATTERN = Pattern.compile("%(\\d+\\$)?[\\d.]*[df]");
        List<InputStream> streams = new ArrayList<>();

        String[] langPath = {"/assets/pixelmon/lang/", "/assets/server/lang/"};

        for (String path : langPath){
            InputStream inputStream;
            inputStream = Pixelmon.class.getResourceAsStream(path+lang.toLowerCase()+".json");
            if (inputStream == null)
                inputStream = Pixelmon.class.getResourceAsStream(path+"en_us.json");
            streams.add(inputStream);
        }

        for (InputStream langstream : streams){
            JsonObject langJsonObject = new Gson().fromJson(new InputStreamReader(langstream, StandardCharsets.UTF_8), JsonObject.class);
            Iterator var3 = langJsonObject.entrySet().iterator();

            while(var3.hasNext()) {
                Map.Entry<String, JsonElement> entry = (Map.Entry)var3.next();
                String s = NUMERIC_VARIABLE_PATTERN.matcher(JSONUtils.getString((JsonElement)entry.getValue(), (String)entry.getKey())).replaceAll("%$1s");
                LanguageMap.put(entry.getKey(), s);
            }
        }
    }

    public static LanguageManager getInstance(String lang){
        return new LanguageManager(lang);
    }
    public String getTranslateStringKey(String key){
        return this.LanguageMap.getOrDefault(key, key);
    }
}