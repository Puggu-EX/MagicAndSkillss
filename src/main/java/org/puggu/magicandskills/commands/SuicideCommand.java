package org.puggu.magicandskills.commands;

import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.menusystem.menu.SuicideConfirmMenu;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SuicideCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;

            //create the menu and then open it for the player
            new SuicideConfirmMenu(MagicAndSkills.getPlayerMenuUtility(p)).open();
        }

        return true;
    }
}
