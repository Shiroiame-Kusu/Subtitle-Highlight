package icu.nyat.kusunoki.subtitle_highlight;

import icu.nyat.kusunoki.subtitle_highlight.Configuration.ConfigManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
@Environment(EnvType.CLIENT)
public class SubtitleHighlight implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ConfigManager.Load();
    }
}