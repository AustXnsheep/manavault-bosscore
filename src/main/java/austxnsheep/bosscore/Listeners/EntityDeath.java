package austxnsheep.bosscore.Listeners;

import austxnsheep.bosscore.Main;
import austxnsheep.bosscore.Utils.NbtUtil.NBTUtil;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Objects;

public class EntityDeath implements Listener, NBTUtil {
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Entity damaged = event.getEntity();

        if (Objects.equals(getCustomNBT(damaged, Main.getInstance(), "custombosstype"), "piglinwhisperer")) {

        }
    }
}
