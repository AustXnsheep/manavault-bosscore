package austxnsheep.bosscore.Particles;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public interface ShapeCreator {

    //This code is beyond god awful.

    //drawCircle(3, player.getLocation(), 3) <-This would make a circle that is 3 blocks wide and that has 3 points, It by default will have more
    default void drawCircle(Location center, Particle particle, double radius, int count, float pitch, float yaw) {
        World world = center.getWorld();
        if (world == null) return;

        // Convert pitch and yaw to radians for calculations
        double pitchRad = Math.toRadians(pitch);
        double yawRad = Math.toRadians(yaw);

        for (int i = 0; i < count; i++) {
            double angle = 2 * Math.PI * i / count;
            double x = radius * Math.cos(angle);
            double y = radius * Math.sin(angle);
            double z = 0;

            // Apply pitch (rotation around X-axis)
            double newY = y * Math.cos(pitchRad) - z * Math.sin(pitchRad);
            double newZ = y * Math.sin(pitchRad) + z * Math.cos(pitchRad);
            y = newY;
            z = newZ;

            // Apply yaw (rotation around Y-axis)
            double newX = x * Math.cos(yawRad) + z * Math.sin(yawRad);
            newZ = -x * Math.sin(yawRad) + z * Math.cos(yawRad);
            x = newX;
            z = newZ;

            Location particleLoc = center.clone().add(new Vector(x, y, z));

            world.spawnParticle(particle, particleLoc, 1);
        }
    }

    default void drawPolygon(Location center, double radius, int sides, Particle particle, Vector direction) {
        World world = center.getWorld();
        if (world == null) return;

        // Normalize the direction vector and create a perpendicular vector
        Vector perp = new Vector(-direction.getZ(), 0, direction.getX()).normalize();

        for (int i = 0; i < sides; i++) {
            double angle = 2 * Math.PI * i / sides;
            double dx = radius * Math.cos(angle);
            double dz = radius * Math.sin(angle);

            // Rotate the point around the center
            Vector point = perp.clone().multiply(dx).add(direction.clone().multiply(dz));
            Location particleLocation = center.clone().add(point);
            world.spawnParticle(particle, particleLocation, 1);
        }
    }
    //DrawLine(Location 1, Location 2, Amount of particles in the line)
    default void drawLine(Location start, Location end, Particle particle, int particles) {
        World world = start.getWorld();
        if (world == null || !world.equals(end.getWorld())) return;

        double distance = start.distance(end);
        Vector vector = end.toVector().subtract(start.toVector()).normalize().multiply(distance / particles);

        for (int i = 0; i < particles; i++) {
            Location particleLocation = start.clone().add(vector.clone().multiply(i));
            world.spawnParticle(particle, particleLocation, 1);
        }
    }
}
