package net.glasscrab.iridium.farming;

import io.papermc.paper.datacomponent.DataComponentType;
import io.papermc.paper.datacomponent.DataComponentTypes;
import net.glasscrab.iridium.BlockManager;
import net.glasscrab.iridium.ItemManager;
import net.glasscrab.iridium.RNGManager;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class IridiumHoeUseEvent implements Listener {

    private final BlockManager blockManager;
    private final Plugin plugin;
    private final RNGManager rngManager;
    private final ItemManager itemManager;
    public IridiumHoeUseEvent(BlockManager blockManager, ItemManager itemManager, RNGManager rngManager, Plugin plugin){
        this.blockManager = blockManager;
        this.rngManager = rngManager;
        this.plugin = plugin;
        this.itemManager = itemManager;
    }

    @EventHandler
    public void onUseIridiumHoe(PlayerInteractEvent e){
        if(e.getHand() != EquipmentSlot.HAND) return;
        //e.getPlayer().sendMessage("hand");
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        //e.getPlayer().sendMessage("right click block");
        if(e.getItem() == null) return;
        //e.getPlayer().sendMessage("has item");
        if(!e.getItem().hasData(DataComponentTypes.ITEM_MODEL)) return;
        //e.getPlayer().sendMessage("has item model");
        if(!e.getItem().getData(DataComponentTypes.ITEM_MODEL).value().equals("iridium_hoe")) return;
        //e.getPlayer().sendMessage("item model iridium hoe");
        if(!blockManager.isCustomCrop(e.getClickedBlock().getState().getType())) return;

        BlockState state = e.getClickedBlock().getState();
        BlockData data = state.getBlockData();
        Ageable age = (Ageable) data;

        
        switch(e.getClickedBlock().getState().getType()){
            case WHEAT:
                switch (age.getAge()){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        itemManager.harvestItem(e.getClickedBlock(),new ItemStack(Material.WHEAT_SEEDS,rngManager.random(1)),e.getPlayer());
                        itemManager.harvestItem(e.getClickedBlock(),new ItemStack(Material.WHEAT,1),e.getPlayer());
                        age.setAge(age.getAge()-4);
                        break;
                    case 5:
                        itemManager.harvestItem(e.getClickedBlock(), itemManager.makeBasicSeedItem(rngManager.random(1),64,"Cabbage Seeds",new NamespacedKey("farming","cabbage_seeds"),ItemRarity.COMMON),e.getPlayer());
                        itemManager.harvestItem(e.getClickedBlock(), itemManager.makeBasicFoodItem(rngManager.random(2), 64,"Cabbage",new NamespacedKey("farming","cabbage"),2,1f,1.61f,ItemRarity.COMMON),e.getPlayer());
                        age.setAge(age.getAge()-4);
                        break;
                    case 6:
                        itemManager.harvestItem(e.getClickedBlock(),itemManager.makeBasicSeedItem(rngManager.random(1), 64,"Garlic Seeds",new NamespacedKey("farming","garlic_seeds"),ItemRarity.COMMON),e.getPlayer());
                        itemManager.harvestItem(e.getClickedBlock(),itemManager.makeBasicFoodItem(rngManager.random(2), 64,"Garlic",new NamespacedKey("farming","garlic"),2,1f,1.61f,ItemRarity.COMMON),e.getPlayer());
                        age.setAge(age.getAge()-4);
                        break;
                    case 7:
                        itemManager.harvestItem(e.getClickedBlock(),itemManager.makeBasicSeedItem(rngManager.random(1), 64,"Pepper Seeds",new NamespacedKey("farming","pepper_seeds"),ItemRarity.COMMON),e.getPlayer());
                        itemManager.harvestItem(e.getClickedBlock(),itemManager.makeBasicFoodItem(rngManager.random(2), 64,"Pepper",new NamespacedKey("farming","pepper"),2,1f,1.61f,ItemRarity.COMMON),e.getPlayer());
                        age.setAge(age.getAge()-4);
                        break;
                }
                break;
            case POTATOES:
                switch (age.getAge()){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        itemManager.harvestItem(e.getClickedBlock(),new ItemStack(Material.POTATO,1),e.getPlayer());
                        itemManager.harvestItem(e.getClickedBlock(),new ItemStack(Material.POTATO,rngManager.random(2)),e.getPlayer());
                        age.setAge(age.getAge()-4);
                        break;
                    case 5:
                        itemManager.harvestItem(e.getClickedBlock(),itemManager.makeBasicSeedItem(rngManager.random(1), 64,"Ginger Seeds",new NamespacedKey("farming","ginger_seeds"),ItemRarity.COMMON),e.getPlayer());
                        itemManager.harvestItem(e.getClickedBlock(),itemManager.makeBasicFoodItem(rngManager.random(2), 64,"Ginger",new NamespacedKey("farming","ginger"),2,1f,1.61f,ItemRarity.COMMON),e.getPlayer());
                        age.setAge(age.getAge()-4);
                        break;
                    case 6:
                        itemManager.harvestItem(e.getClickedBlock(),itemManager.makeBasicSeedItem(rngManager.random(1), 64,"Onion Seeds",new NamespacedKey("farming","onion_seeds"),ItemRarity.COMMON),e.getPlayer());
                        itemManager.harvestItem(e.getClickedBlock(),itemManager.makeBasicFoodItem(rngManager.random(2), 64,"Onion",new NamespacedKey("farming","onion"),2,1f,1.61f,ItemRarity.COMMON),e.getPlayer());
                        age.setAge(age.getAge()-4);
                        break;
                    case 7:
                        itemManager.harvestItem(e.getClickedBlock(),itemManager.makeBasicSeedItem(rngManager.random(1),64,"Corn Seeds",new NamespacedKey("farming","corn_seeds"),ItemRarity.COMMON),e.getPlayer());
                        itemManager.harvestItem(e.getClickedBlock(),itemManager.makeBasicFoodItem(rngManager.random(2), 64,"Corn",new NamespacedKey("farming","corn"),2,1f,1.61f,ItemRarity.COMMON),e.getPlayer());
                        age.setAge(age.getAge()-4);
                        break;

                }
                break;

            case CARROTS:
                switch (age.getAge()){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        itemManager.harvestItem(e.getClickedBlock(),new ItemStack(Material.CARROT,1),e.getPlayer());
                        itemManager.harvestItem(e.getClickedBlock(),new ItemStack(Material.CARROT,rngManager.random(2)),e.getPlayer());
                        age.setAge(age.getAge()-4);
                        break;
                    case 5:
                        itemManager.harvestItem(e.getClickedBlock(),itemManager.makeBasicSeedItem(rngManager.random(2), 64,"Rice Seeds",new NamespacedKey("farming","rice_seeds"),ItemRarity.COMMON),e.getPlayer());
                        itemManager.harvestItem(e.getClickedBlock(),itemManager.makeBasicFoodItem(rngManager.random(2), 64,"Rice",new NamespacedKey("farming","rice"),2,1f,1.61f,ItemRarity.COMMON),e.getPlayer());
                        age.setAge(age.getAge()-4);
                        break;
                    case 6:
                        itemManager.harvestItem(e.getClickedBlock(),itemManager.makeBasicSeedItem(rngManager.random(2), 64,"Tomato Seeds",new NamespacedKey("farming","tomato_seeds"),ItemRarity.COMMON),e.getPlayer());
                        itemManager.harvestItem(e.getClickedBlock(),itemManager.makeBasicFoodItem(rngManager.random(2), 64,"Tomato",new NamespacedKey("farming","tomato"),2,1f,1.61f,ItemRarity.COMMON),e.getPlayer());
                        age.setAge(age.getAge()-4);
                        break;
                    case 7:
                        itemManager.harvestItem(e.getClickedBlock(),itemManager.makeBasicSeedItem(rngManager.random(2),64,"Peanut Seeds",new NamespacedKey("farming","peanut_seeds"),ItemRarity.COMMON),e.getPlayer());
                        itemManager.harvestItem(e.getClickedBlock(),itemManager.makeBasicFoodItem(rngManager.random(2), 64,"Peanut",new NamespacedKey("farming","peanut"),2,1f,1.61f,ItemRarity.COMMON),e.getPlayer());
                        age.setAge(age.getAge()-4);
                        break;

                }
                break;

            case BEETROOTS:
                switch (age.getAge()){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        itemManager.harvestItem(e.getClickedBlock(),new ItemStack(Material.BEETROOT_SEEDS,rngManager.random(1)),e.getPlayer());
                        itemManager.harvestItem(e.getClickedBlock(),new ItemStack(Material.BEETROOT,1),e.getPlayer());
                        age.setAge(age.getAge()-3);
                        break;
                }
                break;
            case NETHER_WART:
                switch (age.getAge()){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        itemManager.harvestItem(e.getClickedBlock(),new ItemStack(Material.NETHER_WART,rngManager.random(1)),e.getPlayer());
                        itemManager.harvestItem(e.getClickedBlock(),new ItemStack(Material.NETHER_WART,1),e.getPlayer());
                        age.setAge(age.getAge()-3);
                        break;
                }
                break;
        }

        new BukkitRunnable() {

            @Override
            public void run() {
                e.getClickedBlock().setBlockData(age);
            }
        }.runTaskLater(this.plugin, 1);
        
    }


}
