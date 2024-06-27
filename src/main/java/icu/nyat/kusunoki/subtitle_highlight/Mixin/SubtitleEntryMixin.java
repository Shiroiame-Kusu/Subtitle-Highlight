package icu.nyat.kusunoki.subtitle_highlight.Mixin;

import icu.nyat.kusunoki.subtitle_highlight.Configuration.ConfigManager;
import icu.nyat.kusunoki.subtitle_highlight.Configuration.Settings;
import icu.nyat.kusunoki.subtitle_highlight.Utils.SplitKeyArrays;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.hud.SubtitlesHud;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(SubtitlesHud.SubtitleEntry.class)
public class SubtitleEntryMixin {
    @Shadow
    @Final
    private Text text;

    @Inject(at = @At("RETURN"), method = "getText()Lnet/minecraft/text/Text;", cancellable = true)
    private void SubtitleColor(CallbackInfoReturnable<Text> ReturnableInfo) {
        MutableText SubtitleText = ((MutableText) this.text).formatted(Formatting.RESET);
        if (SubtitleText.getContent() instanceof TranslatableTextContent) {
            for (Settings.Custom element : ConfigManager.Options.CustomizedList) {
                if (((TranslatableTextContent) SubtitleText.getContent()).getKey().equals(element.LocalizedKeyName)) {
                    ReturnableInfo.setReturnValue(SubtitleText.setStyle(SubtitleText.getStyle().withColor(element.Color).withObfuscated(element.Random).withBold(element.Bold).withStrikethrough(element.Deleteline).withUnderline(element.Underline).withItalic(element.italic)));
                    return;
                }
            }
            String[] KeySplit = ((TranslatableTextContent) SubtitleText.getContent()).getKey().split("\\.");
            if (KeySplit[0].equals("subtitles")) {
                switch (KeySplit[1]) {
                    case "ambient", "weather" -> {
                        ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.Environment.FetchFormatCode()));
                        return;
                    }
                    case "block" -> {
                        if (KeySplit[2].equals("generic")) {
                            ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.block.general.FetchFormatCode()));
                            return;
                        }
                        for (String element : SplitKeyArrays.interact) {
                            if (KeySplit[2].equals(element)) {
                                if ((KeySplit[2].equals("anvil") && KeySplit[3].equals("land")) || (KeySplit[2].equals("tripwire") && KeySplit[3].equals("click"))) {
                                    ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.block.dangerousBlock.FetchFormatCode()));
                                    return;
                                }
                                ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.block.interact.FetchFormatCode()));
                                return;
                            }
                        }
                        for (String element : SplitKeyArrays.working) {
                            if (KeySplit[2].equals(element)) {
                                if ((KeySplit[2].equals("beacon") && KeySplit[3].equals("power_select")) || (KeySplit[2].equals("beehive") && KeySplit[3].equals("shear"))) {
                                    ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.block.interact.FetchFormatCode()));
                                    return;
                                }
                                if (KeySplit[2].equals("pointed_dripstone") && (KeySplit[3].startsWith("drip_lava") || KeySplit[3].equals("land"))) {
                                    ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.block.dangerousBlock.FetchFormatCode()));
                                    return;
                                }
                                ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.block.working.FetchFormatCode()));
                                return;
                            }
                        }
                        for (String element : SplitKeyArrays.dangerousBlocks) {
                            if (KeySplit[2].equals(element)) {
                                ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.block.dangerousBlock.FetchFormatCode()));
                                return;
                            }
                        }
                        for (String element : SplitKeyArrays.crops) {
                            if (KeySplit[2].equals(element)) {
                                ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.block.crops.FetchFormatCode()));
                                return;
                            }
                        }
                        ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.block.otherBlocks.FetchFormatCode()));
                        return;
                    }
                    case "chiseled_bookshelf", "ui" -> {
                        ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.block.interact.FetchFormatCode()));
                        return;
                    }
                    case "enchant", "particle" -> {
                        ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.Curse.FetchFormatCode()));
                        return;
                    }
                    case "entity" -> {
                        if (KeySplit[2].equals("generic") || KeySplit[2].equals("player")) {
                            if (KeySplit[3].equals("attack")) {
                                ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.entity.mob.player.attack.FetchFormatCode()));
                                return;
                            }
                            for (String element : SplitKeyArrays.hurt) {
                                if (KeySplit[3].equals(element)) {
                                    ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.entity.mob.player.hurt.FetchFormatCode()));
                                    return;
                                }
                            }
                            ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.entity.mob.player.otherPlayer.FetchFormatCode()));
                            return;
                        }
                        for (String element : SplitKeyArrays.passiveMobs) {
                            if (KeySplit[2].equals(element)) {
                                if (KeySplit[2].equals("chicken") && ConfigManager.Options.iKun) {
                                    ReturnableInfo.setReturnValue(Text.translatable("subtitles.entity.kun." + KeySplit[3]).setStyle(SubtitleText.getStyle().withColor(TextColor.fromFormatting(Formatting.GRAY)).withBold(true)));
                                    return;
                                }
                                ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.entity.mob.passiveMobs.FetchFormatCode()));
                                return;
                            }
                        }
                        for (String element : SplitKeyArrays.neutralMobs) {
                            if (KeySplit[2].equals(element)) {
                                ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.entity.mob.neutralMobs.FetchFormatCode()));
                                return;
                            }
                        }
                        for (String element : SplitKeyArrays.hostileMobs) {
                            if (KeySplit[2].equals(element)) {
                                ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.entity.mob.hostileMobs.FetchFormatCode()));
                                return;
                            }
                        }
                        for (String element : SplitKeyArrays.boss) {
                            if (KeySplit[2].equals(element)) {
                                ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.entity.mob.boss.FetchFormatCode()));
                                return;
                            }
                        }
                        for (String element : SplitKeyArrays.vehicle) {
                            if (KeySplit[2].equals(element)) {
                                ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.entity.vehicle.FetchFormatCode()));
                                return;
                            }
                        }
                        for (String element : SplitKeyArrays.Catapult) {
                            if (KeySplit[2].equals(element)) {
                                ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.entity.Catapult.FetchFormatCode()));
                                return;
                            }
                        }
                        for (String element : SplitKeyArrays.explosive) {
                            if (KeySplit[2].equals(element)) {
                                ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.entity.explosive.FetchFormatCode()));
                                return;
                            }
                        }
                        for (String element : SplitKeyArrays.decoration) {
                            if (KeySplit[2].equals(element)) {
                                ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.entity.decoration.FetchFormatCode()));
                                return;
                            }
                        }
                        ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.entity.OtherEntitys.FetchFormatCode()));
                        return;
                    }
                    case "event" -> {
                        ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.entity.mob.hostileMobs.FetchFormatCode()));
                        return;
                    }
                    case "item" -> {
                        for (String element : SplitKeyArrays.weapon) {
                            if (KeySplit[2].equals(element)) {
                                ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.item.weapon.FetchFormatCode()));
                                return;
                            }
                        }
                        for (String element : SplitKeyArrays.armor) {
                            if (KeySplit[2].equals(element)) {
                                ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.item.armor.FetchFormatCode()));
                                return;
                            }
                        }
                        for (String element : SplitKeyArrays.tool) {
                            if (KeySplit[2].equals(element)) {
                                ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.item.tool.FetchFormatCode()));
                                return;
                            }
                        }
                        ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.item.otherItems.FetchFormatCode()));
                        return;
                    }
                }
            }
        }
        ReturnableInfo.setReturnValue(SubtitleText.formatted(ConfigManager.Options.BasicColorSettings.others.FetchFormatCode()));
    }
}
