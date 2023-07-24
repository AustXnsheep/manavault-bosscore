package austxnsheep.bosscore.Utils.ParticleUtils;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public interface ShapeCreator {
    default void drawLine(Location start, Location end, Particle particle) {
        Vector direction = end.clone().subtract(start).toVector();
        double length = direction.length();
        direction.normalize();

        for (double i = 0; i <= length; i += 0.1) {
            Vector shift = direction.clone().multiply(i);
            Location loc = start.clone().add(shift);
            start.getWorld().spawnParticle(particle, loc, 1);
        }
    }
    default void drawPolygon(Location center, int vertices, double radius, Particle particle, Vector direction) {
        direction.normalize();

        Vector ortho1 = new Vector(-direction.getZ(), 0, direction.getX()).normalize();
        Vector ortho2 = direction.getCrossProduct(ortho1);

        Location firstLoc = null;
        Location prevLoc = null;
        for (int i = 0; i <= vertices; i++) {
            double angle = 2 * Math.PI * i / vertices;
            double dx = radius * Math.cos(angle);
            double dy = radius * Math.sin(angle);

            Vector shift = ortho1.clone().multiply(dx).add(ortho2.clone().multiply(dy));
            Location loc = center.clone().add(shift);

            if (prevLoc != null) {
                drawLine(prevLoc, loc, particle);
            }

            if (i == 0) {
                firstLoc = loc;
            }

            if (i == vertices) {
                drawLine(loc, firstLoc, particle);
            }

            prevLoc = loc;
        }
    }
    default void drawCircle(Location location, double radius, Particle particle, Vector direction) {
        direction.normalize();

        Vector ortho1 = new Vector(-direction.getZ(), 0, direction.getX()).normalize();
        Vector ortho2 = direction.getCrossProduct(ortho1);

        for (double i = 0; i < 360; i += 1) {
            double angle = i * Math.PI / 180;
            double dx = radius * Math.cos(angle);
            double dy = radius * Math.sin(angle);

            Vector shift = ortho1.clone().multiply(dx).add(ortho2.clone().multiply(dy));
            Location loc = location.clone().add(shift);
            location.getWorld().spawnParticle(particle, loc, 1);
        }
    }
}
