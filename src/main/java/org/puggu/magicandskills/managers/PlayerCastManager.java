package org.puggu.magicandskills.managers;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.ability.events.*;
import org.puggu.magicandskills.ability.magic.MagicFireball;
import org.puggu.magicandskills.ability.skill.Substitution;

/**
 * Primary function is to take in a sequence of clicks and respond with the proper action
 * (ie cast a spell, do nothing, etc.)
 * Steps:
 * Get sequence
 * Check to see if player has a spell listed under this sequence
 * Cast ability via PlayerAbilityManager
 * (Abilities individually handle the requirements to cast spells)
 */
public class PlayerCastManager {

    private final MagicAndSkills plugin;
    private final PlayerProgressionManager playerProgressionManager;

    public PlayerCastManager(MagicAndSkills plugin) {
        this.plugin = plugin;
        this.playerProgressionManager = new PlayerProgressionManager();
    }

    public void castHandler(Player player, String castSequence) {
        // Get player Wand
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();

        if (meta == null || meta.getPersistentDataContainer().isEmpty()) {
            return;
        }

        String spell = playerProgressionManager.getSpell(player, castSequence);

        switch (spell) {
            case "MagicFireball":
                MagicFireball magicFireball = new MagicFireball(plugin, player);
                magicFireball.executeAbility();
                break;
            case "Substitution":
                Substitution substitution = new Substitution(plugin, player);
                substitution.executeAbility();
                break;
            case "FAIL":
                player.sendMessage("No spell tied to this sequence");
                break;
            default:
                // Something went wrong
                break;
        }

//        switch (castSequence) {
//            case "LLL":
//                player.sendMessage("Fireball");
//                new MagicFireball(plugin, player).executeAbility();
//                break;
//            case "RRR":
//                player.sendMessage("Substitution");
//                new Substitution(plugin, player).executeAbility();
//                break;
//            case "LRR":
//                player.sendMessage("Arrow Storm");
//                Bukkit.getServer().getPluginManager().callEvent(new ArrowStormEvent(player));
//                break;
//            case "RRR":
//                player.sendMessage("Substitution");
//                Substitution substitution = new Substitution(plugin, player);
//                Bukkit.getServer().getPluginManager().registerEvents(substitution, plugin);
//                SubstitutionEvent event = new SubstitutionEvent(player);
//                Bukkit.getServer().getPluginManager().callEvent(event);
//                event.getHandlers().unregister(substitution);
//                break;
//            case "RRL":
//                player.sendMessage("Fireball");
//                Bukkit.getServer().getPluginManager().callEvent(new FireballEvent(player));
//                break;
//            case "RLL":
//                player.sendMessage("Arrow Volley");
//                Bukkit.getServer().getPluginManager().callEvent(new ArrowVolleyEvent(player));
//                break;
        // }
    }
}
