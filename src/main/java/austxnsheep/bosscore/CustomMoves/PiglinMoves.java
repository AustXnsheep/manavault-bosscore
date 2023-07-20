package austxnsheep.bosscore.CustomMoves;

import austxnsheep.bosscore.Items.EquipBossWeapon;
import austxnsheep.bosscore.Main;
import austxnsheep.bosscore.Particles.ShapeCreator;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import static org.bukkit.Bukkit.getServer;

public interface PiglinMoves extends ShapeCreator, EquipBossWeapon {
    default void performCustomPiglinMove1(Location center, double radius, double damage) {

        //there is NO WAY there isn't a better way to do this.
        List<Player> playersinradius = null;
        for (Player player : Bukkit.getOnlinePlayers()) {
            double distance = center.distance(player.getLocation());
            if (distance <= radius) {
                playersinradius.add(player);
            }
        }
        //one day circle logic will be here.


        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            // Code to be executed after the delay
            for (Player player : playersinradius) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 5, 2));
                player.damage(damage);
            }
        }, 20L);
    }
    default void performCustomPiglinMove2(Location center) {
        //textcomponent
        TextComponent minionname = Component.text()
                .color(NamedTextColor.RED)
                .content("")
                .append(Component.text("Piglin Minion", NamedTextColor.RED, TextDecoration.BOLD))
                .build();
        for (int i = 0; i <= getRandomNumber(5, 10); i++){
            Vector vector = center.getDirection();
            Location newlocation = center.add(vector);
            Entity piglin = center.getWorld().spawnEntity(getRandomLocation(newlocation, 2), EntityType.PIGLIN);
            piglin.setVisualFire(true);
            piglin.setCustomNameVisible(true);
            piglin.customName(minionname);
            //equipment
            equipPiglinMinionEquipment((Piglin) piglin);

        }
    }

    default void performCustomPiglinMove3(Location center) {
        Location spawnLocation = getRandomLocation(center, 10);
        Piglin piglin = (Piglin) spawnLocation.getWorld().spawnEntity(spawnLocation, EntityType.PIGLIN);
        Hoglin hoglin = (Hoglin) spawnLocation.getWorld().spawnEntity(spawnLocation, EntityType.HOGLIN);
        hoglin.addPassenger(piglin);

        //textcomponent
        TextComponent piglinname = Component.text()
                .color(NamedTextColor.RED)
                .content("")
                .append(Component.text("Hoglin Rider", NamedTextColor.BLUE, TextDecoration.BOLD))
                .build();
        //attributes
        Objects.requireNonNull(piglin.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(50);
        piglin.setHealth(50);
        piglin.customName(piglinname);
        piglin.setCustomNameVisible(true);
        //equipment
        equipPiglinMinionEquipment(piglin);

    }

    default void performCustomPiglinMove4(Location center) {
        for (int i = 0; i < 500; i++) {
            Block block = getRandomBlock(center, 10).getBlock();
            double newy = block.getLocation().getY() + 1.0;
            Location blockLocation = new Location(block.getWorld(), block.getLocation().getX(), newy, block.getLocation().getZ(), block.getLocation().getYaw(), block.getLocation().getPitch());
            if (block.isBuildable()) {
                drawLineBetweenBlocks(center, blockLocation);
            }
        }
        Location newLocation = center.subtract(1, 1, 1);
        Giant giant = (Giant) center.getWorld().spawnEntity(center, EntityType.GIANT);
        giant.setInvisible(true);
        giant.setInvulnerable(true);
        giant.setCollidable(false);
        giant.setCustomName("Dinnerbone");
        giant.teleport(newLocation);
        giant.setBodyYaw(0);
        giant.getEquipment().setItemInMainHand(new ItemStack(Material.GOLDEN_AXE));
        giant.setCustomNameVisible(false);
        giant.setAI(false);
        giant.setGravity(false);
        center.getWorld().playSound(center, Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
        getServer().getScheduler().runTaskLater(Main.getInstance(), giant::remove, 5 * 20L);
    }

    private int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    private static void throwBlockUpwards(Location center) {
        Random random = new Random();
        World world = center.getWorld();
        Material material = world.getBlockAt(center).getType();

        FallingBlock fallingBlock = world.spawnFallingBlock(center, material.createBlockData());

        double velocityX = (random.nextDouble() - 0.5) * 0.5;
        double velocityY = random.nextDouble() * 0.5 + 0.5;
        double velocityZ = (random.nextDouble() - 0.5) * 0.5;

        fallingBlock.setVelocity(new Vector(velocityX, velocityY, velocityZ));
    }

    private static Location getRandomBlock(Location center, int radius) {
        Random random = new Random();
        World world = center.getWorld();
        int x = center.getBlockX();
        int y = center.getBlockY();
        int z = center.getBlockZ();

        int randomX = x + random.nextInt(radius * 2 + 1) - radius;
        int randomY = y + random.nextInt(radius * 2 + 1) - radius;
        int randomZ = z + random.nextInt(radius * 2 + 1) - radius;

        return new Location(world, randomX, randomY, randomZ);
    }

    private Location getRandomLocation(Location center, int radius) {
        World world = center.getWorld();
        double x = center.getX() + (Math.random() * (2 * radius) - radius);
        double y = center.getY() + 10;
        double z = center.getZ() + (Math.random() * (2 * radius) - radius);
        return new Location(world, x, y, z);
    }

    private void drawLineBetweenBlocks(Location startBlock, Location endBlock) {
        World world = startBlock.getWorld();
        Vector direction = endBlock.toVector().subtract(startBlock.toVector());
        int points = 50; // Number of points to create the line

        new BukkitRunnable() {
            int currentPoint = 0;

            @Override
            public void run() {
                if (currentPoint >= points) {
                    startBlock.getWorld().playSound(endBlock, Sound.BLOCK_GLASS_BREAK,1.0f, 1.0f);
                    throwBlockUpwards(endBlock);
                    this.cancel();
                    return;
                }

                Vector pointVector = startBlock.toVector().add(direction.clone().multiply(currentPoint / (double) points));
                Location pointLocation = new Location(world, pointVector.getX(), pointVector.getY(), pointVector.getZ());
                world.spawnParticle(Particle.CLOUD, pointLocation, 0, 0, 0, 0, 1);

                currentPoint++;
            }
        }.runTaskTimer(Main.getInstance(), 0L, 1L); // Run every tick (1/20th of a second)
    }
}
