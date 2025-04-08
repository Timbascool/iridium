package net.glasscrab.iridium;

import net.glasscrab.iridium.commands.ToggleRatCommand;
import net.glasscrab.iridium.farming.*;
import net.glasscrab.iridium.iridium.IridiumArmorVanishEvent;
import net.glasscrab.iridium.iridium.IridiumDropEvent;
import net.glasscrab.iridium.iridium.IridiumPistonCraftEvent;
import net.glasscrab.iridium.iridium.IridiumRecipes;
import net.glasscrab.iridium.opal.OpalDropEvent;
import net.glasscrab.iridium.opal.OpalRecipies;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Method;

public final class Iridium extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getServer().getPluginManager().registerEvents(new IridiumDropEvent(new RNGManager(),new ItemManager(this)), this);
        this.getServer().getPluginManager().registerEvents(new IridiumPistonCraftEvent(new ItemManager(this),this) ,this);
        this.getServer().getPluginManager().registerEvents(new OpalDropEvent(new RNGManager(), new ItemManager(this)),this);
        this.getServer().getPluginManager().registerEvents(new OpalRecipies(new ItemManager(this),this),this);
        this.getServer().getPluginManager().registerEvents(new IridiumArmorVanishEvent(new ItemManager(this),this),this);
        this.getServer().getPluginManager().registerEvents(new PlayerSquishEvent(),this);
        this.getServer().getPluginManager().registerEvents(new CropGrowEvent(new BlockManager(this, new ItemManager(this)), new RNGManager(), this),this);
        this.getServer().getPluginManager().registerEvents(new BreakCropEvent(new BlockManager(this, new ItemManager(this)), new ItemManager(this) ,new RNGManager(), this), this);
        this.getServer().getPluginManager().registerEvents(new IridiumHoeUseEvent(new BlockManager(this, new ItemManager(this)), new ItemManager(this) ,new RNGManager(), this), this);
        this.getServer().getPluginManager().registerEvents(new SeedPlaceEvent(new ItemManager(this),this),this);
        this.getServer().getPluginManager().registerEvents(new CropTrampleEvent(),this);
        this.getServer().getPluginManager().registerEvents(new PlayerBonemealCropEvent(new BlockManager(this, new ItemManager(this)),new RNGManager(),this),this);
        this.getServer().getPluginManager().registerEvents(new MiscMobDrops(new RNGManager()), this);
        this.getServer().getPluginManager().registerEvents(new BreakerPushEvent(),this);
        this.getServer().getPluginManager().registerEvents(new BlockGenerateEvent(this),this);
        //this.getServer().getPluginManager().registerEvents(new PlacerDropEvent(this),this);

        this.getCommand("togglerat").setExecutor(new ToggleRatCommand());

        IridiumRecipes ir = new IridiumRecipes(new ItemManager(this),this);
        Bukkit.addRecipe(ir.IridiumIngotFurnaceRecipe());
        Bukkit.addRecipe(ir.iridumChunkShapelessRecipe());



        NetheriteRecipes nr = new NetheriteRecipes(new ItemManager(this), this);
        Bukkit.addRecipe(nr.NetheriteBowSmithingTransformRecipe());
        Bukkit.addRecipe(nr.NetheriteCrossbowSmithingTransformRecipe());
        Bukkit.addRecipe(nr.NetheriteFishingRodSmithingTransformRecipe());
        Bukkit.addRecipe(nr.NetheriteFlintAndSteelSmithingTransformRecipe());
        Bukkit.addRecipe(nr.NetheriteCarrotOnAStickSmithingTransformRecipe());
        Bukkit.addRecipe(nr.NetheriteWarpedFungusOnAStickSmithingTransformRecipe());
        Bukkit.addRecipe(nr.NetheriteShearsSmithingTransformRecipe());
        Bukkit.addRecipe(nr.NetheriteBrushSmithingTransformRecipe());
        Bukkit.addRecipe(nr.NetheriteElytraSmithingTransformRecipe());
        Bukkit.addRecipe(nr.NetheriteMaceSmithingTransformRecipe());

        FarmingRecipes fr = new FarmingRecipes(new ItemManager(this),this);
        Bukkit.addRecipe(fr.strawberryShapelessRecipe());
        Bukkit.addRecipe(fr.popcornFurnaceRecipe());
        Bukkit.addRecipe(fr.sodaShapelessRecipe());
        Bukkit.addRecipe(fr.vanillaShapelessRecipe());
        Bukkit.addRecipe(fr.chocolateIceCreamShapelessRecipe());
        Bukkit.addRecipe(fr.vanillaIceCreamShapelessRecipe());
        Bukkit.addRecipe(fr.mintIceCreamShapelessRecipe());
        Bukkit.addRecipe(fr.milkBottleShapelessRecipe());
        Bukkit.addRecipe(fr.saltShapelessRecipe());
        Bukkit.addRecipe(fr.butterShapelessRecipe());
        Bukkit.addRecipe(fr.cheeseShapelessRecipe());
        Bukkit.addRecipe(fr.pepperoniShapelessRecipe());
        Bukkit.addRecipe(fr.pizzaSliceShapelessRecipe());
        Bukkit.addRecipe(fr.pizzaShapelessRecipe());
        Bukkit.addRecipe(fr.sausageShapelessRecipe());
        Bukkit.addRecipe(fr.hotDogShapelessRecipe());
        Bukkit.addRecipe(fr.baconShapelessRecipe());
        Bukkit.addRecipe(fr.sandwichShapelessRecipe());
        Bukkit.addRecipe(fr.friedEggFurnaceRecipe());
        Bukkit.addRecipe(fr.doughnutShapedRecipe());
        Bukkit.addRecipe(fr.bagelShapedRecipe());
        Bukkit.addRecipe(fr.pizzaSliceShapelessRecipe2());


        MiscRecipes mr = new MiscRecipes(this);
        Bukkit.addRecipe(mr.ElytraShapedRecipe());
        Bukkit.addRecipe(mr.NetheriteSmithingTemplateShapedRecipe());
        Bukkit.addRecipe(mr.SaddleShapedRecipe());
        Bukkit.addRecipe(mr.DiamondHorseArmorShapedRecipe());
        Bukkit.addRecipe(mr.IronHorseArmorShapedRecipe());
        Bukkit.addRecipe(mr.GoldenHorseArmorShapedRecipe());
        Bukkit.addRecipe(mr.GildedBlackstoneShapedRecipe());
        Bukkit.addRecipe(mr.CobwebShapedRecipe());
        Bukkit.addRecipe(mr.CalciteFurnaceRecipe());
        Bukkit.addRecipe(mr.TuffFurnaceRecipe());
        Bukkit.addRecipe(mr.DarkPrismarineFurnaceRecipe());




        this.getLogger().info("Iridium has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
