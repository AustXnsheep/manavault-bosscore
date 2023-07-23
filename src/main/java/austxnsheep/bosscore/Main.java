package austxnsheep.bosscore;

import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import austxnsheep.bosscore.Commands.Boss;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public final class Main extends JavaPlugin {
    private static Plugin instance;

    @Override
    public void onEnable() {
        instance = this;
        Objects.requireNonNull(this.getCommand("boss")).setExecutor(new Boss());
    }

    @Override
    public void onDisable() {
    }

    public static Plugin getInstance() {
        return instance;
    }

    public static String generateBossBarId() {
        Random random = new Random();
        return String.valueOf(random.nextInt(100000));
    }
}
