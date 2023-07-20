package austxnsheep.bosscore.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    @EventHandler
    public void playerjoin(PlayerJoinEvent e) {
        e.getPlayer().setCollidable(false);
    }
}
