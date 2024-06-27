package icu.nyat.kusunoki.subtitle_highlight.Configuration.ModMenu;

import icu.nyat.kusunoki.subtitle_highlight.Configuration.ConfigManager;
import icu.nyat.kusunoki.subtitle_highlight.Configuration.Settings;
import icu.nyat.kusunoki.subtitle_highlight.Utils.ColorCode;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.gui.entries.MultiElementListEntry;
import me.shedaniel.clothconfig2.gui.entries.NestedListListEntry;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Environment(EnvType.CLIENT)
public class ConfigurationAPI implements ModMenuApi{
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return (PreviousPage) -> {
            try {
                Identifier temp = Identifier.of("minecraft", "textures/block/white_concrete.png");
                ConfigBuilder Constructor = ConfigBuilder.create().setParentScreen(PreviousPage).setTitle(Text.translatable("subtitle_highlight.configure.title")).setDoesConfirmSave(false).setSavingRunnable(ConfigManager::Save).setDefaultBackgroundTexture(temp);
                Constructor.getOrCreateCategory(Text.translatable("subtitle_highlight.configure.general")).addEntry(Constructor.entryBuilder().startBooleanToggle(Text.translatable("options.showSubtitles"), MinecraftClient.getInstance().options.getShowSubtitles().getValue()).setTooltip(Text.translatable("subtitle_highlight.configure.general.option.tooltip")).setDefaultValue(false).setSaveConsumer((NewValue) -> {
                    MinecraftClient.getInstance().options.getShowSubtitles().setValue(NewValue);
                    MinecraftClient.getInstance().options.write();
                }).build()).addEntry(Constructor.entryBuilder().startDoubleField(Text.translatable("subtitle_highlight.configure.general.start_ratio"), ConfigManager.Options.StartRatio).setTooltip(Text.translatable("subtitle_highlight.configure.general.start_ratio.tooltip_1"), Text.translatable("subtitle_highlight.configure.general.start_ratio.tooltip_2")).setDefaultValue(1).setMax(1).setMin(0).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.StartRatio = NewValue;
                }).build()).addEntry(Constructor.entryBuilder().startDoubleField(Text.translatable("subtitle_highlight.configure.general.end_ratio"), ConfigManager.Options.StopRatio).setTooltip(Text.translatable("subtitle_highlight.configure.general.end_ratio.tooltip_1"), Text.translatable("subtitle_highlight.configure.general.end_ratio.tooltip_2")).setDefaultValue(0.29411764705882354).setMax(1).setMin(0).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.StopRatio = NewValue;
                }).build()).addEntry(Constructor.entryBuilder().startAlphaColorField(Text.translatable("subtitle_highlight.configure.general.background_color"), ConfigManager.Options.BackgroundColor).setTooltip(Text.translatable("subtitle_highlight.configure.general.background_color.tooltip_1"), Text.translatable("subtitle_highlight.configure.general.background_color.tooltip_2")).setDefaultValue(0xcc000000).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.BackgroundColor = NewValue;
                }).build()).addEntry(Constructor.entryBuilder().startFloatField(Text.translatable("subtitle_highlight.configure.general.scale"), ConfigManager.Options.Scale).setTooltip(Text.translatable("subtitle_highlight.configure.general.scale.tooltip_1"), Text.translatable("subtitle_highlight.configure.general.scale.tooltip_2")).setDefaultValue(1).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.Scale = NewValue;
                }).build()).addEntry(Constructor.entryBuilder().startFloatField(Text.translatable("subtitle_highlight.configure.general.bottom_margin"), ConfigManager.Options.MarginBottom).setTooltip(Text.translatable("subtitle_highlight.configure.general.bottom_margin.tooltip_1"), Text.translatable("subtitle_highlight.configure.general.bottom_margin.tooltip_2")).setDefaultValue(30).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.MarginBottom = NewValue;
                }).build()).addEntry(Constructor.entryBuilder().startFloatField(Text.translatable("subtitle_highlight.configure.general.side_margin"), ConfigManager.Options.MarginSide).setTooltip(Text.translatable("subtitle_highlight.configure.general.side_margin.tooltip_1"), Text.translatable("subtitle_highlight.configure.general.side_margin.tooltip_2")).setDefaultValue(1).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.MarginSide = NewValue;
                }).build()).addEntry(Constructor.entryBuilder().startBooleanToggle(Text.translatable("subtitle_highlight.configure.general.ikun"), ConfigManager.Options.iKun).setTooltip(Text.translatable("subtitle_highlight.configure.general.ikun.tooltip_1"), Text.translatable("subtitle_highlight.configure.general.ikun.tooltip_2")).setDefaultValue(true).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.iKun = NewValue;
                }).build()).setDescription(new MutableText[]{Text.translatable("subtitle_highlight.configure.general.description")});
                SubCategoryBuilder BasicColorSettings = Constructor.entryBuilder().startSubCategory(Text.translatable("subtitle_highlight.configure.general.color")).setTooltip(Text.translatable("subtitle_highlight.configure.general.color.tooltip")).setExpanded(true);
                BasicColorSettings.add(Constructor.entryBuilder().startEnumSelector(Text.translatable("subtitle_highlight.configure.general.color.ambient"), ColorCode.class, ConfigManager.Options.BasicColorSettings.Environment).setDefaultValue(ColorCode.DarkBlue).setEnumNameProvider(ColorCode::ColorCodeTranslate).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.BasicColorSettings.Environment = NewValue;
                }).build());
                SubCategoryBuilder block = Constructor.entryBuilder().startSubCategory(Text.translatable("subtitle_highlight.configure.general.color.block")).setExpanded(true);
                block.add(Constructor.entryBuilder().startEnumSelector(Text.translatable("subtitle_highlight.configure.general.color.block.generic"), ColorCode.class, ConfigManager.Options.BasicColorSettings.block.general).setDefaultValue(ColorCode.White).setEnumNameProvider(ColorCode::ColorCodeTranslate).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.BasicColorSettings.block.general = NewValue;
                }).build());
                block.add(Constructor.entryBuilder().startEnumSelector(Text.translatable("subtitle_highlight.configure.general.color.block.interact"), ColorCode.class, ConfigManager.Options.BasicColorSettings.block.interact).setDefaultValue(ColorCode.LightBlue).setEnumNameProvider(ColorCode::ColorCodeTranslate).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.BasicColorSettings.block.interact = NewValue;
                }).build());
                block.add(Constructor.entryBuilder().startEnumSelector(Text.translatable("subtitle_highlight.configure.general.color.block.working"), ColorCode.class, ConfigManager.Options.BasicColorSettings.block.working).setDefaultValue(ColorCode.LakeBlue).setEnumNameProvider(ColorCode::ColorCodeTranslate).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.BasicColorSettings.block.working = NewValue;
                }).build());
                block.add(Constructor.entryBuilder().startEnumSelector(Text.translatable("subtitle_highlight.configure.general.color.block.dangerous"), ColorCode.class, ConfigManager.Options.BasicColorSettings.block.dangerousBlock).setDefaultValue(ColorCode.Red).setEnumNameProvider(ColorCode::ColorCodeTranslate).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.BasicColorSettings.block.dangerousBlock = NewValue;
                }).build());
                block.add(Constructor.entryBuilder().startEnumSelector(Text.translatable("subtitle_highlight.configure.general.color.block.crop"), ColorCode.class, ConfigManager.Options.BasicColorSettings.block.crops).setDefaultValue(ColorCode.DarkGreen).setEnumNameProvider(ColorCode::ColorCodeTranslate).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.BasicColorSettings.block.crops = NewValue;
                }).build());
                block.add(Constructor.entryBuilder().startEnumSelector(Text.translatable("subtitle_highlight.configure.general.color.block.other"), ColorCode.class, ConfigManager.Options.BasicColorSettings.block.otherBlocks).setDefaultValue(ColorCode.DarkGrey).setEnumNameProvider(ColorCode::ColorCodeTranslate).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.BasicColorSettings.block.otherBlocks = NewValue;
                }).build());
                BasicColorSettings.add(block.build());
                BasicColorSettings.add(Constructor.entryBuilder().startEnumSelector(Text.translatable("subtitle_highlight.configure.general.color.enchant"), ColorCode.class, ConfigManager.Options.BasicColorSettings.Curse).setDefaultValue(ColorCode.Purple).setEnumNameProvider(ColorCode::ColorCodeTranslate).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.BasicColorSettings.Curse = NewValue;
                }).build());
                SubCategoryBuilder entity = Constructor.entryBuilder().startSubCategory(Text.translatable("subtitle_highlight.configure.general.color.entity")).setExpanded(true);
                SubCategoryBuilder mob = Constructor.entryBuilder().startSubCategory(Text.translatable("subtitle_highlight.configure.general.color.entity.mob")).setExpanded(true);
                SubCategoryBuilder player = Constructor.entryBuilder().startSubCategory(Text.translatable("subtitle_highlight.configure.general.color.entity.mob.player")).setExpanded(true);
                player.add(Constructor.entryBuilder().startEnumSelector(Text.translatable("subtitle_highlight.configure.general.color.entity.mob.player.attack"), ColorCode.class, ConfigManager.Options.BasicColorSettings.entity.mob.player.attack).setDefaultValue(ColorCode.Gold).setEnumNameProvider(ColorCode::ColorCodeTranslate).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.BasicColorSettings.entity.mob.player.attack = NewValue;
                }).build());
                player.add(Constructor.entryBuilder().startEnumSelector(Text.translatable("subtitle_highlight.configure.general.color.entity.mob.player.hurt"), ColorCode.class, ConfigManager.Options.BasicColorSettings.entity.mob.player.hurt).setDefaultValue(ColorCode.DarkRed).setEnumNameProvider(ColorCode::ColorCodeTranslate).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.BasicColorSettings.entity.mob.player.hurt = NewValue;
                }).build());
                player.add(Constructor.entryBuilder().startEnumSelector(Text.translatable("subtitle_highlight.configure.general.color.entity.mob.player.other"), ColorCode.class, ConfigManager.Options.BasicColorSettings.entity.mob.player.otherPlayer).setDefaultValue(ColorCode.White).setEnumNameProvider(ColorCode::ColorCodeTranslate).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.BasicColorSettings.entity.mob.player.otherPlayer = NewValue;
                }).build());
                mob.add(player.build());
                mob.add(Constructor.entryBuilder().startEnumSelector(Text.translatable("subtitle_highlight.configure.general.color.entity.mob.passive"), ColorCode.class, ConfigManager.Options.BasicColorSettings.entity.mob.passiveMobs).setDefaultValue(ColorCode.Green).setEnumNameProvider(ColorCode::ColorCodeTranslate).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.BasicColorSettings.entity.mob.passiveMobs = NewValue;
                }).build());
                mob.add(Constructor.entryBuilder().startEnumSelector(Text.translatable("subtitle_highlight.configure.general.color.entity.mob.neutral"), ColorCode.class, ConfigManager.Options.BasicColorSettings.entity.mob.neutralMobs).setDefaultValue(ColorCode.Yellow).setEnumNameProvider(ColorCode::ColorCodeTranslate).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.BasicColorSettings.entity.mob.neutralMobs = NewValue;
                }).build());
                mob.add(Constructor.entryBuilder().startEnumSelector(Text.translatable("subtitle_highlight.configure.general.color.entity.mob.hostile"), ColorCode.class, ConfigManager.Options.BasicColorSettings.entity.mob.hostileMobs).setDefaultValue(ColorCode.Red).setEnumNameProvider(ColorCode::ColorCodeTranslate).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.BasicColorSettings.entity.mob.hostileMobs = NewValue;
                }).build());
                mob.add(Constructor.entryBuilder().startEnumSelector(Text.translatable("subtitle_highlight.configure.general.color.entity.mob.boss"), ColorCode.class, ConfigManager.Options.BasicColorSettings.entity.mob.boss).setDefaultValue(ColorCode.Pink).setEnumNameProvider(ColorCode::ColorCodeTranslate).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.BasicColorSettings.entity.mob.boss = NewValue;
                }).build());
                entity.add(mob.build());
                entity.add(Constructor.entryBuilder().startEnumSelector(Text.translatable("subtitle_highlight.configure.general.color.entity.vehicle"), ColorCode.class, ConfigManager.Options.BasicColorSettings.entity.vehicle).setDefaultValue(ColorCode.Gray).setEnumNameProvider(ColorCode::ColorCodeTranslate).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.BasicColorSettings.entity.vehicle = NewValue;
                }).build());
                entity.add(Constructor.entryBuilder().startEnumSelector(Text.translatable("subtitle_highlight.configure.general.color.entity.projectile"), ColorCode.class, ConfigManager.Options.BasicColorSettings.entity.Catapult).setDefaultValue(ColorCode.Gold).setEnumNameProvider(ColorCode::ColorCodeTranslate).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.BasicColorSettings.entity.Catapult = NewValue;
                }).build());
                entity.add(Constructor.entryBuilder().startEnumSelector(Text.translatable("subtitle_highlight.configure.general.color.entity.explosive"), ColorCode.class, ConfigManager.Options.BasicColorSettings.entity.explosive).setDefaultValue(ColorCode.Red).setEnumNameProvider(ColorCode::ColorCodeTranslate).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.BasicColorSettings.entity.explosive = NewValue;
                }).build());
                entity.add(Constructor.entryBuilder().startEnumSelector(Text.translatable("subtitle_highlight.configure.general.color.entity.decoration"), ColorCode.class, ConfigManager.Options.BasicColorSettings.entity.decoration).setDefaultValue(ColorCode.Gray).setEnumNameProvider(ColorCode::ColorCodeTranslate).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.BasicColorSettings.entity.decoration = NewValue;
                }).build());
                entity.add(Constructor.entryBuilder().startEnumSelector(Text.translatable("subtitle_highlight.configure.general.color.entity.other"), ColorCode.class, ConfigManager.Options.BasicColorSettings.entity.OtherEntitys).setDefaultValue(ColorCode.DarkGrey).setEnumNameProvider(ColorCode::ColorCodeTranslate).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.BasicColorSettings.entity.OtherEntitys = NewValue;
                }).build());
                BasicColorSettings.add(entity.build());
                SubCategoryBuilder item = Constructor.entryBuilder().startSubCategory(Text.translatable("subtitle_highlight.configure.general.color.item")).setExpanded(true);
                item.add(Constructor.entryBuilder().startEnumSelector(Text.translatable("subtitle_highlight.configure.general.color.item.weapon"), ColorCode.class, ConfigManager.Options.BasicColorSettings.item.weapon).setDefaultValue(ColorCode.Gold).setEnumNameProvider(ColorCode::ColorCodeTranslate).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.BasicColorSettings.item.weapon = NewValue;
                }).build());
                item.add(Constructor.entryBuilder().startEnumSelector(Text.translatable("subtitle_highlight.configure.general.color.item.armor"), ColorCode.class, ConfigManager.Options.BasicColorSettings.item.armor).setDefaultValue(ColorCode.DarkGreen).setEnumNameProvider(ColorCode::ColorCodeTranslate).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.BasicColorSettings.item.armor = NewValue;
                }).build());
                item.add(Constructor.entryBuilder().startEnumSelector(Text.translatable("subtitle_highlight.configure.general.color.item.tool"), ColorCode.class, ConfigManager.Options.BasicColorSettings.item.tool).setDefaultValue(ColorCode.Blue).setEnumNameProvider(ColorCode::ColorCodeTranslate).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.BasicColorSettings.item.tool = NewValue;
                }).build());
                item.add(Constructor.entryBuilder().startEnumSelector(Text.translatable("subtitle_highlight.configure.general.color.item.other"), ColorCode.class, ConfigManager.Options.BasicColorSettings.item.otherItems).setDefaultValue(ColorCode.White).setEnumNameProvider(ColorCode::ColorCodeTranslate).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.BasicColorSettings.item.otherItems = NewValue;
                }).build());
                BasicColorSettings.add(item.build());
                BasicColorSettings.add(Constructor.entryBuilder().startEnumSelector(Text.translatable("subtitle_highlight.configure.general.color.other"), ColorCode.class, ConfigManager.Options.BasicColorSettings.others).setDefaultValue(ColorCode.White).setEnumNameProvider(ColorCode::ColorCodeTranslate).setSaveConsumer((NewValue) -> {
                    ConfigManager.Options.BasicColorSettings.others = NewValue;
                }).build());
                Constructor.getOrCreateCategory(Text.translatable("subtitle_highlight.configure.general")).addEntry(BasicColorSettings.build());
                Constructor.getOrCreateCategory(Text.translatable("subtitle_highlight.configure.custom")).addEntry(new NestedListListEntry<>(Text.translatable("subtitle_highlight.configure.custom.list"), ConfigManager.Options.CustomizedList, true, Optional::empty, (NewValue) -> {
                    ConfigManager.Options.CustomizedList = (ArrayList<Settings.Custom>) NewValue;
                }, List::of, Constructor.entryBuilder().getResetButtonKey(), true, true, (element, entry) -> {
                    Settings.Custom CustomizationOptions = element == null ? ConfigManager.Options.new Custom() : element;
                    return new MultiElementListEntry<>(Text.translatable("subtitle_highlight.configure.custom.entry"), CustomizationOptions, List.of(Constructor.entryBuilder().startStrField(Text.translatable("subtitle_highlight.configure.custom.entry.translation_key"), CustomizationOptions.LocalizedKeyName).setTooltip(Text.translatable("subtitle_highlight.configure.custom.entry.translation_key.tooltip")).setDefaultValue("").setSaveConsumer((NewValue) -> {
                        CustomizationOptions.LocalizedKeyName = NewValue;
                    }).build(), Constructor.entryBuilder().startColorField(Text.translatable("subtitle_highlight.configure.custom.entry.color"), CustomizationOptions.Color).setTooltip(Text.translatable("subtitle_highlight.configure.custom.entry.color.tooltip_1"), Text.translatable("subtitle_highlight.configure.custom.entry.color.tooltip_2")).setDefaultValue(TextColor.fromRgb(0xffffff)).setSaveConsumer((NewValue) -> {
                        CustomizationOptions.Color = TextColor.fromRgb(NewValue);
                    }).build(), Constructor.entryBuilder().startBooleanToggle(Text.translatable("formatting_code.obfuscated"), CustomizationOptions.Random).setDefaultValue(false).setSaveConsumer((NewValue) -> {
                        CustomizationOptions.Random = NewValue;
                    }).build(), Constructor.entryBuilder().startBooleanToggle(Text.translatable("formatting_code.bold"), CustomizationOptions.Bold).setDefaultValue(false).setSaveConsumer((NewValue) -> {
                        CustomizationOptions.Bold = NewValue;
                    }).build(), Constructor.entryBuilder().startBooleanToggle(Text.translatable("formatting_code.strikethrough"), CustomizationOptions.Deleteline).setDefaultValue(false).setSaveConsumer((NewValue) -> {
                        CustomizationOptions.Deleteline = NewValue;
                    }).build(), Constructor.entryBuilder().startBooleanToggle(Text.translatable("formatting_code.underline"), CustomizationOptions.Underline).setDefaultValue(false).setSaveConsumer((NewValue) -> {
                        CustomizationOptions.Underline = NewValue;
                    }).build(), Constructor.entryBuilder().startBooleanToggle(Text.translatable("formatting_code.italic"), CustomizationOptions.italic).setDefaultValue(false).setSaveConsumer((NewValue) -> {
                        CustomizationOptions.italic = NewValue;
                    }).build()), true);
                }));
                Constructor.getOrCreateCategory(Text.translatable("subtitle_highlight.configure.custom")).setDescription(new MutableText[]{Text.translatable("subtitle_highlight.configure.custom.description")});
                return Constructor.build();
            } catch (NullPointerException e) {
                ConfigManager.Options = new Settings();
                ConfigManager.Save();
                return getModConfigScreenFactory().create(PreviousPage);
            }
        };
    }
}
