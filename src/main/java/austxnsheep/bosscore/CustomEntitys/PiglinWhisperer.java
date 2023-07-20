package austxnsheep.bosscore.CustomEntitys;

import austxnsheep.bosscore.Items.EquipBossEquipment;
import austxnsheep.bosscore.CustomMoves.PiglinMoves;
import austxnsheep.bosscore.NbtUtil.NBTUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import austxnsheep.bosscore.Main;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;
import java.util.Random;

public class PiglinWhisperer implements EquipBossEquipment, PiglinMoves, NBTUtil {
    //Only god knows how this works.
    private final LivingEntity bossEntity;
    private BossBar bossBar;


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

        //bossbar
        this.bossBar = Bukkit.createBossBar("Piglin Whisperer", BarColor.PURPLE, BarStyle.SOLID);

        //attributes
        Objects.requireNonNull(this.bossEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(100);
        this.bossEntity.setHealth(100);
        this.bossEntity.customName(bossname);
        this.bossEntity.setCustomNameVisible(true);

        //Equipment
        equipPiglinBossEquipment((Skeleton) this.bossEntity, 1);
        //NBT
        addCustomNBT(this.bossEntity, Main.getInstance(), "piglinwhisperer");
        //tasks
        LivingEntity loopedEntity = this.bossEntity;
        BukkitRunnable randommovetask = new BukkitRunnable() {
            @Override
            public void run() {
                if (!loopedEntity.isValid()) {
                    cancel();
                }
                Random random = new Random();
                int choice = random.nextInt(4);
                switch (choice) {
                    case 0:
                        executeRandomMove();
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        switchWeapon();
                }
                updateBossbar();
            }
        };
        randommovetask.runTaskTimer(Main.getInstance(), 20L, 0L);
    }

    public void updateBossbar() {
        double healthPercentage = this.bossEntity.getHealth() / this.bossEntity.getMaxHealth();
        this.bossBar.setProgress(healthPercentage);
    }
    public void switchWeapon() {
        if (Objects.requireNonNull(this.bossEntity.getEquipment()).getItemInMainHand().getType()==Material.BOW) {
            this.bossEntity.getEquipment().setItemInMainHand(new ItemStack(Material.GOLDEN_AXE));
        } else {
            equipPiglinBossEquipment((Skeleton) this.bossEntity, 1);
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

    public BossBar getBossBar() {
        return this.bossBar;
    }

    public void despawn() {
        this.bossEntity.remove();
    }
}
