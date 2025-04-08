package net.glasscrab.iridium;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

public class PlacerDropEvent implements Listener {

    private final Plugin plugin;

    public PlacerDropEvent(Plugin plugin){
        this.plugin = plugin;
    }

    public ItemStack placerItem(){
        ItemStack item = new ItemStack(Material.BUCKET,1);
        return item;
    }

    @EventHandler
    public void onPlacerDrop(BlockDispenseEvent e){
        Collection<ItemFrame> frames = e.getBlock().getLocation().getNearbyEntitiesByType(ItemFrame.class,2);
        Bukkit.broadcast(Component.text(frames.size()));
        if(!frames.isEmpty()){
            for(ItemFrame f : frames){
                if(f.getItem().isSimilar(placerItem())){
                    if(e.getItem().getType().isBlock()){
                        //e.getItem().setAmount(e.getItem().getAmount()-1);
                        Directional directional = (Directional) e.getBlock().getBlockData();
                        Block placedBlock = e.getBlock().getRelative(directional.getFacing());
                        if(!placedBlock.isSolid()) placedBlock.setType(e.getItem().getType());
                        //e.setItem(new ItemStack(Material.AIR,0));
                        e.getItem().setAmount(0);
                        return;
                    }
                }

            }
        }
    }
}
