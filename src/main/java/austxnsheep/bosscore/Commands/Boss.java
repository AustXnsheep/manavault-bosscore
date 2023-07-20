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
                        case "PiglinCaptain" -> {
                            summonBoss(args[1], player.getLocation());
                            return true;
                        }
                        default -> {
                            player.sendMessage("PiglinCaptain\n");
                            return true;
                        }
                    }
                case "bossmove":
                    switch (args[1]) {
                        case "PiglinMove1" -> {
                            performCustomPiglinMove1(player.getLocation(), 5, 5);
                            return true;
                        }
                        case "PiglinMove2" -> {
                            performCustomPiglinMove2(player.getLocation());
                            return true;
                        }
                        case "PiglinMove3" -> {
                            performCustomPiglinMove3(player.getLocation());
                            return true;
                        }
                        case "PiglinMove4" -> {
                            performCustomPiglinMove4(player.getLocation());
                            return true;
                        }
                        case "PiglinMove5" -> {
                            performCustomPiglinMove5(player.getLocation(), 5, 5);
                            return true;
                        }
                        case "test" -> {
                            test(player.getLocation());
                            return true;
                        }
                        default -> {
                            player.sendMessage("PiglinMove1\nPiglinMove2\nPiglinMove3\nPiglinMove4");
                            return true;
                        }
                    }
                case "help":
                    player.sendMessage("/boss summon list\n/boss bossmove list\n/boss summon list");
                    return true;
            }
            return false;
        }
        return false;
    }
}
