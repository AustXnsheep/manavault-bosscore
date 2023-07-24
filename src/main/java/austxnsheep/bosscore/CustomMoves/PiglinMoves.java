package austxnsheep.bosscore.CustomMoves;

import austxnsheep.bosscore.Items.EquipBossEquipment;
import austxnsheep.bosscore.Main;
import austxnsheep.bosscore.Utils.ParticleUtils.ShapeCreator;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static org.bukkit.Bukkit.getServer;

public interface PiglinMoves extends ShapeCreator, EquipBossEquipment {
    default void performCustomPiglinMove1(Location center, double radius, double damage) {

        //It's extremely beutiful.

        center.setPitch(-90.0F);
        Vector down = center.getDirection();
        drawCircle(center, radius, Particle.DRIP_LAVA, down);
        List<Player> playerList = getPlayersInRadius(center, radius);
        for (Player player : playerList) {
            player.addPotionEffect(PotionEffectType.DARKNESS.createEffect(5, 1));
            player.damage(damage);
        }

    }
    default void performCustomPiglinMove2(Location center) {
        //textcomponent
        TextComponent minionname = Component.text()
                .color(NamedTextColor.RED)
                .content("")
                .append(Component.text("Piglin Minion", NamedTextColor.RED, TextDecoration.BOLD))
                .build();
        Location down = center.clone();
        down.setPitch(-90.0F);
        Vector direction = down.getDirection();
        drawCircle(center.clone().add(0, 5, 0), 10, Particle.GLOW, direction);
        drawPolygon(center.clone().add(0, 5, 0), 4, 10, Particle.GLOW, direction);
        drawPolygon(center.clone().add(0, 5, 0), 6, 10, Particle.GLOW, direction);
        drawCircle(center.clone().add(0, 5, 0), 5, Particle.GLOW, direction);
        drawPolygon(center.clone().add(0, 5, 0), 3, 5, Particle.GLOW, direction);
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
                if (block.isSolid()) {
                    drawLineBetweenBlocks(center.clone().add(0, 2, 0), blockLocation);
                }
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
        List<Player> playerList = getPlayersInRadius(center, 5);
        for (Player player : playerList) {
            pushPlayerUpwards(player, 1);
            player.damage(5);
        }
    }

    default void performCustomPiglinMove5(Location center, int distance, int radius) {
        List<Block> blocks = new ArrayList<>();

        Vector direction = center.getDirection();
        Location start = center.add(direction.multiply(distance));

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    blocks.add(start.clone().add(x, y, z).getBlock());
                }
            }
        }
        for (Block loopedBlock : blocks) {
            pushBlockUpwards(loopedBlock.getLocation());
        }
    }

    private int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    private List<Player> getPlayersInRadius(Location center, double radius) {
        List<Player> playerList = new ArrayList<>();
        for (Entity entity : center.getWorld().getNearbyEntities(center, radius, radius, radius)) {
            if (entity instanceof Player) {
                Player player = (Player) entity;
                playerList.add(player);
            }
        }
        return playerList;
    }

    default void pushBlockUpwards(Location center) {
        Random random = new Random();
        World world = center.getWorld();
        Block block = center.getBlock();
        Material material = world.getBlockAt(center).getType();

        FallingBlock fallingBlock = world.spawnFallingBlock(center, material.createBlockData());

        double velocityX = (random.nextDouble() - 0.5) * 0.5;
        double velocityY = random.nextDouble() * 0.5 + 0.5;
        double velocityZ = (random.nextDouble() - 0.5) * 0.5;

        fallingBlock.setVelocity(new Vector(velocityX, velocityY, velocityZ));
        block.breakNaturally();
    }

    default void pushPlayerUpwards(Player player, double strength) {
        double xVelocity = (Math.random() - 0.5) * 2.0;
        double yVelocity = Math.random() * strength;
        double zVelocity = (Math.random() - 0.5) * 2.0;

        Vector velocity = new Vector(xVelocity, yVelocity, zVelocity);
        player.setVelocity(velocity);
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
                    pushBlockUpwards(endBlock);
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
