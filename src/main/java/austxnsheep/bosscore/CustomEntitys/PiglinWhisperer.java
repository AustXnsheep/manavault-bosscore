package austxnsheep.bosscore.CustomEntitys;

import austxnsheep.bosscore.Items.EquipBossWeapon;
import austxnsheep.bosscore.CustomMoves.PiglinMoves;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import austxnsheep.bosscore.Main;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;
import java.util.Random;

public class PiglinWhisperer implements EquipBossWeapon, PiglinMoves {
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

        //Equipment
        equipPiglinBossEquipment((Skeleton) this.bossEntity);
        //other stuff
        Main.PiglinWhispererList.add(this);

        LivingEntity loopedEntity = this.bossEntity;
        BukkitRunnable randommovetask = new BukkitRunnable() {
            @Override
            public void run() {
                if (!loopedEntity.isValid()) {
                    cancel();
                }
                executeRandomMove();
            }
        };
        randommovetask.runTaskLater(Main.getInstance(), 200L);
        BukkitRunnable changeweapontask = new BukkitRunnable() {
            @Override
            public void run() {
                if (!loopedEntity.isValid()) {
                    cancel();
                }
                switchWeapon();
            }
        };
        changeweapontask.runTaskLater(Main.getInstance(), 200L);
    }

    public void attack(LivingEntity entity) {
        performCustomPiglinMove4(entity.getLocation());
        entity.damage(10);
    }
    public void switchWeapon() {
        if (Objects.requireNonNull(this.bossEntity.getEquipment()).getItemInMainHand().equals(new ItemStack(Material.BOW))) {
            this.bossEntity.getEquipment().setItemInMainHand(new ItemStack(Material.GOLDEN_AXE));
        } else {
            equipPiglinBossEquipment((Skeleton) this.bossEntity);
        }
    }
    public void executeRandomMove() {
        Random random = new Random();
        int choice = random.nextInt(3);

        switch (choice) {
            case 0:
                performCustomPiglinMove1(this.bossEntity.getLocation(), 5, 5);
            case 1:
                performCustomPiglinMove2(this.bossEntity.getLocation());
            case 2:
                performCustomPiglinMove3(this.bossEntity.getLocation());
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
