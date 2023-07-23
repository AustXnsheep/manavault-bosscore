package austxnsheep.bosscore.Listeners;

import austxnsheep.bosscore.CustomMoves.PiglinMoves;
import austxnsheep.bosscore.Utils.NbtUtil.NBTUtil;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import austxnsheep.bosscore.Main;

import java.util.Objects;
import java.util.Random;

public class EntityDamage implements Listener, NBTUtil, PiglinMoves {
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity damaged = event.getEntity();

        if (Objects.equals(getCustomNBT(damager, Main.getInstance(), "custombosstype"), "piglinwhisperer")) {
            if (damager.getType()==EntityType.SKELETON && damaged instanceof Player) {
                Skeleton entityasSkeleton = (Skeleton) damager;
                if (entityasSkeleton.getEquipment().getItemInMainHand().getType()== Material.GOLDEN_AXE) {
                    pushPlayerUpwards((Player) damaged, 1);
                    performCustomPiglinMove5(damager.getLocation(), 3, 3);
                    return;
                }
                Random random = new Random();
                int choice = random.nextInt(3);
                if (choice == 1) {
                    performCustomPiglinMove4(damaged.getLocation());
                }
            }
        }
        if (Objects.equals(getCustomNBT(damaged, Main.getInstance(), "custombosstype"), "piglinwhisperer")) {
            if (damaged.getType()==EntityType.SKELETON) {

            }
        }
    }
}
