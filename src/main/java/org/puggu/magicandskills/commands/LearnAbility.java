package org.puggu.magicandskills.commands;

import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.ability.magic.MagicFireball;
import org.puggu.magicandskills.ability.skill.ArrowGatling;
import org.puggu.magicandskills.ability.skill.Substitution;
import org.puggu.magicandskills.managers.PlayerProgressionManager;

public class LearnAbility implements CommandExecutor {

    private final MagicAndSkills plugin;

    public LearnAbility(MagicAndSkills plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) || args.length < 2) {
            return false;
        }

        PlayerProgressionManager ppd = new PlayerProgressionManager();
        Player player = (Player) sender;
        NamespacedKey key;

        switch (args[1].toLowerCase()) {
            case "magicfireball":
                key = new MagicFireball(plugin, player).getAbilityKey();
                break;
            case "substitution":
                key = new Substitution(plugin, player).getAbilityKey();
                break;
            case "arrowgatling":
                key = new ArrowGatling(plugin, player).getAbilityKey();
                break;
            default:
                return false;
        }

        if (args[0].equals("learn")) {
            ppd.playerLearnAbility(player, key);
        } else if (args[0].equals("forget")) {
            ppd.playerForgetAbility(player, key);
        }

        return true;
    }

}

