package org.puggu.magicandskills.managers;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.persistence.PersistentDataType;
import org.puggu.magicandskills.MagicAndSkills;

/**
 * The cooldown time between when a player can cast another spell
 */
public class PlayerCooldownManager implements Listener {

    private final NamespacedKey cooldown;

    public PlayerCooldownManager(MagicAndSkills plugin) {
        cooldown = new NamespacedKey(plugin, "cooldown");
    }

    public boolean hasGlobalCooldownKey(Player player){
        return player.getPersistentDataContainer().has(cooldown, PersistentDataType.LONG);
    }

    public long getGlobalCooldown(Player player) {
        Long value = player.getPersistentDataContainer().get(cooldown, PersistentDataType.LONG);
        if (value == null){
            return -1L;
        }
        return value;
    }

    public void setOnGlobalCooldown(Player player) {
        player.getPersistentDataContainer().set(cooldown, PersistentDataType.LONG, System.currentTimeMillis());
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        // Get player abilities
        // Generate CooldownManager based off abilities
    }

    @EventHandler
    public void onPlayerLeave(final PlayerQuitEvent event) {
        // Deactivate CooldownManager for this player
    }
}
