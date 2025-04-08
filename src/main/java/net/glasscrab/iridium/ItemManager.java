package net.glasscrab.iridium;

import com.oheers.fish.api.plugin.EMFPlugin;
import io.papermc.paper.datacomponent.DataComponentType;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.*;
import io.papermc.paper.datacomponent.item.consumable.ConsumeEffect;
import io.papermc.paper.datacomponent.item.consumable.ItemUseAnimation;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.keys.BlockTypeKeys;
import io.papermc.paper.registry.set.RegistryKeySet;
import io.papermc.paper.registry.set.RegistrySet;
import io.papermc.paper.registry.tag.TagKey;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.util.TriState;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.block.BlockType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.tag.DamageTypeTags;
import org.checkerframework.checker.units.qual.N;

import java.util.*;


public class ItemManager {

    private static ItemManager manager;
    private static Plugin plugin;
    public ItemManager(Plugin plugin){
        manager = this;
        this.plugin = plugin;
    }

    public ItemStack makeDummyItem(int count, int stackSize){
        ItemStack item = new ItemStack(Material.MUSIC_DISC_CREATOR,count);
        item.unsetData(DataComponentTypes.JUKEBOX_PLAYABLE);
        item.setData(DataComponentTypes.MAX_STACK_SIZE,stackSize);
        return item;
    }


    //A basic item is just something with a custom name, model, and material. Can be used for basic crafting.
    public ItemStack makeBasicItem(String itemName, NamespacedKey model, ItemRarity rarity){
        ItemStack item = makeDummyItem(1, 64);
        item.setData(DataComponentTypes.ITEM_MODEL, model);
        item.setData(DataComponentTypes.ITEM_NAME, Component.text(itemName));
        item.setData(DataComponentTypes.RARITY, rarity);
        item.setData(DataComponentTypes.DAMAGE_RESISTANT, DamageResistant.damageResistant(TagKey.create(RegistryKey.DAMAGE_TYPE, DamageTypeTags.IS_FIRE.key())));

        return item;
    }

    public ItemStack makeBasicStackedItem(int count, int stackSize, String itemName, NamespacedKey model, ItemRarity rarity){
        ItemStack item = makeDummyItem(count, stackSize);
        item.setData(DataComponentTypes.ITEM_MODEL, model);
        item.setData(DataComponentTypes.ITEM_NAME, Component.text(itemName));
        item.setData(DataComponentTypes.RARITY, rarity);
        item.setData(DataComponentTypes.DAMAGE_RESISTANT, DamageResistant.damageResistant(TagKey.create(RegistryKey.DAMAGE_TYPE, DamageTypeTags.IS_FIRE.key())));

        return item;
    }

    public ItemStack makeBasicFoodItem(int count, int stackSize, String itemName, NamespacedKey model, int nutrition, float saturation, float eatTime,ItemRarity rarity){
        ItemStack item = makeDummyItem(count, stackSize);
        FoodProperties.Builder food = FoodProperties.food()
                .canAlwaysEat(false)
                .nutrition(nutrition)
                .saturation(saturation);
        Consumable.Builder consumable = Consumable.consumable()
                .consumeSeconds(1.61f)
                .hasConsumeParticles(true);
        item.setData(DataComponentTypes.FOOD,food);
        item.setData(DataComponentTypes.CONSUMABLE, consumable);
        item.setData(DataComponentTypes.ITEM_MODEL, model);
        item.setData(DataComponentTypes.ITEM_NAME, Component.text(itemName));
        item.setData(DataComponentTypes.RARITY, rarity);
        return item;
    }

    public ItemStack makeBasicDrinkableItem(int count, int stackSize, String itemName, NamespacedKey model, float drinkTime, NamespacedKey sound, ItemUseAnimation useAnimation, List<PotionEffect> effects,float prob,ItemRarity rarity){
        ItemStack item = makeDummyItem(count, stackSize);
        Consumable.Builder consumable = Consumable.consumable()
                .consumeSeconds(1.61f)
                .sound(sound)
                .animation(useAnimation)
                .addEffect(ConsumeEffect.applyStatusEffects(effects,prob))
                .hasConsumeParticles(false);
        item.setData(DataComponentTypes.CONSUMABLE, consumable);
        item.setData(DataComponentTypes.ITEM_MODEL, model);
        item.setData(DataComponentTypes.ITEM_NAME, Component.text(itemName));
        item.setData(DataComponentTypes.RARITY, rarity);
        return item;
    }

