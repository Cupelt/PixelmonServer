package org.Kyoee01.pixelmon.translation;

import com.google.gson.JsonObject;
import net.minecraft.client.util.JSONException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class Translator {
    private final FileReader reader;
    private final JsonObject json;
    private final File file;

    public Translator(){
        try{
            this.file = new File(getClass().getResource("ko_kr.json").toURI());
            System.out.println(this.file.getAbsolutePath());
            this.reader = new FileReader(this.file);
            this.json = (JsonObject) new JSONParser().parse(this.reader);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public String GetTrans(String s){
        return this.json.get(s).toString();
    }
}
