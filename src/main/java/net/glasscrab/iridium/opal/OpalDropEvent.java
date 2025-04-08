package net.glasscrab.iridium.opal;

import net.glasscrab.iridium.ItemManager;
import net.glasscrab.iridium.RNGManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.inventory.ItemRarity;

public class OpalDropEvent implements Listener {

    private final RNGManager rngManager;
    private final ItemManager itemManager;

    public OpalDropEvent(RNGManager rngManager, ItemManager itemManager){
        this.rngManager = rngManager;
        this.itemManager = itemManager;
    }

    @EventHandler
    public void opalDropEvent(BlockDropItemEvent e){
        if(e.getBlockState().getType() != Material.DEEPSLATE_DIAMOND_ORE) return;
        if(e.getItems().getFirst().getItemStack().getType() != Material.DIAMOND) return;
        if(e.getPlayer().getInventory().getItemInMainHand().getType() != Material.NETHERITE_PICKAXE) return;

        if(rngManager.jackpot(150)){
            itemManager.dropItemOnItem(e.getItems().getFirst(),itemManager.makeBasicItem("Charged Opal", new NamespacedKey("opal", "charged_opal"), ItemRarity.COMMON));
            e.getPlayer().getWorld().spawnParticle(Particle.TRIAL_SPAWNER_DETECTION_OMINOUS,e.getBlock().getLocation().add(0.5,0.5,0.5),10,0.5,0.5,0.5,0.2);
            //e.getPlayer().sendMessage(Component.text("You unearthed a Charged Opal!").color(TextColor.color(0, 253, 255)));
            Bukkit.broadcast(Component.text(e.getPlayer().getName()+" unearthed a Charged Opal!").color(TextColor.color(0, 253, 255)));
        }
    }
}
