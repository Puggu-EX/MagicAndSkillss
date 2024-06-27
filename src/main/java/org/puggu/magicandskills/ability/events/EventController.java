package org.puggu.magicandskills.ability.events;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.persistence.PersistentDataType;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.clicks.CastClick;
import org.puggu.magicandskills.managers.PlayerClickManager;

import java.util.*;

public class EventController implements Listener {
    private final PlayerClickManager playerClickManager;

    public EventController(MagicAndSkills plugin) {
        this.playerClickManager = new PlayerClickManager(plugin);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        // Only handle if player is using a stick with a PDC containing "wand-type" in main hand
        if (!event.hasItem() ||
                Objects.requireNonNull(event.getItem()).getType() != Material.STICK ||
                event.getHand() != EquipmentSlot.HAND ||
                !Objects.requireNonNull(event.getItem().getItemMeta()).getPersistentDataContainer().has(
                        new NamespacedKey(MagicAndSkills.getPlugin(), "wand-type"), PersistentDataType.STRING)) {
            return;
        }

        Player player = event.getPlayer();
        Action action = event.getAction();

        CastClick actionAsClick = action.toString().contains("LEFT") ? CastClick.LEFT : CastClick.RIGHT;

        playerClickManager.addClick(player, actionAsClick);

        event.setCancelled(true);
    }
}