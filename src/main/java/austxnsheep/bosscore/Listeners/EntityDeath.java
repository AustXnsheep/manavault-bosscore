package austxnsheep.bosscore.Listeners;

import austxnsheep.bosscore.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import static austxnsheep.bosscore.Main.containsPiglinBoss;
import static austxnsheep.bosscore.Main.findPiglinBoss;

public class EntityDeath implements Listener {
    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (containsPiglinBoss(e.getEntity())) {
            Main.PiglinWhispererList.remove(findPiglinBoss(e.getEntity()));
        }
    }
}
