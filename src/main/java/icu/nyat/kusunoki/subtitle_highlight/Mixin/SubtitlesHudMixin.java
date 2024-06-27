package icu.nyat.kusunoki.subtitle_highlight.Mixin;

import icu.nyat.kusunoki.subtitle_highlight.Configuration.ConfigManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.SubtitlesHud;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Iterator;

//import static net.minecraft.client.gui.hud.SubtitlesHud.SubtitleEntry;

@Environment(EnvType.CLIENT)
@Mixin(SubtitlesHud.class)
public class SubtitlesHudMixin {
    private static double DurationRatio;
    @Shadow
    @Final
    private MinecraftClient client;



    @Redirect(method = "render(Lnet/minecraft/client/gui/DrawContext;)V", at = @At(value = "INVOKE", target = "Ljava/util/Iterator;next()Ljava/lang/Object;", ordinal = 0))
    private Object FetchSubtitleEntries(Iterator Instance) {
        SubtitlesHud.SubtitleEntry subtitleEntry = (SubtitlesHud.SubtitleEntry) Instance.next();
        DurationRatio = (Util.getMeasuringTimeMs() - subtitleEntry.getTime()) / (double) (ConfigManager.Options.MaximumDuration);
        return subtitleEntry;
    }

    @Redirect(method = "render(Lnet/minecraft/client/gui/DrawContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SubtitlesHud$SubtitleEntry;getTime()J"))
    private long SubtitleTimeRedirect(SubtitlesHud.SubtitleEntry Instance) {
        return (long) (Instance.getTime() - 3000 * this.client.options.getNotificationDisplayTime().getValue() + ConfigManager.Options.MaximumDuration * this.client.options.getNotificationDisplayTime().getValue());
    }

    @ModifyVariable(method = "render(Lnet/minecraft/client/gui/DrawContext;)V", at = @At("STORE"), ordinal = 7)
    private int DirectionDisplayColorChanger(int OriginalValue) {
        return MathHelper.floor(MathHelper.clampedLerp(255 * ConfigManager.Options.StartRatio, 255 * ConfigManager.Options.StopRatio, DurationRatio));
    }

    @ModifyArgs(method = "render(Lnet/minecraft/client/gui/DrawContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;III)I"))
    private void SubtitleDisplayColorChanger(Args Parameters) {
        Text text = Parameters.get(1);
        Parameters.set(1, Text.literal(text.getString()).setStyle(text.getStyle().withColor((TextColor) null)));
        int Red, Green, Blue;
        if (text.getStyle().getColor() != null) {
            Red = (text.getStyle().getColor().getRgb() >> 16) & 255;
            Green = (text.getStyle().getColor().getRgb() >> 8) & 255;
            Blue = text.getStyle().getColor().getRgb() & 255;
        } else {
            Red = Green = Blue = 255;
        }
        int RedLeft = MathHelper.floor(MathHelper.clampedLerp(Red * ConfigManager.Options.StartRatio, Red * ConfigManager.Options.StopRatio, DurationRatio));
        int GreenLeft = MathHelper.floor(MathHelper.clampedLerp(Green * ConfigManager.Options.StartRatio, Green * ConfigManager.Options.StopRatio, DurationRatio));
        int BlueLeft = MathHelper.floor(MathHelper.clampedLerp(Blue * ConfigManager.Options.StartRatio, Blue * ConfigManager.Options.StopRatio, DurationRatio));
        Parameters.set(4, (RedLeft << 16 | GreenLeft << 8 | BlueLeft) - 16777216);
    }

    @ModifyArgs(method = "render(Lnet/minecraft/client/gui/DrawContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;translate(FFF)V"))
    private void LocationChanger(Args Parameters) {
        float Width = -((float) Parameters.get(0) - this.client.getWindow().getScaledWidth() + 2);
        float Height = -((float) Parameters.get(1) - this.client.getWindow().getScaledHeight() + 35);
        Parameters.set(0, this.client.getWindow().getScaledWidth() - (Width + 1) * Math.abs(ConfigManager.Options.Scale) - ConfigManager.Options.MarginSide);
        Parameters.set(1, this.client.getWindow().getScaledHeight() - (Height + 5) * Math.abs(ConfigManager.Options.Scale) - ConfigManager.Options.MarginBottom);
    }

    @ModifyArgs(method = "render(Lnet/minecraft/client/gui/DrawContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;scale(FFF)V"))
    private void ScaleChanger(Args Parameters) {
        Parameters.set(0, ConfigManager.Options.Scale);
        Parameters.set(1, ConfigManager.Options.Scale);
    }

    @ModifyArgs(method = "render(Lnet/minecraft/client/gui/DrawContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V"))
    private void FillChanger(Args Parameters) {
        Parameters.set(4, ConfigManager.Options.BackgroundColor);
    }
}
