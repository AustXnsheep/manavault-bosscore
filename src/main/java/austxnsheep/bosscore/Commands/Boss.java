package austxnsheep.bosscore.Commands;

import austxnsheep.bosscore.CustomMoves.PiglinMoves;
import austxnsheep.bosscore.Summoning.SummonBoss;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
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
                            performCustomPiglinMove1(player.getLocation(), 10, 3);
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
                            performCustomPiglinMove5(player.getLocation(), 5, 3);
                            return true;
                        }
                        default -> {
                            player.sendMessage("PiglinMove1\nPiglinMove2\nPiglinMove3\nPiglinMove4\nPiglinMove5");
                            return true;
                        }
                    }
                case "utils":
                    switch (args[1]) {
                        case "drawPolygon" -> {
                            drawPolygon(player.getLocation(), 5, 4.0, Particle.CHERRY_LEAVES, player.getLocation().getDirection());
                            return true;
                        }
                        case "drawCircle" -> {
                            drawCircle(player.getLocation(), 4.0, Particle.CHERRY_LEAVES, player.getLocation().getDirection());
                            return true;
                        }
                        case "drawLine" -> {
                            drawLine(player.getLocation(), player.getTargetBlock(null, 100).getLocation(), Particle.CHERRY_LEAVES);
                            return true;
                        }
                        default -> {
                            player.sendMessage("drawPolygon\ndrawCircle\ndrawLine");
                            return true;
                        }
                    }

                default:
                    player.sendMessage("/boss utils\n/boss bossmove\n/boss summon");
                    return true;
            }
        }
        return false;
    }
}
