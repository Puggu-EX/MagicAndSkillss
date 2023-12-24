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
import org.puggu.magicandskills.energy.PlayerEnergyManager;
import org.puggu.magicandskills.item.wand.WandTagger;
import org.puggu.magicandskills.item.wand.WandType;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventController implements Listener {
    private final MagicAndSkills plugin;
    private final WandTagger wandTagger;
    private final PlayerEnergyManager playerEnergyManager;
//    private final PlayerClickManager playerClickManager;

    public EventController(MagicAndSkills plugin) {
        this.plugin = plugin;
        this.wandTagger = new WandTagger(plugin);
        this.playerEnergyManager = new PlayerEnergyManager(plugin);
//        this.playerClickManager = new PlayerClickManager(plugin);
    }

    public final Map<UUID, List<Long>> timeSequence = new HashMap<>();
    private final Map<UUID, List<Character>> clickSequence = new HashMap<>();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND && event.getItem().getType() == Material.STICK) {
            return;
        }

//        ItemStack item = event.getItem();
//        if (item != null && wandTagger.getTypeOfWand(item) == WandType.FIRE) {
//            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 15, 3, false, false, true));
//        }

        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();

        List<Long> clickTimes = timeSequence.computeIfAbsent(playerId, k -> new CopyOnWriteArrayList<>());
        List<Character> clickActions = clickSequence.computeIfAbsent(playerId, k -> new CopyOnWriteArrayList<>());

        long currentTime = System.currentTimeMillis();
        Action action = event.getAction();

        // Convert action to char
        char actionAsChar = action.toString().contains("LEFT") ? 'L' : 'R';

        if (clickTimes.size() >= 3 || (clickTimes.size() > 0 && currentTime - clickTimes.get(0) > 1000)){
            System.out.println("Clearing");
            clickTimes.clear();
            clickActions.clear();
//            playerClickManager.resetClicks(player);
        }

        if (clickTimes.size() == 0 && action.toString().contains("RIGHT")) {
            castBasicSpell(player);
            clickTimes.clear();
            clickActions.clear();
//            playerClickManager.resetClicks(player);
            return;
        }

        clickTimes.add(currentTime);
        clickActions.add(actionAsChar);

        System.out.println(clickTimes);
        System.out.println(clickActions);

//        playerClickManager.setPlayerClicks(player, new ArrayList<>(clickActions));


//        DisplayActionBarSchedule.updateEnergyBar(player,
//                playerEnergyManager.getPlayerMana(player),
//                playerEnergyManager.getPlayerKi(player),
//                new ArrayList<>(clickActions));

        if (clickTimes.size() == 3 && clickActions.size() == 3 && currentTime - clickTimes.get(0) <= 1000) {
            castComplexSpell(player, clickActions);
            clickTimes.clear();
            clickActions.clear();
        }
    }

    private void castBasicSpell(Player player) {
        Bukkit.getServer().getPluginManager().callEvent(new FireballEvent(player));
    }

    private void castComplexSpell(Player player, List<Character> actionSequence) {

        StringBuilder charSequence = new StringBuilder();
        for (char c : actionSequence){
            charSequence.append(c);
        }

        if (charSequence.toString().equals("LLL")) {
            Bukkit.getServer().getPluginManager().callEvent(new HellzoneEvent(player));
        } else if (charSequence.toString().equals("LRL")) {
            Bukkit.getServer().getPluginManager().callEvent(new ArrowStormEvent(player));
        }
    }
}