package austxnsheep.bosscore.Shapes;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public interface ShapeCreator {
    //drawCircle(3, player.getLocation(), 3) <-This would make a circle that is 3 blocks wide and that has 3 points, It by default will have more
    default List<Location> drawCircle(Integer radius, Location center, float yaw, float pitch) {
        List<Location> locations = new ArrayList<>();
        for (int i = 0; i < 360; i++) {
            double x = radius * Math.cos(Math.toRadians(i));
                double y = center.getY();
                double z = radius * Math.sin(Math.toRadians(i));

                // Rotate the circle around the center based on yaw and pitch
                Vector rotated = new Vector(x, y, z).rotateAroundY(-yaw).rotateAroundX(pitch);
                Location location = center.clone().add(rotated);
                locations.add(location);
            }
            return locations;
        }
    default List<Location> drawPolygon(Integer radius, Location center, int corners, float yaw, float pitch) {
        List<Location> locations = new ArrayList<>();
        List<Location> finallocations = new ArrayList<>();
        int V1 = 180 * (corners-2);
        int V2 = V1/corners;
        for (int i = 0; i < V1; i += V2) {
            double x = radius * Math.cos(Math.toRadians(V2));
            double y = center.getY();
            double z = radius * Math.sin(Math.toRadians(V2));

            // Rotate the circle around the center based on yaw and pitch
            Vector rotated = new Vector(x, y, z).rotateAroundY(-yaw).rotateAroundX(pitch);
            Location location = center.clone().add(rotated);
            locations.add(location);
        }
        for (int i = 0; i < corners; i++) {
            if (!(locations.get(i + 1) == null)) {
                finallocations.addAll(drawLine(locations.get(i), locations.get(i + 1), 50));
            }
        }
        return finallocations;
    }
    default void drawLineWithCurves(Location start, Location end, int numSegments, Particle particle) {
        World world = start.getWorld();
        Random rand = new Random();
        Vector direction = end.toVector().subtract(start.toVector()).normalize();
        double segmentLength = start.distance(end) / numSegments;
        Location current = start.clone();
        for (int i = 0; i < numSegments; i++) {
            double x = rand.nextDouble() * segmentLength;
            double y = rand.nextDouble() * segmentLength;
            double z = rand.nextDouble() * segmentLength;
            Vector offset = new Vector(x, y, z);
            offset.rotateAroundAxis(direction, rand.nextDouble() * 360);
            current.add(offset);
            world.spawnParticle(particle, current, 1, 0, 0, 0, 0, new Particle.DustOptions(Color.RED, 1));
        }
        world.spawnParticle(particle, end, 1, 0, 0, 0, 0, new Particle.DustOptions(Color.RED, 1));
    }
    //DrawLine(Location 1, Location 2, Amount of particles in the line)
    default List<Location> drawLine(Location start, Location end, int particles) {
        World world = start.getWorld();
        double distance = start.distance(end);
        double length = distance / particles;
        double xDiff = (end.getX() - start.getX()) / distance;
        double yDiff = (end.getY() - start.getY()) / distance;
        double zDiff = (end.getZ() - start.getZ()) / distance;

        List<Location> locations = new ArrayList<>();
        for (int i = 0; i < particles; i++) {
            double x = start.getX() + xDiff * i * length;
            double y = start.getY() + yDiff * i * length;
            double z = start.getZ() + zDiff * i * length;
            Location location = new Location(world, x, y, z);
            locations.add(location);
        }
        return locations;
    }
}