    public ItemStack makeBasicSeedItem(int count, int stackSize, String itemName, NamespacedKey model, ItemRarity rarity){
        ItemStack item = makeDummyItem(count, stackSize);
        item.setData(DataComponentTypes.ITEM_MODEL, model);
        item.setData(DataComponentTypes.ITEM_NAME, Component.text(itemName));
        item.setData(DataComponentTypes.RARITY, rarity);

        return item;
    }

    public boolean isSeedItem(ItemStack item){
        Set<String> names = new HashSet<>();
        names.add("cabbage_seeds");
        names.add("garlic_seeds");
        names.add("pepper_seeds");
        names.add("corn_seeds");
        names.add("onion_seeds");
        names.add("rice_seeds");
        names.add("ginger_seeds");
        names.add("tomato_seeds");
        names.add("peanut_seeds");


        return names.contains(item.getData(DataComponentTypes.ITEM_MODEL).value());
    }

    public ItemStack makeBasicNotFireItem(String itemName, NamespacedKey model, ItemRarity rarity){
        ItemStack item = makeDummyItem(1, 64);
        item.setData(DataComponentTypes.ITEM_MODEL, model);
        item.setData(DataComponentTypes.ITEM_NAME, Component.text(itemName));
        item.setData(DataComponentTypes.RARITY, rarity);

        return item;
    }
    
    public ItemStack makeBasicTool(String itemName, Material type, NamespacedKey model, int maxDamage,ItemRarity rarity){
        ItemStack item = new ItemStack(type);
        item.setData(DataComponentTypes.ITEM_MODEL, model);
        item.setData(DataComponentTypes.RARITY, rarity);
        item.setData(DataComponentTypes.MAX_DAMAGE, maxDamage);
        item.setData(DataComponentTypes.ITEM_NAME, Component.text(itemName));
        item.setData(DataComponentTypes.DAMAGE_RESISTANT, DamageResistant.damageResistant(TagKey.create(RegistryKey.DAMAGE_TYPE, DamageTypeTags.IS_FIRE.key())));

        return item;
    }

    public ItemStack upgradeBasicTool(ItemStack item, String itemName, NamespacedKey model, int maxDamage, ItemRarity rarity){
        item.setData(DataComponentTypes.ITEM_MODEL, model);
        item.setData(DataComponentTypes.RARITY, rarity);
        item.setData(DataComponentTypes.MAX_DAMAGE, maxDamage);
        item.setData(DataComponentTypes.ITEM_NAME, Component.text(itemName));
        item.setData(DataComponentTypes.DAMAGE_RESISTANT, DamageResistant.damageResistant(TagKey.create(RegistryKey.DAMAGE_TYPE, DamageTypeTags.IS_FIRE.key())));

        return item;
    }

    public ItemStack upgradeMiningTool(ItemStack item, String itemName, NamespacedKey model, int maxDamage, int miningSpeed, ItemRarity rarity){
        ItemMeta meta = item.getItemMeta();
        NamespacedKey key = new NamespacedKey(plugin, "test");
        AttributeModifier speed = new AttributeModifier(key,miningSpeed, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND);
        meta.addAttributeModifier(Attribute.MINING_EFFICIENCY,speed);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);

        item.setData(DataComponentTypes.ITEM_MODEL, model);
        item.setData(DataComponentTypes.RARITY, rarity);
        item.setData(DataComponentTypes.MAX_DAMAGE, maxDamage);
        item.setData(DataComponentTypes.ITEM_NAME, Component.text(itemName));
        item.setData(DataComponentTypes.DAMAGE_RESISTANT, DamageResistant.damageResistant(TagKey.create(RegistryKey.DAMAGE_TYPE, DamageTypeTags.IS_FIRE.key())));

