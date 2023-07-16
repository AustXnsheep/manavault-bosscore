package austxnsheep.bosscore;

import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import austxnsheep.bosscore.Commands.Boss;
import austxnsheep.bosscore.CustomEntitys.PiglinWhisperer;

import java.util.List;
import java.util.Objects;

public final class Main extends JavaPlugin {
    public static List<PiglinWhisperer> PiglinWhispererList;
    private static Plugin instance;

    @Override
    public void onEnable() {
        instance = this;
        Objects.requireNonNull(this.getCommand("boss")).setExecutor(new Boss());
    }

    @Override
    public void onDisable() {
        for (PiglinWhisperer piglin : PiglinWhispererList) {
            piglin.despawn();
        }
    }

    public static Plugin getInstance() {
        return instance;
    }

    public static boolean containsPiglinBoss(Entity e) {
        for (PiglinWhisperer piglin : PiglinWhispererList) {
            if (piglin.getBossEntity() == e) {
                return true;
            }
        }
        return false;
    }
    public static PiglinWhisperer findPiglinBoss(Entity e) {
        for (PiglinWhisperer piglin : PiglinWhispererList) {
            if (piglin.getBossEntity() == e) {
                return piglin;
            }
        }
        return null;
    }
}
