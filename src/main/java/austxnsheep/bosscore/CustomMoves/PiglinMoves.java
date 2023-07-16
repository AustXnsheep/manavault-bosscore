package austxnsheep.bosscore.CustomMoves;

import austxnsheep.bosscore.Main;
import austxnsheep.bosscore.Shapes.ShapeCreator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public interface PiglinMoves extends ShapeCreator {
    default void performCustomPiglinMove1(Location center, double radius, double damage) {

        //there is NO WAY there isn't a better way to do this.

        //drawCircle(Integer radius, Location center, float yaw, float pitch) {
        List<Location> locationlist = drawCircle((int) radius, center, 0, 0);
        List<Player> playersinradius = null;
        for (Player player : Bukkit.getOnlinePlayers()) {
            double distance = center.distance(player.getLocation());
            if (distance <= radius) {
                assert false;
                playersinradius.add(player);
            }
        }
        for (Location loc : locationlist) {
            Particle particle = Particle.REDSTONE;
            center.getWorld().spawnParticle(particle, loc, 10, 0.5f, 0.5f, 5);
        }
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            // Code to be executed after the delay
            assert false;
            for (Player player : playersinradius) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 5, 2));
                player.damage(damage);
            }
        }, 20L); // Delay of 20 ticks (1 second)
    }
}
