package austxnsheep.bosscore.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ColorableArmorMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface BossDrops {
    default List<ItemStack> getPiglinDrops() {
        List<ItemStack> itemList = new ArrayList<>();
        ArmorTrim trim = new ArmorTrim(TrimMaterial.REDSTONE, TrimPattern.SILENCE);

        // Redstone Armor

        //
        // Boots
        //

        //Name Component
        TextComponent bootsname = Component.text()
                .color(NamedTextColor.RED)
                .content("")
                .append(Component.text("RedStone Boots", NamedTextColor.RED, TextDecoration.BOLD))
                .build();
        //Trimming/color
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        ColorableArmorMeta bootsItemMeta = (ColorableArmorMeta) boots.getItemMeta();
        bootsItemMeta.setTrim(trim);
        bootsItemMeta.setColor(Color.RED);
        //Attributes
        bootsItemMeta.setUnbreakable(true);
        bootsItemMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.movement.speed", 0.01, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
        bootsItemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 1.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
        bootsItemMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max.health", -1.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
        bootsItemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, false);
        bootsItemMeta.setDisplayName(String.valueOf(bootsname));
        boots.setItemMeta(bootsItemMeta);
        //other
        itemList.add(boots);

        //
        //  Leggings
        //
        //Name Component
        TextComponent leggingsname = Component.text()
                .color(NamedTextColor.RED)
                .content("")
                .append(Component.text("RedStone Leggings", NamedTextColor.RED, TextDecoration.BOLD))
                .build();
        //Trimming/color
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        ColorableArmorMeta leggingsItemMeta = (ColorableArmorMeta) leggings.getItemMeta();
        leggingsItemMeta.setTrim(trim);
        leggingsItemMeta.setColor(Color.RED);
        //Attributes
        leggingsItemMeta.setUnbreakable(true);
        leggingsItemMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.movement.speed", 0.01, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
        leggingsItemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 2.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
        leggingsItemMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max.health", -1.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
        leggingsItemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, false);
        leggingsItemMeta.setDisplayName(String.valueOf(leggingsname));
        leggings.setItemMeta(leggingsItemMeta);
        //other
        itemList.add(leggings);

        //
        // chestplate
        //
        //Name Component
        TextComponent chestplatename = Component.text()
                .color(NamedTextColor.RED)
                .content("")
                .append(Component.text("RedStone Chestplate", NamedTextColor.RED, TextDecoration.BOLD))
                .build();
        //Trimming/color
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        ColorableArmorMeta chestplateItemMeta = (ColorableArmorMeta) leggings.getItemMeta();
        chestplateItemMeta.setTrim(trim);
        chestplateItemMeta.setColor(Color.RED);
        //Attributes
        chestplateItemMeta.setUnbreakable(true);
        chestplateItemMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.movement.speed", 0.02, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
        chestplateItemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 4.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
        chestplateItemMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max.health", -1.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
        chestplateItemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, false);
        chestplateItemMeta.setDisplayName(String.valueOf(chestplatename));
        chestplate.setItemMeta(chestplateItemMeta);
        //other
        itemList.add(chestplate);

        //
        // Helmet
        //
        //Name Component
        TextComponent helmetname = Component.text()
                .color(NamedTextColor.RED)
                .content("")
                .append(Component.text("RedStone Helmet", NamedTextColor.RED, TextDecoration.BOLD))
                .build();
        //Trimming/color
        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        ColorableArmorMeta helmetItemMeta = (ColorableArmorMeta) boots.getItemMeta();
        helmetItemMeta.setTrim(trim);
        helmetItemMeta.setColor(Color.RED);
        //Attributes
        helmetItemMeta.setUnbreakable(true);
        helmetItemMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.movement.speed", 0.01, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
        helmetItemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 1.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
        helmetItemMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max.health", -1.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
        helmetItemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, false);
        helmetItemMeta.setDisplayName(String.valueOf(helmetname));
        helmet.setItemMeta(helmetItemMeta);
        //other
        itemList.add(helmet);

        return itemList;
    }
}
