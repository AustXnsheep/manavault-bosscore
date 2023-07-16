package austxnsheep.bosscore.Listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import austxnsheep.bosscore.CustomEntitys.PiglinWhisperer;
import austxnsheep.bosscore.Main;

public class EntityDamageEvent implements Listener {
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity damaged = event.getEntity();

        // Check if the damager is your custom boss entity
        if (Main.containsPiglinBoss(damager)) {
            PiglinWhisperer boss = Main.findPiglinBoss(damager);
            boss.attack((Player) damaged);
        }
    }
}
