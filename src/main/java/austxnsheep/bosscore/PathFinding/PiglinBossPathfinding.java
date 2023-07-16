package austxnsheep.bosscore.PathFinding;

import com.destroystokyo.paper.entity.ai.Goal;
import com.destroystokyo.paper.entity.ai.GoalKey;
import com.destroystokyo.paper.entity.ai.GoalType;
import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.EnumSet;

public class PiglinBossPathfinding implements Goal<Skeleton> {
    private final GoalKey<Skeleton> key;
    private final Mob mob;
    private Player closestPlayer;
    private int cooldown;

    public PiglinBossPathfinding(Plugin plugin, Mob mob) {
        this.key = GoalKey.of(Skeleton.class, new NamespacedKey(plugin, "follow_players"));
        this.mob = mob;
    }

    @Override
    public boolean shouldActivate() {
        if (cooldown > 0) {
            --cooldown;
            return false;
        }
        closestPlayer = getClosestPlayer();
        return closestPlayer != null;
    }

    @Override
    public boolean shouldStayActive() {
        return shouldActivate();
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
        mob.getPathfinder().stopPathfinding();
        mob.setTarget(null);
        cooldown = 100;
    }

    @Override
    public void tick() {
        mob.setTarget(closestPlayer);
        if (mob.getLocation().distanceSquared(closestPlayer.getLocation()) < 6.25) {
            mob.getPathfinder().stopPathfinding();
        } else {
            mob.getPathfinder().moveTo(closestPlayer, 1.0D);
        }
    }

    @Override
    public @NotNull GoalKey<Skeleton> getKey() {
        return key;
    }

    @Override
    public @NotNull EnumSet<GoalType> getTypes() {
        return EnumSet.of(GoalType.MOVE, GoalType.LOOK);
    }

    private Player getClosestPlayer() {
        Collection<Player> nearbyPlayers = mob.getWorld().getNearbyPlayers(mob.getLocation(), 50.0, player ->
                !player.isDead() && player.getGameMode() != GameMode.SPECTATOR && player.isValid());
        double closestDistance = -1.0;
        Player closestPlayer = null;
        for (Player player : nearbyPlayers) {
            double distance = player.getLocation().distanceSquared(mob.getLocation());
            if (closestDistance != -1.0 && !(distance < closestDistance)) {
                continue;
            }
            closestDistance = distance;
            closestPlayer = player;
        }
        return closestPlayer;
    }
}
