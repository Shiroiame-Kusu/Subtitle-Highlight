package icu.nyat.kusunoki.subtitle_highlight.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
public class ConfigManager {
    public static final File ConfigFile = new File(FabricLoader.getInstance().getConfigDir().toFile().getPath(), "subtitle_highlight.json");
    public static Settings Options;
    public static Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().setPrettyPrinting().create();
    public static void Save() {
        try (FileWriter fileWriter = new FileWriter(ConfigFile, StandardCharsets.UTF_8, false)) {
            fileWriter.write(gson.toJson(Options));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void Load() {
        if (!ConfigFile.exists() || !ConfigFile.isFile()) {
            ConfigFile.delete();
            try {
                ConfigFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Options = new Settings();
            Save();
        } else {
            try (FileReader fileReader = new FileReader(ConfigFile, StandardCharsets.UTF_8)) {
                char[] content = new char[(int) ConfigFile.length()];
                fileReader.read(content);
                try {
                    Options = gson.fromJson(new String(content).trim(), Settings.class);
                } catch (JsonSyntaxException e) {
                    Options = new Settings();
                    Save();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
