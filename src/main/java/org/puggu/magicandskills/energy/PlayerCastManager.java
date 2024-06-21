package org.puggu.magicandskills.energy;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.ability.events.*;
import org.puggu.magicandskills.clicks.CastClick;
import org.puggu.magicandskills.clicks.PlayerClick;

import java.util.ArrayList;
import java.util.List;

public class PlayerCastManager {

    private final MagicAndSkills plugin;
    private final PlayerClickManager playerClickManager;

    public PlayerCastManager(MagicAndSkills plugin) {
        this.plugin = plugin;
        playerClickManager = new PlayerClickManager(plugin);
    }

    private void castBasicSpell(Player player) {
        Bukkit.getServer().getPluginManager().callEvent(new FireballEvent(player));
    }

    public void castComplexSpell(Player player, String castSequence) {

        player.sendMessage("Casting: " + castSequence);
        if (castSequence.equals("LLL")) {
            player.sendMessage("Hellzone Grenade");
            Bukkit.getServer().getPluginManager().callEvent(new HellzoneEvent(player));
        } else if (castSequence.equals("LRL")) {
            player.sendMessage("Arrow Storm");
            Bukkit.getServer().getPluginManager().callEvent(new ArrowStormEvent(player));
        } else if (castSequence.equals("RRR")) {
            player.sendMessage("Substitution");
            Bukkit.getServer().getPluginManager().callEvent(new SubstitutionEvent(player));
        } else if (castSequence.equals("RRL")) {
            player.sendMessage("Fireball");
            Bukkit.getServer().getPluginManager().callEvent(new FireballEvent(player));
        }else if (castSequence.equals("RLL")) {
            player.sendMessage("FragmentationArrow");
            Bukkit.getServer().getPluginManager().callEvent(new FragmentationArrowEvent(player));
        }
    }
}
