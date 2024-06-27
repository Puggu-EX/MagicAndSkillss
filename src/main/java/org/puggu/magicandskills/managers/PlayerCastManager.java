package org.puggu.magicandskills.managers;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.ability.events.*;

public class PlayerCastManager {

    private final NamespacedKey wandTypeKey;
    private final MagicAndSkills plugin;
    private final PlayerClickManager playerClickManager;

    public PlayerCastManager(MagicAndSkills plugin) {
        this.plugin = plugin;
        this.wandTypeKey = new NamespacedKey(plugin, "wand-type");
        playerClickManager = new PlayerClickManager(plugin);
    }

    private void castBasicSpell(Player player) {
        Bukkit.getServer().getPluginManager().callEvent(new FireballEvent(player));
    }

    public void castHandler(Player player, String castSequence) {
//        player.sendMessage("Casting: " + castSequence);

        // Get player Wand
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();

        if (meta == null || meta.getPersistentDataContainer().isEmpty()) {
            return;
        }

//        PersistentDataContainer pdc = meta.getPersistentDataContainer();
//        System.out.println(pdc.get(wandTypeKey, PersistentDataType.STRING));

        // If castSequence starts with a RIGHT click then it's a SKILL
        // If castSequence starts with a LEFT click then it's a SPELL
        switch (castSequence.charAt(0)) {
            case 'L':
                // cast spell;
                break;
            case 'R':
                // cast skill
                break;
        }

        // Based off player's progression, decide what to cast

        switch (castSequence) {
            case "LLL":
                player.sendMessage("IceShield");
                Bukkit.getServer().getPluginManager().callEvent(new IceShieldEvent(player));
                break;
            case "LRL":
                player.sendMessage("Arrow Gatling");
                Bukkit.getServer().getPluginManager().callEvent(new ArrowGatlingEvent(player));
                break;
            case "LRR":
                player.sendMessage("Arrow Storm");
                Bukkit.getServer().getPluginManager().callEvent(new ArrowStormEvent(player));
                break;
            case "RRR":
                player.sendMessage("Substitution");
                Bukkit.getServer().getPluginManager().callEvent(new SubstitutionEvent(player));
                break;
            case "RRL":
                player.sendMessage("Fireball");
                Bukkit.getServer().getPluginManager().callEvent(new FireballEvent(player));
                break;
            case "RLL":
                player.sendMessage("Arrow Volley");
                Bukkit.getServer().getPluginManager().callEvent(new ArrowVolleyEvent(player));
                break;
        }
    }


}
