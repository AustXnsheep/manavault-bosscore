package austxnsheep.bosscore.CustomEntitys;

import austxnsheep.bosscore.Items.equipBossWeapon;
import austxnsheep.bosscore.CustomMoves.PiglinMoves;
import austxnsheep.bosscore.PathFinding.PiglinBossPathfinding;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import austxnsheep.bosscore.Main;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;
import java.util.Random;

public class PiglinWhisperer implements equipBossWeapon, PiglinMoves {
    //Only god knows how this works.
    private final LivingEntity bossEntity;

    public PiglinWhisperer(Location spawnLocation) {
        this.bossEntity = (LivingEntity) spawnLocation.getWorld().spawnEntity(spawnLocation, EntityType.SKELETON);
        Entity entity = this.bossEntity.getWorld().spawnEntity(this.bossEntity.getLocation(), EntityType.RAVAGER);
        entity.addPassenger(this.bossEntity);

        //textcomponent
        TextComponent bossname = Component.text()
                .color(NamedTextColor.RED)
                .content("")
                .append(Component.text("Piglin Whisperer", NamedTextColor.AQUA, TextDecoration.BOLD))
                .build();
        //attributes
        Objects.requireNonNull(this.bossEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(100);
        this.bossEntity.setHealth(100);
        this.bossEntity.customName(bossname);
        this.bossEntity.setCustomNameVisible(true);
        //Custom ai, as we all could guess it doesn't work.

        this.bossEntity.setAI(false);
        Skeleton skeleton = (Skeleton) this.bossEntity;
        PiglinBossPathfinding goal = new PiglinBossPathfinding(Main.getInstance(), (Mob) this.bossEntity);
        if (!Bukkit.getMobGoals().hasGoal(skeleton, goal.getKey())) {
            Bukkit.getMobGoals().addGoal(skeleton, 3, goal);
        }

        //Equipment
        equipSoulFireBow((Skeleton) this.bossEntity);
        Objects.requireNonNull(this.bossEntity.getEquipment()).setHelmet(new ItemStack(Material.PIGLIN_HEAD));
        //other stuff
        Main.PiglinWhispererList.add(this);

        LivingEntity loopedEntity = this.bossEntity;

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!loopedEntity.isValid()) {
                    // Entity is no longer valid, stop the task
                    cancel();
                    return;
                }

                // Perform your action here
                executeRandomMove();
            }
        }.runTaskTimer(Main.getInstance(), 100L, 20L);
    }

    public void attack(LivingEntity entity) {
        entity.damage(10);
    }
    public void executeRandomMove() {
        Random random = new Random();
        int choice = random.nextInt(3); // Generates a random integer from 0 to 2

        switch (choice) {
            case 0:
                performCustomPiglinMove1(this.bossEntity.getLocation(), 5, 5);
            case 1:

            case 2:

        }
    }

    public LivingEntity getBossEntity() {
        return this.bossEntity;
    }

    public void despawn() {
        Main.PiglinWhispererList.remove(this);
        bossEntity.remove();
    }
}
