package icu.nyat.kusunoki.subtitle_highlight;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import icu.nyat.kusunoki.subtitle_highlight.Configuration.ConfigManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.spongepowered.asm.mixin.Final;

@Environment(EnvType.CLIENT)
public class SubtitleHighlight implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        ConfigManager.Load();
    }

}
