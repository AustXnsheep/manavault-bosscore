package austxnsheep.bosscore.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Skeleton;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public interface equipBossWeapon {
    default void equipSoulFireBow(Skeleton skeleton) {
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta meta = bow.getItemMeta();
        meta.addEnchant(Enchantment.ARROW_KNOCKBACK, 4, true);

        TextComponent bossname = Component.text()
                .color(NamedTextColor.RED)
                .content("")
                .append(Component.text("SoulFire Bow", NamedTextColor.RED, TextDecoration.BOLD))
                .build();

        meta.displayName(bossname);
        meta.setUnbreakable(true);
        bow.setItemMeta(meta);
        skeleton.getEquipment().setItemInMainHand(bow);
    }
}
