package austxnsheep.bosscore.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.Skeleton;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public interface EquipBossEquipment {
    default void equipPiglinBossEquipment(Skeleton skeleton, int id) {
        if (id==1) {
            ItemStack bow = new ItemStack(Material.BOW);
            ItemMeta meta = bow.getItemMeta();
            meta.addEnchant(Enchantment.ARROW_KNOCKBACK, 4, true);

            TextComponent weaponName = Component.text()
                    .color(NamedTextColor.RED)
                    .content("")
                    .append(Component.text("SoulFire Bow", NamedTextColor.RED, TextDecoration.BOLD))
                    .build();

            meta.displayName(weaponName);
            meta.setUnbreakable(true);
            bow.setItemMeta(meta);
            Objects.requireNonNull(skeleton.getEquipment()).setHelmet(new ItemStack(Material.PIGLIN_HEAD));
            skeleton.getEquipment().setItemInMainHand(bow);
        }
        if (id==2) {
            ItemStack axe = new ItemStack(Material.GOLDEN_AXE);
            ItemMeta meta = axe.getItemMeta();
            meta.addEnchant(Enchantment.DAMAGE_ALL, 6, true);

            TextComponent weaponName = Component.text()
                    .color(NamedTextColor.RED)
                    .content("")
                    .append(Component.text("Solstice Sunderer", NamedTextColor.RED, TextDecoration.BOLD))
                    .build();

            meta.displayName(weaponName);
            meta.setUnbreakable(true);
            skeleton.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
            skeleton.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
            skeleton.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
            skeleton.getEquipment().setItemInMainHand(axe);

        }
    }

    default void equipPiglinMinionEquipment(Piglin piglin) {
        piglin.getEquipment().setChestplate(new ItemStack(Material.GOLDEN_CHESTPLATE));
        piglin.getEquipment().setItemInMainHand(new ItemStack(Material.GOLDEN_AXE));
    }
}