        return item;
    }

    public ItemStack upgradeFishingRod(ItemStack item, String itemName, NamespacedKey model, int maxDamage, ItemRarity rarity){



        item.setData(DataComponentTypes.ITEM_MODEL, model);
        item.setData(DataComponentTypes.RARITY, rarity);
        item.setData(DataComponentTypes.MAX_DAMAGE, maxDamage);
        item.setData(DataComponentTypes.ITEM_NAME, Component.text(itemName));
        item.setData(DataComponentTypes.DAMAGE_RESISTANT, DamageResistant.damageResistant(TagKey.create(RegistryKey.DAMAGE_TYPE, DamageTypeTags.IS_FIRE.key())));

        return item;
    }

    public ItemStack makeUpgradedBrush(String itemName, Material type, NamespacedKey model, int maxDamage, float miningSpeed, ItemRarity rarity){
        ItemStack item = new ItemStack(type);

        TypedKey<BlockType>[] mineableBlocks = new TypedKey[]{
                TypedKey.create(RegistryKey.BLOCK, BlockTypeKeys.END_STONE.key()),
                TypedKey.create(RegistryKey.BLOCK, BlockTypeKeys.PURPUR_BLOCK.key()),
                TypedKey.create(RegistryKey.BLOCK, BlockTypeKeys.PURPUR_PILLAR.key()),
                TypedKey.create(RegistryKey.BLOCK, BlockTypeKeys.PURPUR_SLAB.key()),
                TypedKey.create(RegistryKey.BLOCK, BlockTypeKeys.PURPUR_STAIRS.key())
        };

        RegistryKeySet<BlockType> iridiumMineable = RegistrySet.keySet(RegistryKey.BLOCK, mineableBlocks);
        Tool.Rule rule = Tool.rule(iridiumMineable, miningSpeed, TriState.TRUE);
        Tool.Builder tool = Tool.tool().defaultMiningSpeed(1.0f).addRule(rule);

        item.setData(DataComponentTypes.TOOL, tool);

        item.setData(DataComponentTypes.ITEM_MODEL, model);
        item.setData(DataComponentTypes.RARITY, rarity);
        item.setData(DataComponentTypes.MAX_DAMAGE, maxDamage);
        item.setData(DataComponentTypes.ITEM_NAME, Component.text(itemName));
        item.setData(DataComponentTypes.DAMAGE_RESISTANT, DamageResistant.damageResistant(TagKey.create(RegistryKey.DAMAGE_TYPE, DamageTypeTags.IS_FIRE.key())));

        return item;
    }

    public ItemStack upgradeBrush(ItemStack item, String itemName, NamespacedKey model, int maxDamage, float miningSpeed, ItemRarity rarity){
        TypedKey<BlockType>[] mineableBlocks = new TypedKey[]{
                TypedKey.create(RegistryKey.BLOCK, BlockTypeKeys.END_STONE.key()),
                TypedKey.create(RegistryKey.BLOCK, BlockTypeKeys.PURPUR_BLOCK.key()),
                TypedKey.create(RegistryKey.BLOCK, BlockTypeKeys.PURPUR_PILLAR.key()),
                TypedKey.create(RegistryKey.BLOCK, BlockTypeKeys.PURPUR_SLAB.key()),
                TypedKey.create(RegistryKey.BLOCK, BlockTypeKeys.PURPUR_STAIRS.key())
        };

        RegistryKeySet<BlockType> iridiumMineable = RegistrySet.keySet(RegistryKey.BLOCK, mineableBlocks);
        Tool.Rule rule = Tool.rule(iridiumMineable, miningSpeed, TriState.TRUE);
        Tool.Builder tool = Tool.tool().defaultMiningSpeed(1.0f).addRule(rule);

        item.setData(DataComponentTypes.TOOL, tool);

        item.setData(DataComponentTypes.ITEM_MODEL, model);
        item.setData(DataComponentTypes.RARITY, rarity);
        item.setData(DataComponentTypes.MAX_DAMAGE, maxDamage);
        item.setData(DataComponentTypes.ITEM_NAME, Component.text(itemName));
        item.setData(DataComponentTypes.DAMAGE_RESISTANT, DamageResistant.damageResistant(TagKey.create(RegistryKey.DAMAGE_TYPE, DamageTypeTags.IS_FIRE.key())));

        return item;
    }

    public ItemStack upgradeCombatTool(ItemStack item, String itemName, NamespacedKey model, int maxDamage, int attackDamage, double attackSpeed,ItemRarity rarity){
        ItemMeta meta = item.getItemMeta();
        NamespacedKey key = new NamespacedKey(plugin, "test");
        AttributeModifier damage = new AttributeModifier(key,attackDamage, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND);
        AttributeModifier atkSpeed = new AttributeModifier(key,attackSpeed, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND);
        meta.removeAttributeModifier(Attribute.ATTACK_DAMAGE,damage);
        meta.removeAttributeModifier(Attribute.ATTACK_SPEED,atkSpeed);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE,damage);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED,atkSpeed);
        item.setItemMeta(meta);

        item.setData(DataComponentTypes.ITEM_MODEL, model);
        item.setData(DataComponentTypes.RARITY, rarity);
        item.setData(DataComponentTypes.MAX_DAMAGE, maxDamage);
        item.setData(DataComponentTypes.ITEM_NAME, Component.text(itemName));
        item.setData(DataComponentTypes.DAMAGE_RESISTANT, DamageResistant.damageResistant(TagKey.create(RegistryKey.DAMAGE_TYPE, DamageTypeTags.IS_FIRE.key())));

        return item;
    }

    public ItemStack makeCombatTool(String itemName, Material type, NamespacedKey model,int maxDamage, int attackDamage, double attackSpeed, ItemRarity rarity){
        ItemStack item = new ItemStack(type, 1);
        ItemMeta meta = item.getItemMeta();
        NamespacedKey key = new NamespacedKey(plugin, "test");
        AttributeModifier damage = new AttributeModifier(key,attackDamage, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND);
        AttributeModifier atkSpeed = new AttributeModifier(key,attackSpeed, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE,damage);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED,atkSpeed);
        item.setItemMeta(meta);

        item.setData(DataComponentTypes.ITEM_MODEL, model);
        item.setData(DataComponentTypes.RARITY, rarity);
        item.setData(DataComponentTypes.MAX_DAMAGE, maxDamage);
        item.setData(DataComponentTypes.ITEM_NAME, Component.text(itemName));
        item.setData(DataComponentTypes.DAMAGE_RESISTANT, DamageResistant.damageResistant(TagKey.create(RegistryKey.DAMAGE_TYPE, DamageTypeTags.IS_FIRE.key())));

        return item;
    }

    public boolean isPistonUpgradeableItem(ItemStack item){
        Set<String> names = new HashSet<>();
        names.add("netherite_pickaxe");
        names.add("netherite_axe");
        names.add("netherite_sword");
        names.add("netherite_shovel");
        names.add("netherite_hoe");
        names.add("netherite_helmet");
        names.add("netherite_chestplate");
        names.add("netherite_leggings");
        names.add("netherite_boots");
        names.add("netherite_shears");
        names.add("netherite_elytra_item");
        names.add("netherite_bow");
        names.add("netherite_crossbow");
        names.add("netherite_flint_and_steel");
        names.add("netherite_fishing_rod");
        names.add("netherite_carrot_on_a_stick");
        names.add("netherite_warped_fungus_on_a_stick");
        names.add("netherite_brush");
        names.add("netherite_mace");

        return names.contains(item.getData(DataComponentTypes.ITEM_MODEL).value());
    }

    public boolean isOpalUpgradeableItem(ItemStack item){
        Set<String> names = new HashSet<>();
        names.add("iridium_pickaxe");
        names.add("iridium_axe");
        names.add("iridium_sword");
        names.add("iridium_shovel");
        names.add("iridium_hoe");
        names.add("iridium_helmet");
        names.add("iridium_chestplate");
        names.add("iridium_leggings");
        names.add("iridium_boots");
        names.add("iridium_shears");
        names.add("iridium_elytra_item");
        names.add("iridium_bow");
        names.add("iridium_crossbow");
        names.add("iridium_flint_and_steel");
        names.add("iridium_fishing_rod");
        names.add("iridium_carrot_on_a_stick");
        names.add("iridium_warped_fungus_on_a_stick");
        names.add("iridium_brush");
        names.add("iridium_mace");

        names.add("opal_pickaxe");
        names.add("opal_axe");
        names.add("opal_sword");
        names.add("opal_shovel");
        names.add("opal_hoe");
        names.add("opal_helmet");
        names.add("opal_chestplate");
        names.add("opal_leggings");
        names.add("opal_boots");
        names.add("opal_shears");
        names.add("opal_elytra_item");
        names.add("opal_bow");
        names.add("opal_crossbow");
        names.add("opal_flint_and_steel");
        names.add("opal_fishing_rod");
        names.add("opal_carrot_on_a_stick");
        names.add("opal_warped_fungus_on_a_stick");
        names.add("opal_brush");
        names.add("opal_mace");

        return names.contains(item.getData(DataComponentTypes.ITEM_MODEL).value());
    }

    public boolean opalUpgradeEfficiency(ItemStack item){
        Set<String> names = new HashSet<>();
        names.add("iridium_pickaxe");
        names.add("iridium_axe");
        names.add("iridium_shovel");
        names.add("iridium_hoe");
        names.add("iridium_shears");

        names.add("opal_pickaxe");
        names.add("opal_axe");
        names.add("opal_shovel");
        names.add("opal_hoe");
        names.add("opal_shears");

        return names.contains(item.getData(DataComponentTypes.ITEM_MODEL).value());
    }

    public boolean opalUpgradeUnbreaking(ItemStack item){
        Set<String> names = new HashSet<>();
        names.add("iridium_elytra_item");
        names.add("iridium_flint_and_steel");
        names.add("iridium_carrot_on_a_stick");
        names.add("iridium_warped_fungus_on_a_stick");
        names.add("iridium_brush");

        names.add("opal_elytra_item");
        names.add("opal_flint_and_steel");
        names.add("opal_carrot_on_a_stick");
        names.add("opal_warped_fungus_on_a_stick");
        names.add("opal_brush");

        return names.contains(item.getData(DataComponentTypes.ITEM_MODEL).value());
    }

    public boolean opalUpgradeProtection(ItemStack item){
        Set<String> names = new HashSet<>();
        names.add("iridium_helmet");
        names.add("iridium_chestplate");
        names.add("iridium_leggings");
        names.add("iridium_boots");

        names.add("opal_helmet");
        names.add("opal_chestplate");
        names.add("opal_leggings");
        names.add("opal_boots");

        return names.contains(item.getData(DataComponentTypes.ITEM_MODEL).value());
    }

    public boolean opalUpgradePower(ItemStack item){
        Set<String> names = new HashSet<>();
        names.add("iridium_bow");
        names.add("iridium_crossbow");

        names.add("opal_bow");
        names.add("opal_crossbow");

        return names.contains(item.getData(DataComponentTypes.ITEM_MODEL).value());
    }

    public boolean opalUpgradeSharpness(ItemStack item){
        Set<String> names = new HashSet<>();
        names.add("iridium_sword");

        names.add("opal_sword");

        return names.contains(item.getData(DataComponentTypes.ITEM_MODEL).value());
    }

    public boolean opalUpgradeDensity(ItemStack item){
        Set<String> names = new HashSet<>();
        names.add("iridium_mace");

        names.add("opal_mace");

        return names.contains(item.getData(DataComponentTypes.ITEM_MODEL).value());
    }

    public boolean opalUpgradeLure(ItemStack item){
        Set<String> names = new HashSet<>();
        names.add("iridium_fishing_rod");

        names.add("opal_fishing_rod");

        return names.contains(item.getData(DataComponentTypes.ITEM_MODEL).value());
    }

    public boolean opalUpgradeOnAStick(ItemStack item){
        Set<String> names = new HashSet<>();
        names.add("iridium_carrot_on_a_stick");
        names.add("iridium_warped_fungus_on_a_stick");

        names.add("opal_carrot_on_a_stick");
        names.add("opal_warped_fungus_on_a_stick");

        return names.contains(item.getData(DataComponentTypes.ITEM_MODEL).value());
    }

    public boolean canBeInvisible(ItemStack item){
        Set<String> names = new HashSet<>();
        names.add("iridium_helmet");
        names.add("iridium_chestplate");
        names.add("iridium_leggings");
        names.add("iridium_boots");

        names.add("opal_helmet");
        names.add("opal_chestplate");
        names.add("opal_leggings");
        names.add("opal_boots");

        return names.contains(item.getData(DataComponentTypes.ITEM_MODEL).value());
    }



    public ItemStack makeArmorItem(String itemName, Material type, NamespacedKey model, Key armorModel, EquipmentSlot slot, Key sound, double armorValue, double toughnessValue, double kbResistValue, int maxDamage, ItemRarity rarity, EquipmentSlotGroup equipmentSlotGroup){
        ItemStack item = new ItemStack(type, 1);
        ItemMeta meta = item.getItemMeta();

        Equippable.Builder equippable = item.getData(DataComponentTypes.EQUIPPABLE).toBuilder()
                .assetId(armorModel)
                .equipSound(sound);

        NamespacedKey key = new NamespacedKey(plugin, model.value());
        AttributeModifier armor = new AttributeModifier(key,armorValue, AttributeModifier.Operation.ADD_NUMBER, equipmentSlotGroup);
        AttributeModifier toughness = new AttributeModifier(key,toughnessValue, AttributeModifier.Operation.ADD_NUMBER, equipmentSlotGroup);
        AttributeModifier kbresist = new AttributeModifier(key,kbResistValue, AttributeModifier.Operation.ADD_NUMBER, equipmentSlotGroup);
        meta.addAttributeModifier(Attribute.ARMOR, armor);
        meta.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, toughness);
        meta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE, kbresist);

        item.setItemMeta(meta);

        item.setData(DataComponentTypes.ITEM_MODEL, model);
        item.setData(DataComponentTypes.RARITY, rarity);
        item.setData(DataComponentTypes.MAX_DAMAGE, maxDamage);
        item.setData(DataComponentTypes.ITEM_NAME, Component.text(itemName));
        item.setData(DataComponentTypes.EQUIPPABLE, equippable);
        item.setData(DataComponentTypes.DAMAGE_RESISTANT, DamageResistant.damageResistant(TagKey.create(RegistryKey.DAMAGE_TYPE, DamageTypeTags.IS_FIRE.key())));
        meta.getEquippable().setSlot(slot);

        return item;
    }

    public ItemStack upgradeArmorItem(ItemStack item, String itemName, NamespacedKey model, Key armorModel, EquipmentSlot slot, Key sound, double armorValue, double toughnessValue, double kbResistValue, int maxDamage, ItemRarity rarity, EquipmentSlotGroup equipmentSlotGroup){
        ItemMeta meta = item.getItemMeta();

        Equippable.Builder equippable = item.getData(DataComponentTypes.EQUIPPABLE).toBuilder()
                .assetId(armorModel)
                .equipSound(sound);

        NamespacedKey key = new NamespacedKey(plugin, model.value());
        AttributeModifier armor = new AttributeModifier(key,armorValue, AttributeModifier.Operation.ADD_NUMBER, equipmentSlotGroup);
        AttributeModifier toughness = new AttributeModifier(key,toughnessValue, AttributeModifier.Operation.ADD_NUMBER, equipmentSlotGroup);
        AttributeModifier kbresist = new AttributeModifier(key,kbResistValue, AttributeModifier.Operation.ADD_NUMBER, equipmentSlotGroup);
        meta.addAttributeModifier(Attribute.ARMOR, armor);
        meta.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, toughness);
        meta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE, kbresist);

        item.setItemMeta(meta);

        item.setData(DataComponentTypes.ITEM_MODEL, model);
        item.setData(DataComponentTypes.RARITY, rarity);
        item.setData(DataComponentTypes.MAX_DAMAGE, maxDamage);
        item.setData(DataComponentTypes.ITEM_NAME, Component.text(itemName));
        item.setData(DataComponentTypes.EQUIPPABLE, equippable);
        item.setData(DataComponentTypes.DAMAGE_RESISTANT, DamageResistant.damageResistant(TagKey.create(RegistryKey.DAMAGE_TYPE, DamageTypeTags.IS_FIRE.key())));
        meta.getEquippable().setSlot(slot);

        return item;
    }

    public boolean isInventoryFull(Player p)
    {
        return p.getInventory().firstEmpty() == -1;
    }

    public void harvestItem(Block block, ItemStack item, Player p){
        if(isInventoryFull(p)){
            dropItemOnBlock(block, item);
        }
        else{
            p.getInventory().addItem(item);
        }
        p.playSound(block.getLocation(),Sound.BLOCK_CROP_BREAK,SoundCategory.BLOCKS,1,1);
        block.getWorld().spawnParticle(Particle.BLOCK,block.getLocation().add(0.5,0.5,0.5),5,0.2,0.2,0.2, block.getBlockData());
    }

    public void dropItemOnItem(Item item, ItemStack itemStack){
        item.getWorld().dropItem(item.getLocation(),itemStack);
    }

    //Use this if you want to drop an item at the location of a block if you don't have another item to drop it on.
    public void dropItemOnBlock(Block block, ItemStack itemStack){
        Location blockLoc = block.getLocation();
        Location dropLoc = blockLoc.add(0.5,0.5,0.5);
        block.getWorld().dropItem(dropLoc,itemStack);
    }

    
    public static ItemManager getItemManager(){
        return manager;
    }
    
}
