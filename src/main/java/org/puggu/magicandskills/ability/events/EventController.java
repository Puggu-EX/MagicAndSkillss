package org.puggu.magicandskills.ability.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.clicks.CastClick;
import org.puggu.magicandskills.clicks.PlayerClick;
import org.puggu.magicandskills.energy.PlayerClickManager;
import org.puggu.magicandskills.energy.PlayerEnergyManager;
import org.puggu.magicandskills.item.wand.WandTagger;
import org.puggu.magicandskills.item.wand.WandType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventController implements Listener {
    private final MagicAndSkills plugin;
    private final PlayerEnergyManager playerEnergyManager;
    private final PlayerClickManager playerClickManager;

    public EventController(MagicAndSkills plugin) {
        this.plugin = plugin;
        this.playerEnergyManager = new PlayerEnergyManager(plugin);
        this.playerClickManager = new PlayerClickManager(plugin);
//        this.wandTagger = new WandTagger(plugin);
    }

//    public final Map<UUID, List<Long>> timeSequence = new HashMap<>();
//    private final Map<UUID, List<Character>> clickSequence = new HashMap<>();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND || event.getItem().getType() != Material.STICK) {
            return;
        }

//        ItemStack item = event.getItem();
//        if (item != null && wandTagger.getTypeOfWand(item) == WandType.FIRE) {
//            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 15, 3, false, false, true));
//        }

        Player player = event.getPlayer();

        Action action = event.getAction();

        CastClick actionAsClick = action.toString().contains("LEFT") ? CastClick.LEFT : CastClick.RIGHT;

        playerClickManager.addClick(player, actionAsClick);
    }

}