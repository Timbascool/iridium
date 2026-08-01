package net.glasscrab.iridium;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ItemEnchantments;
import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.Piston;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class BreakerPushEvent implements Listener {


    public static ItemStack silkItem(){
        ItemStack item = new ItemStack(Material.NETHERITE_PICKAXE,1);
        Map <Enchantment, Integer> enchants = new HashMap<>();
        enchants.put(Enchantment.SILK_TOUCH,1);
        item.setData(DataComponentTypes.ENCHANTMENTS,ItemEnchantments.itemEnchantments(enchants));

        return item;
    }

    private static final ItemStack SILK_ITEM = silkItem();

    private static final Map<Material, Material> blockTransformations = Map.of(
            Material.COBBLESTONE, Material.GRAVEL,
            Material.GRAVEL, Material.SAND
    );

    @EventHandler
    public void onBreakerPush(BlockPistonExtendEvent e){
        var blocks = e.getBlocks();
        if (blocks.isEmpty()) {
            return;
        }

        Block piston = e.getBlock();
        Block first = blocks.getFirst();

        if (!Tag.CHAINS.isTagged(first.getType()) && first.getType() != Material.END_ROD) {
            return;
        }
        Block brokenBlock;

        boolean cancelEvent = false;

        // Break block right in front of chain

        if (blocks.size() > 1 && !blocks.get(1).isLiquid() &&
                blocks.get(1).getPistonMoveReaction() != PistonMoveReaction.BREAK) {
            brokenBlock = blocks.get(1);
            cancelEvent = true;
        }
        // Break block with gap
        else {
            Directional directional = (Directional) piston.getBlockData();
            brokenBlock = first.getRelative(directional.getFacing(), 2);
        }


        if (brokenBlock.getType().getHardness() < 0) return;
        if (brokenBlock.getType().getHardness() >= 50) return;

        if (!isPushable(brokenBlock, e.getDirection(),true, e.getDirection())) return;

        // Iron chains transform (when transforming doesn't break), End rods have silk touch and copper chains break normally
        if (first.getType() == Material.IRON_CHAIN &&
                blockTransformations.containsKey(brokenBlock.getType())) {
            brokenBlock.setType(blockTransformations.get(brokenBlock.getType()));
        }
        else if (first.getType() == Material.END_ROD) {
            brokenBlock.breakNaturally(SILK_ITEM,true);
        }
        else {
            brokenBlock.breakNaturally(true);
        }

        if (cancelEvent) e.setCancelled(true);
    }


    // Copied from PistonBaseBlock#isPushable() in the Minecraft source code
    private static boolean isPushable(Block block, BlockFace direction, boolean allowDestroyable, BlockFace connectionDirection)
    {
        World world = block.getWorld();
        Location location = block.getLocation();
        int y = block.getY();

        if (y < world.getMinHeight() || y > world.getMaxHeight()) {
            return false;
        }
        if (!world.getWorldBorder().isInside(location)) {
            return false;
        }

        Material material = block.getType();

        if (material.isAir()) {
            return true;
        }
        if (material == Material.OBSIDIAN || material == Material.CRYING_OBSIDIAN
            || material == Material.RESPAWN_ANCHOR || material == Material.REINFORCED_DEEPSLATE) {
            return false;
        }

        if (direction == BlockFace.DOWN && location.y() == world.getMinHeight()) {
            return false;
        }
        if (direction == BlockFace.UP && location.y() == world.getMaxHeight()) {
            return false;
        }

        if (!(material == Material.PISTON || material == Material.STICKY_PISTON)) {
            if (block.getType().getHardness() == -1) {
                return false;
            }

            switch (block.getPistonMoveReaction()) {
                case BLOCK:
                    return false;
                case BREAK:
                    return allowDestroyable;
                case PUSH_ONLY:
                    return direction == connectionDirection;
            }
        } else if (((Piston) block.getBlockData()).isExtended()) {
            return false;
        }


        return !(block.getState() instanceof TileState);
    }

}
