package austxnsheep.bosscore.CustomEntitys;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Skeleton;
import org.bukkit.inventory.ItemStack;
import austxnsheep.bosscore.Main;
import austxnsheep.bosscore.PathFinding.PiglinBossPathfinding;

import java.util.Objects;

public class PiglinWhisperer {
    //Only god knows how this works.
    private LivingEntity bossEntity;

    public PiglinWhisperer(Location spawnLocation) {
        this.bossEntity = (LivingEntity) spawnLocation.getWorld().spawnEntity(spawnLocation, EntityType.SKELETON);

        this.bossEntity.setMaxHealth(100);
        this.bossEntity.setHealth(100);
        this.bossEntity.setCustomName("Custom Boss");
        this.bossEntity.setCustomNameVisible(true);
        Objects.requireNonNull(this.bossEntity.getEquipment()).setHelmet(new ItemStack(Material.PIGLIN_HEAD));

        this.bossEntity.setAI(false);
        Skeleton skeleton = (Skeleton) this.bossEntity;
        PiglinBossPathfinding goal = new PiglinBossPathfinding(Main.getInstance(), (Mob) this.bossEntity);
        if (!Bukkit.getMobGoals().hasGoal(skeleton, goal.getKey())) {
            Bukkit.getMobGoals().addGoal(skeleton, 3, goal);
        }

        Main.PiglinWhispererList.add(this);
    }

    public void attack(LivingEntity entity) {
        entity.damage(10);
    }

    public LivingEntity getBossEntity() {
        return this.bossEntity;
    }

    public void despawn() {
        Main.PiglinWhispererList.remove(this);
        bossEntity.remove();
    }
}
