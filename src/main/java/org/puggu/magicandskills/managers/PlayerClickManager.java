package org.puggu.magicandskills.managers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.persistence.PersistentDataType;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.actionbar.UpdateActionBarEvent;
import org.puggu.magicandskills.clicks.CastClick;
import org.puggu.magicandskills.clicks.PlayerClick;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Captures player clicks and add them to a hashmap with their UUID as the key.
 * If a sequence of clicks (3) are captured within the set time frame, the
 * sequence is sent to the PlayerCastManager
 */
public class PlayerClickManager implements Listener {
    private final MagicAndSkills plugin;
    private final int sequenceLength = 5; // A spell is a sequence of X clicks
    PlayerCastManager castManager;

    private static final ConcurrentHashMap<UUID, List<PlayerClick>> playerClicks = new ConcurrentHashMap<>();

    public PlayerClickManager(MagicAndSkills plugin) {
        this.plugin = plugin;
        this.castManager = new PlayerCastManager(plugin);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        // Only handle if player is using a stick with a PDC containing "wand-type" in main hand
        if (!event.hasItem() ||
                Objects.requireNonNull(event.getItem()).getType() != Material.STICK ||
                event.getHand() != EquipmentSlot.HAND ||
                !Objects.requireNonNull(event.getItem().getItemMeta()).getPersistentDataContainer().has(
                        new NamespacedKey(plugin, "wand-type"), PersistentDataType.STRING)) {
            return;
        }

//        Player player = event.getPlayer();
//        Action action = event.getAction();

        CastClick actionAsClick = event.getAction().toString().contains("LEFT") ? CastClick.LEFT : CastClick.RIGHT;
//        CastClick actionAsClick = action.toString().contains("LEFT") ? CastClick.LEFT : CastClick.RIGHT;

        addClick(event.getPlayer(), actionAsClick);
//        addClick(player, actionAsClick);

//        event.setCancelled(true);
    }

    public void clearClicks(Player player) {
        playerClicks.remove(player.getUniqueId());
    }

    public void addClick(Player player, CastClick click) {
        List<PlayerClick> clicks = getPlayerClicks(player);

        if (clicks == null || clicks.size() >= sequenceLength || System.currentTimeMillis() - clicks.getLast().getTime() > 1000) {
            clicks = new ArrayList<>();
        }

        clicks.add(new PlayerClick(click, System.currentTimeMillis()));
        playerClicks.put(player.getUniqueId(), clicks);

        player.playSound(player, Sound.BLOCK_LEVER_CLICK, .5f, 1f);


        Bukkit.getServer().getPluginManager().callEvent(new UpdateActionBarEvent(player));

        if (clicks.size() == sequenceLength) {
            castManager.castHandler(player, getCastSequenceAsString(player));
            clearClicks(player);
        }

    }

    public List<PlayerClick> getPlayerClicks(Player player) {
        return playerClicks.get(player.getUniqueId());
    }

    public List<CastClick> getCastSequence(Player player) {
        List<PlayerClick> clicks = getPlayerClicks(player);
        List<CastClick> castClicks = new ArrayList<>();
        for (PlayerClick click : clicks) {
            castClicks.add(click.getClick());
        }
        return castClicks;
    }

    public String getCastSequenceAsString(Player player) {
        List<PlayerClick> clicks = playerClicks.get(player.getUniqueId());

        // no clicks or last click was longer than a second ago
        if (clicks == null || System.currentTimeMillis() - clicks.getLast().getTime() > 1000) {
            return "#####";
        }

        StringBuilder castClicks = new StringBuilder();
        for (PlayerClick click : clicks) {
            char clickAsChar = (click.getClick() == CastClick.LEFT) ? 'L' : 'R';
            castClicks.append(clickAsChar);
        }
        return castClicks.toString();
    }
}
