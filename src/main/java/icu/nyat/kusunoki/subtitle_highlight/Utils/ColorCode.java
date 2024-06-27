package icu.nyat.kusunoki.subtitle_highlight.Utils;

import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public enum ColorCode {
    Black('0'), DarkBlue('1'), DarkGreen('2'), LakeBlue('3'), DarkRed('4'), Purple('5'), Gold('6'), Gray('7'), DarkGrey('8'), Blue('9'), Green('a'), LightBlue('b'), Red('c'), Pink('d'), Yellow('e'), White('f');
    private final Formatting FormatCode;

    ColorCode(char colorCode) {
        this.FormatCode = Formatting.byCode(colorCode);
    }

    public static Text FormatCodeTranslate(Formatting colorCode) {
        return Text.translatable("formatting_code." + colorCode.getName()).formatted(colorCode);
    }

    public static Text ColorCodeTranslate(Enum<ColorCode> type) {
        if (type instanceof ColorCode colorCode) {
            return FormatCodeTranslate(colorCode.FetchFormatCode());
        } else {
            return Text.literal("????");
        }
    }

    public Formatting FetchFormatCode() {
        return this.FormatCode;
    }
}