package icu.nyat.kusunoki.subtitle_highlight.Configuration;

import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.minecraft.text.TextColor;
import icu.nyat.kusunoki.subtitle_highlight.Utils.ColorCode;

import java.util.ArrayList;

public class Settings {
    public long MaximumDuration = 3000;
    public double StartRatio = 1;
    public double StopRatio = 0.29411764705882354;
    public int BackgroundColor = 0xcc000000;
    public float Scale = 1;
    public float MarginBottom = 30;
    public float MarginSide = 1;
    public Boolean iKun = true;
    public ColorSetting BasicColorSettings = new ColorSetting();
    public ArrayList<Custom> CustomizedList = new ArrayList<>();

    public class ColorSetting {
        public ColorCode Environment = ColorCode.DarkBlue;
        public Block block = new Block();
        //public ColorCode 魔咒 = ColorCode.Purple;
        public ColorCode Curse = ColorCode.Purple;
        public Entity entity = new Entity();
        public Item item = new Item();
        public ColorCode others = ColorCode.White;

        public class Item {
            public ColorCode weapon = ColorCode.Gold;
            public ColorCode armor = ColorCode.DarkGreen;
            public ColorCode tool = ColorCode.Blue;
            public ColorCode otherItems = ColorCode.White;
        }

        public class Block {
            public ColorCode general = ColorCode.White;
            public ColorCode interact = ColorCode.LightBlue;
            public ColorCode working = ColorCode.LakeBlue;
            //public ColorCode 运作 = ColorCode.LakeBlue;
            public ColorCode dangerousBlock = ColorCode.Red;
            public ColorCode crops = ColorCode.DarkGreen;
            public ColorCode otherBlocks = ColorCode.DarkGrey;
        }

        public class Entity {
            public Mob mob = new Mob();
            public ColorCode vehicle = ColorCode.Gray;
            public ColorCode Catapult = ColorCode.Gold;
            //public ColorCode 弹射物 = ColorCode.Gold;
            public ColorCode explosive = ColorCode.Red;
            public ColorCode decoration = ColorCode.Gray;
            public ColorCode OtherEntitys = ColorCode.DarkGrey;

            public class Mob {

                public Player player = new Player();
                public ColorCode passiveMobs = ColorCode.Green;
                public ColorCode neutralMobs = ColorCode.Yellow;
                public ColorCode hostileMobs = ColorCode.Red;
                public ColorCode boss = ColorCode.Pink;

                public class Player {
                    public ColorCode attack = ColorCode.Gold;
                    public ColorCode hurt = ColorCode.DarkRed;
                    public ColorCode otherPlayer = ColorCode.White;
                }
            }
        }
    }

    public class Custom {
        public String LocalizedKeyName = "";
        public TextColor Color = TextColor.fromRgb(0xffffff);
        public Boolean Random = false;
        public Boolean Bold = false;
        public Boolean Deleteline = false;
        public Boolean Underline = false;
        public Boolean italic = false;
    }
}
