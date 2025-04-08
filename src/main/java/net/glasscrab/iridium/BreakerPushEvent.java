package net.glasscrab.iridium;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ItemEnchantments;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BreakerPushEvent implements Listener {


    public ItemStack silkItem(){
        ItemStack item = new ItemStack(Material.NETHERITE_PICKAXE,1);
        Map <Enchantment, Integer> enchants = new HashMap<>();
        enchants.put(Enchantment.SILK_TOUCH,1);
        item.setData(DataComponentTypes.ENCHANTMENTS,ItemEnchantments.itemEnchantments(enchants,false));

        return item;
    }

    public ItemStack silkBook(){
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK,1);
        Map <Enchantment, Integer> enchants = new HashMap<>();
        enchants.put(Enchantment.SILK_TOUCH,1);
        item.setData(DataComponentTypes.STORED_ENCHANTMENTS,ItemEnchantments.itemEnchantments(enchants,true));

        return item;
    }

    @EventHandler
    public void onBreakerPush(BlockPistonExtendEvent e){
        if(e.getBlocks().isEmpty()) return;
        if(!e.getBlocks().getFirst().getType().equals(Material.CHAIN)) return;
        if(e.getBlocks().size() < 2) return;
        Block brokenBlock = e.getBlocks().get(1);
        if(brokenBlock.getType().getHardness() > 50) return;
        switch (brokenBlock.getType()){
            case COBBLESTONE:
                brokenBlock.setType(Material.GRAVEL);
                break;
            case GRAVEL:
                brokenBlock.setType(Material.SAND);
                break;
        }
        Collection<ItemFrame> frames = e.getBlock().getLocation().getNearbyEntitiesByType(ItemFrame.class,2);
        //Bukkit.broadcast(Component.text(frames.size()));
        if(!frames.isEmpty()){
            for(ItemFrame f : frames){
                if(f.getItem().isSimilar(silkBook())) brokenBlock.breakNaturally(silkItem(),true);
                e.setCancelled(true);
                return;
            }
        }
        brokenBlock.breakNaturally(true);
        e.setCancelled(true);
    }
}
