package org.Kyoee01.pixelmon.server.manager.server.language;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.pixelmonmod.pixelmon.Pixelmon;
import net.minecraft.util.JSONUtils;

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
        InputStream pixelmonstream = Pixelmon.class.getResourceAsStream("/assets/pixelmon/lang/"+lang.toLowerCase()+".json");
        if (pixelmonstream.equals(null)) {
            pixelmonstream = Pixelmon.class.getResourceAsStream("/assets/pixelmon/lang/en_us.json");
        }
        streams.add(pixelmonstream);

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
        return this.LanguageMap.get(key);
    }
}
