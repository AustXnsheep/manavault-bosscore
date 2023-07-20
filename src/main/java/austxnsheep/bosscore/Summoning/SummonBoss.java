package austxnsheep.bosscore.Summoning;

import austxnsheep.bosscore.Main;
import org.bukkit.Location;
import austxnsheep.bosscore.CustomEntitys.PiglinWhisperer;

import java.util.Objects;

public interface SummonBoss {
    default void summonBoss(String bossname, Location loc) {
        if (Objects.equals(bossname, "PiglinCaptain")) {
            PiglinWhisperer boss = new PiglinWhisperer(loc);
        }
    }
}
