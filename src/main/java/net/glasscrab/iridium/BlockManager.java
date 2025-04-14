package net.glasscrab.iridium;

import io.papermc.paper.datacomponent.DataComponentTypes;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.bukkit.Material.POTATOES;

public class BlockManager {
    private static BlockManager manager;
    private static Plugin plugin;
    private final ItemManager itemManager;
    public BlockManager(Plugin plugin, ItemManager itemManager){
        manager = this;
        this.plugin = plugin;
        this.itemManager = itemManager;

    }

    public boolean isCustomCrop(Material type){
        Set<Material> crops = new HashSet<>();
        crops.add(Material.WHEAT);
        crops.add(Material.CARROTS);
        crops.add(Material.POTATOES);
        crops.add(Material.BEETROOTS);
        crops.add(Material.NETHER_WART);

        return crops.contains(type);
    }

    public void dropCropItems(boolean isSeed, ItemStack seedItem, ItemStack cropItem, Block block){
        if(!isSeed){
            itemManager.dropItemOnBlock(block, cropItem);
        }
        itemManager.dropItemOnBlock(block, seedItem);
    }
    
    


    public static BlockManager getBlockManager(){
        return manager;
    }

}
