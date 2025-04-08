package net.glasscrab.iridium.farming;

import net.glasscrab.iridium.BlockManager;
import net.glasscrab.iridium.ItemManager;
import net.glasscrab.iridium.RNGManager;
import net.kyori.adventure.text.Component;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class BreakCropEvent implements Listener {

    private final BlockManager blockManager;
    private final Plugin plugin;
    private final RNGManager rngManager;
    private final ItemManager itemManager;
    public BreakCropEvent(BlockManager blockManager, ItemManager itemManager, RNGManager rngManager, Plugin plugin){
        this.blockManager = blockManager;
        this.rngManager = rngManager;
        this.plugin = plugin;
        this.itemManager = itemManager;
    }
    
    @EventHandler
    public void OnBreakCrop(BlockDropItemEvent e){
        if(!blockManager.isCustomCrop(e.getBlockState().getType())) return;
        if(e.getPlayer().getGameMode() == GameMode.CREATIVE) return;
        BlockState state = e.getBlockState();
        BlockData data = state.getBlockData();
        Ageable age = (Ageable) data;

        e.getItems().clear();



        switch(e.getBlockState().getType()){
            case WHEAT:
                switch (age.getAge()){
                    case 0:
                        blockManager.dropCropItems(true, new ItemStack(Material.WHEAT_SEEDS,1), new ItemStack(Material.WHEAT,1), e.getBlock());
                        break;
                    case 1:
                        blockManager.dropCropItems(true, itemManager.makeBasicSeedItem(1, 64,"Cabbage Seeds",new NamespacedKey("farming","cabbage_seeds"),ItemRarity.COMMON), itemManager.makeBasicFoodItem(rngManager.random(2), 64,"Cabbage",new NamespacedKey("farming","cabbage"),2,1f, 1.61f,ItemRarity.COMMON), e.getBlock());
                        break;
                    case 2:
                        blockManager.dropCropItems(true, itemManager.makeBasicSeedItem(1, 64,"Garlic Seeds",new NamespacedKey("farming","garlic_seeds"),ItemRarity.COMMON), itemManager.makeBasicFoodItem(1, 64,"Garlic",new NamespacedKey("farming","garlic"),2,1f,1.61f,ItemRarity.COMMON), e.getBlock());
                        break;
                    case 3:
                        blockManager.dropCropItems(true, itemManager.makeBasicSeedItem(1, 64,"Pepper Seeds",new NamespacedKey("farming","pepper_seeds"),ItemRarity.COMMON), itemManager.makeBasicFoodItem(rngManager.random(2), 64,"Pepper",new NamespacedKey("farming","pepper"),2,1f,1.61f,ItemRarity.COMMON), e.getBlock());
                        break;
                    case 4:
                        blockManager.dropCropItems(false, new ItemStack(Material.WHEAT_SEEDS,rngManager.random(2)), new ItemStack(Material.WHEAT,1), e.getBlock());
                        break;
                    case 5:
                        blockManager.dropCropItems(false, itemManager.makeBasicSeedItem(rngManager.random(2),64,"Cabbage Seeds",new NamespacedKey("farming","cabbage_seeds"),ItemRarity.COMMON), itemManager.makeBasicFoodItem(rngManager.random(2), 64,"Cabbage",new NamespacedKey("farming","cabbage"),2,1f,1.61f,ItemRarity.COMMON), e.getBlock());
                        break;
                    case 6:
                        blockManager.dropCropItems(false, itemManager.makeBasicSeedItem(rngManager.random(2), 64,"Garlic Seeds",new NamespacedKey("farming","garlic_seeds"),ItemRarity.COMMON), itemManager.makeBasicFoodItem(rngManager.random(2), 64,"Garlic",new NamespacedKey("farming","garlic"),2,1f,1.61f,ItemRarity.COMMON), e.getBlock());
                        break;
                    case 7:
                        blockManager.dropCropItems(false, itemManager.makeBasicSeedItem(rngManager.random(2), 64,"Pepper Seeds",new NamespacedKey("farming","pepper_seeds"),ItemRarity.COMMON), itemManager.makeBasicFoodItem(rngManager.random(2), 64,"Pepper",new NamespacedKey("farming","pepper"),2,1f,1.61f,ItemRarity.COMMON), e.getBlock());
                        break;
                }
                break;
            case POTATOES:
                switch (age.getAge()){
                    case 0:
                        blockManager.dropCropItems(true, new ItemStack(Material.POTATO,1), new ItemStack(Material.POTATO,1), e.getBlock());
                        break;
                    case 1:
                        blockManager.dropCropItems(true, itemManager.makeBasicSeedItem(1, 64,"Ginger Seeds",new NamespacedKey("farming","ginger_seeds"),ItemRarity.COMMON), itemManager.makeBasicFoodItem(1, 64,"Ginger",new NamespacedKey("farming","ginger"),2,1f,1.61f,ItemRarity.COMMON), e.getBlock());
                        break;
                    case 2:
                        blockManager.dropCropItems(true, itemManager.makeBasicSeedItem(1, 64,"Onion Seeds",new NamespacedKey("farming","onion_seeds"),ItemRarity.COMMON), itemManager.makeBasicFoodItem(rngManager.random(2), 64,"Onion",new NamespacedKey("farming","onion"),2,1f,1.61f,ItemRarity.COMMON), e.getBlock());
                        break;
                    case 3:
                        blockManager.dropCropItems(true, itemManager.makeBasicSeedItem(1, 64,"Corn Seeds",new NamespacedKey("farming","corn_seeds"),ItemRarity.COMMON), itemManager.makeBasicFoodItem(rngManager.random(2), 64,"Corn",new NamespacedKey("farming","corn"),2,1f,1.61f,ItemRarity.COMMON), e.getBlock());
                        break;
                    case 4:
                        blockManager.dropCropItems(false, new ItemStack(Material.POTATO,1), new ItemStack(Material.POTATO,rngManager.random(2)), e.getBlock());
                        break;
                    case 5:
                        blockManager.dropCropItems(false, itemManager.makeBasicSeedItem(rngManager.random(2), 64,"Ginger Seeds",new NamespacedKey("farming","ginger_seeds"),ItemRarity.COMMON), itemManager.makeBasicFoodItem(rngManager.random(2), 64,"Ginger",new NamespacedKey("farming","ginger"),2,1f,1.61f,ItemRarity.COMMON), e.getBlock());
                        break;
                    case 6:
                        blockManager.dropCropItems(false, itemManager.makeBasicSeedItem(rngManager.random(2), 64,"Onion Seeds",new NamespacedKey("farming","onion_seeds"),ItemRarity.COMMON), itemManager.makeBasicFoodItem(rngManager.random(2), 64,"Onion",new NamespacedKey("farming","onion"),2,1f,1.61f,ItemRarity.COMMON), e.getBlock());
                        break;
                    case 7:
                        blockManager.dropCropItems(false, itemManager.makeBasicSeedItem(rngManager.random(2),64,"Corn Seeds",new NamespacedKey("farming","corn_seeds"),ItemRarity.COMMON), itemManager.makeBasicFoodItem(rngManager.random(2), 64,"Corn",new NamespacedKey("farming","corn"),2,1f,1.61f,ItemRarity.COMMON), e.getBlock());
                        break;

                }
                break;

            case CARROTS:
                switch (age.getAge()){
                    case 0:
                        blockManager.dropCropItems(true, new ItemStack(Material.CARROT,1), new ItemStack(Material.CARROT,1), e.getBlock());
                        break;
                    case 1:
                        blockManager.dropCropItems(true, itemManager.makeBasicSeedItem(1, 64,"Rice Seeds",new NamespacedKey("farming","rice_seeds"),ItemRarity.COMMON), itemManager.makeBasicFoodItem(1, 64,"Rice",new NamespacedKey("farming","rice"),2,1f,1.61f,ItemRarity.COMMON), e.getBlock());
                        break;
                    case 2:
                        blockManager.dropCropItems(true, itemManager.makeBasicSeedItem(1, 64,"Tomato Seeds",new NamespacedKey("farming","tomato_seeds"),ItemRarity.COMMON), itemManager.makeBasicFoodItem(rngManager.random(2), 64,"Tomato",new NamespacedKey("farming","tomato"),2,1f,1.61f,ItemRarity.COMMON), e.getBlock());
                        break;
                    case 3:
                        blockManager.dropCropItems(true, itemManager.makeBasicSeedItem(1, 64,"Peanut Seeds",new NamespacedKey("farming","peanut_seeds"),ItemRarity.COMMON), itemManager.makeBasicFoodItem(rngManager.random(2), 64,"Peanut",new NamespacedKey("farming","peanut"),2,1f,1.61f,ItemRarity.COMMON), e.getBlock());
                        break;
                    case 4:
                        blockManager.dropCropItems(false, new ItemStack(Material.CARROT,1), new ItemStack(Material.CARROT,rngManager.random(2)), e.getBlock());
                        break;
                    case 5:
                        blockManager.dropCropItems(false, itemManager.makeBasicSeedItem(rngManager.random(2), 64,"Rice Seeds",new NamespacedKey("farming","rice_seeds"),ItemRarity.COMMON), itemManager.makeBasicFoodItem(rngManager.random(2), 64,"Rice",new NamespacedKey("farming","rice"),2,1f,1.61f,ItemRarity.COMMON), e.getBlock());
                        break;
                    case 6:
                        blockManager.dropCropItems(false, itemManager.makeBasicSeedItem(rngManager.random(2), 64,"Tomato Seeds",new NamespacedKey("farming","tomato_seeds"),ItemRarity.COMMON), itemManager.makeBasicFoodItem(rngManager.random(2), 64,"Tomato",new NamespacedKey("farming","tomato"),2,1f,1.61f,ItemRarity.COMMON), e.getBlock());
                        break;
                    case 7:
                        blockManager.dropCropItems(false, itemManager.makeBasicSeedItem(rngManager.random(2),64,"Peanut Seeds",new NamespacedKey("farming","peanut_seeds"),ItemRarity.COMMON), itemManager.makeBasicFoodItem(rngManager.random(2), 64,"Peanut",new NamespacedKey("farming","peanut"),2,1f,1.61f,ItemRarity.COMMON), e.getBlock());
                        break;

                }
                break;

            case BEETROOTS:
                switch (age.getAge()){
                    case 0:
                        blockManager.dropCropItems(true, new ItemStack(Material.BEETROOT_SEEDS,1), new ItemStack(Material.BEETROOT,1), e.getBlock());
                        break;
                    case 1:

                    case 2:

                    case 3:
                        blockManager.dropCropItems(false, new ItemStack(Material.BEETROOT_SEEDS,rngManager.random(2)), new ItemStack(Material.BEETROOT,rngManager.random(2)), e.getBlock());
                        break;
                }
                break;
        }
    }

}
