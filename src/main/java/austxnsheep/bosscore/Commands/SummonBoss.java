package austxnsheep.bosscore.Commands;

import org.bukkit.Location;
import austxnsheep.bosscore.CustomEntitys.PiglinWhisperer;

import java.util.Objects;

public interface SummonBoss {
    default void summonBoss(String bossname, Location loc) {
        if (Objects.equals(bossname, "PiglinCaptain")) {
            new PiglinWhisperer(loc);
        }
    }
}
