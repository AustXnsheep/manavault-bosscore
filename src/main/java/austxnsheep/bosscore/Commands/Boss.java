package austxnsheep.bosscore.Commands;

import austxnsheep.bosscore.CustomMoves.PiglinMoves;
import austxnsheep.bosscore.Summoning.SummonBoss;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Boss implements SummonBoss, PiglinMoves, CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            switch (args[0]) {
                case "summon":
                    switch (args[1]) {
                        case "PiglinCaptain" -> summonBoss(args[1], player.getLocation());
                        case "list" -> player.sendMessage("PiglinCaptain\n");
                    }
                    break;
                case "bossmove":
                    switch (args[1]) {
                        case "PiglinMove1" -> performCustomPiglinMove1(player.getLocation(), 5, 5);
                        case "PiglinMove2" -> performCustomPiglinMove2(player.getLocation());
                        case "PiglinMove3" -> performCustomPiglinMove3(player.getLocation());
                        case "PiglinMove4" -> performCustomPiglinMove4(player.getLocation());
                        case "list" -> player.sendMessage("PiglinMove1\nPiglinMove2\nPiglinMove3\nPiglinMove4");
                    }
                    break;
                case "help":
                    player.sendMessage("/boss summon list\n/boss bossmove list\n/boss summon list");
                    break;
            }
            return false;
        }
        return false;
    }
}
