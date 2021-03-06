package GU.api.color;

import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;

/**
 * An enum to standardize the coloring of my mod's colors
 */
public enum EnumColor {


    WHITE,
    ORANGE,
    MAGENTA,
    LIGHT_BLUE,
    YELLOW,
    LIME,
    PINK,
    GRAY,
    LIGHT_GREY,
    CYAN,
    PURPLE,
    BLUE,
    BROWN,
    GREEN,
    RED,
    BLACK,
    NONE;

    public static ItemStack getVinillaDye(EnumColor color) {
        
        switch(color) {
            
            case RED: return new ItemStack(Item.dyePowder.itemID, 1, 1);
            case GREEN: return new ItemStack(Item.dyePowder.itemID, 2, 2);
            case BROWN: return new ItemStack(Item.dyePowder.itemID, 1, 3);
            case BLUE: return new ItemStack(Item.dyePowder.itemID, 2, 4);
            case PURPLE: return new ItemStack(Item.dyePowder.itemID, 1, 5);
            case CYAN: return new ItemStack(Item.dyePowder.itemID, 2, 6);
            case LIGHT_GREY: return new ItemStack(Item.dyePowder.itemID, 1, 7);
            case GRAY: return new ItemStack(Item.dyePowder.itemID, 2, 8);
            case PINK: return new ItemStack(Item.dyePowder.itemID, 1, 9);
            case LIME: return new ItemStack(Item.dyePowder.itemID, 2, 10);
            case YELLOW: return new ItemStack(Item.dyePowder.itemID, 1, 11);
            case LIGHT_BLUE: return new ItemStack(Item.dyePowder.itemID, 2, 12);
            case MAGENTA: return new ItemStack(Item.dyePowder.itemID, 1, 13);
            case ORANGE: return new ItemStack(Item.dyePowder.itemID, 2, 14);
            case WHITE: return new ItemStack(Item.dyePowder.itemID, 1, 15);
            
            default : return null;
        }
    }
    
    public static EnumColor getItemColorValue(ItemStack item) {

        if(item != null){

            if(item.getItem() != null) {

                Item itemI = item.getItem();

                if(itemI instanceof ItemDye){

                    switch(item.getItemDamage()) {

                        case 1: return EnumColor.RED;
                        case 2: return EnumColor.GREEN;
                        case 3: return EnumColor.BROWN;
                        case 4: return EnumColor.BLUE;
                        case 5: return EnumColor.PURPLE;
                        case 6: return EnumColor.CYAN;
                        case 7: return EnumColor.LIGHT_GREY;
                        case 8: return EnumColor.GRAY;
                        case 9: return EnumColor.PINK;
                        case 10: return EnumColor.LIME;
                        case 11: return EnumColor.YELLOW;
                        case 12: return EnumColor.LIGHT_BLUE;
                        case 13: return EnumColor.MAGENTA;
                        case 14: return EnumColor.ORANGE;
                        case 15: return EnumColor.WHITE;
                    }
                }
            }
        }
        return EnumColor.NONE;        
    }
    
    public static EnumColor translateNumberToColor(int numb) {

        switch(numb) {

            case 0: return EnumColor.WHITE;
            case 1:return EnumColor.ORANGE;
            case 2:return EnumColor.MAGENTA;
            case 3:return EnumColor.LIGHT_BLUE;
            case 4:return EnumColor.YELLOW;
            case 5:return EnumColor.LIME;
            case 6:return EnumColor.PINK;
            case 7:return EnumColor.GRAY;
            case 8:return EnumColor.LIGHT_GREY;
            case 9:return EnumColor.CYAN;
            case 10:return EnumColor.PURPLE;
            case 11:return EnumColor.BLUE;
            case 12:return EnumColor.BROWN;
            case 13:return EnumColor.GREEN;
            case 14:return EnumColor.RED;
            case 15:return EnumColor.BLACK;
            default: return EnumColor.NONE;
        }
    }

    public static int translateColorToNumber(EnumColor color) {

        switch(color) {

            case WHITE: return 0;       
            case ORANGE: return 1;                
            case MAGENTA: return 2;
            case LIGHT_BLUE: return 3;            
            case YELLOW: return 4;            
            case LIME: return 5;            
            case PINK: return 6;
            case GRAY: return 7;
            case LIGHT_GREY: return 8;
            case CYAN: return 9;                
            case PURPLE: return 10;                
            case BLUE: return 11;
            case BROWN: return 12;
            case GREEN: return 13;
            case RED: return 14;
            case BLACK: return 15;

            case NONE: return -1;
            default: return -1;
        }
    }
}
