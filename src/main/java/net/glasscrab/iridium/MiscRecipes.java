package net.glasscrab.iridium;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.*;
import org.bukkit.plugin.Plugin;

public class MiscRecipes {

    private final Plugin plugin;

    public MiscRecipes(Plugin plugin){
        this.plugin = plugin;
    }

    public ShapedRecipe ElytraShapedRecipe(){
        NamespacedKey key = new NamespacedKey(plugin, "elytra");
        ShapedRecipe recipe = new ShapedRecipe(key,new ItemStack(Material.ELYTRA,1));
        recipe.shape("pcp","pcp","e e");
        recipe.setIngredient('p',Material.PHANTOM_MEMBRANE);
        recipe.setIngredient('c',Material.POPPED_CHORUS_FRUIT);
        recipe.setIngredient('e',Material.END_ROD);
        return recipe;
    }

    public ShapedRecipe NetheriteSmithingTemplateShapedRecipe(){
        NamespacedKey key = new NamespacedKey(plugin, "netherite_smithing_template");
        ShapedRecipe recipe = new ShapedRecipe(key,new ItemStack(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE,1));
        recipe.shape("ddd","dnd","ddd");
        recipe.setIngredient('d',Material.DIAMOND);
        recipe.setIngredient('n',Material.NETHERRACK);
        return recipe;
    }

    public ShapedRecipe SaddleShapedRecipe(){
        NamespacedKey key = new NamespacedKey(plugin, "saddle");
        ShapedRecipe recipe = new ShapedRecipe(key,new ItemStack(Material.SADDLE,1));
        recipe.shape("lll","c c");
        recipe.setIngredient('l',Material.LEATHER);
        recipe.setIngredient('c',Material.CHAIN);
        return recipe;
    }

    public ShapedRecipe DiamondHorseArmorShapedRecipe(){
        NamespacedKey key = new NamespacedKey(plugin, "diamond_horse_armor");
        ShapedRecipe recipe = new ShapedRecipe(key,new ItemStack(Material.DIAMOND_HORSE_ARMOR,1));
        recipe.shape("  d","ddd","d d");
        recipe.setIngredient('d',Material.DIAMOND);
        return recipe;
    }

    public ShapedRecipe IronHorseArmorShapedRecipe(){
        NamespacedKey key = new NamespacedKey(plugin, "iron_horse_armor");
        ShapedRecipe recipe = new ShapedRecipe(key,new ItemStack(Material.IRON_HORSE_ARMOR,1));
        recipe.shape("  d","ddd","d d");
        recipe.setIngredient('d',Material.IRON_INGOT);
        return recipe;
    }

    public ShapedRecipe GoldenHorseArmorShapedRecipe(){
        NamespacedKey key = new NamespacedKey(plugin, "golden_horse_armor");
        ShapedRecipe recipe = new ShapedRecipe(key,new ItemStack(Material.GOLDEN_HORSE_ARMOR,1));
        recipe.shape("  d","ddd","d d");
        recipe.setIngredient('d',Material.GOLD_INGOT);
        return recipe;
    }

    public ShapedRecipe GildedBlackstoneShapedRecipe(){
        NamespacedKey key = new NamespacedKey(plugin, "gilded_blackstone");
        ShapedRecipe recipe = new ShapedRecipe(key,new ItemStack(Material.GILDED_BLACKSTONE,8));
        recipe.shape("bbb","bgb","bbb");
        recipe.setIngredient('b',Material.BLACKSTONE);
        recipe.setIngredient('g',Material.GOLD_INGOT);
        return recipe;
    }

    public ShapedRecipe CobwebShapedRecipe(){
        NamespacedKey key = new NamespacedKey(plugin, "cobweb");
        ShapedRecipe recipe = new ShapedRecipe(key,new ItemStack(Material.COBWEB,1));
        recipe.shape("c c"," c ","c c");
        recipe.setIngredient('c',Material.STRING);
        return recipe;
    }

    public FurnaceRecipe CalciteFurnaceRecipe(){
        NamespacedKey key = new NamespacedKey(plugin, "calcite");
        FurnaceRecipe recipe = new FurnaceRecipe(key, new ItemStack(Material.CALCITE), new RecipeChoice.MaterialChoice(Material.DIORITE),1f,200);
        return recipe;
    }

    public FurnaceRecipe TuffFurnaceRecipe(){
        NamespacedKey key = new NamespacedKey(plugin, "tuff");
        FurnaceRecipe recipe = new FurnaceRecipe(key, new ItemStack(Material.TUFF), new RecipeChoice.MaterialChoice(Material.ANDESITE),1f,200);
        return recipe;
    }

    public FurnaceRecipe DarkPrismarineFurnaceRecipe(){
        NamespacedKey key = new NamespacedKey(plugin, "dark_prismarine");
        FurnaceRecipe recipe = new FurnaceRecipe(key, new ItemStack(Material.DARK_PRISMARINE), new RecipeChoice.MaterialChoice(Material.PRISMARINE_BRICKS),1f,200);
        return recipe;
    }
}
