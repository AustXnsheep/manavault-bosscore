package austxnsheep.bosscore.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Boss implements SummonBoss, CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            switch (args[0]) {
                case "summon":
                    summonBoss(args[1], player.getLocation());
                    break;
                case "list":
                    player.sendMessage("PiglinCaptain\n");
            }
            return false;
        }
        return false;
    }
}
