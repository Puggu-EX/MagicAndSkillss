package org.puggu.magicandskills.ability.skill;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.puggu.magicandskills.MagicAndSkills;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Dash implements Listener {
    private final MagicAndSkills plugin;

    public Dash(MagicAndSkills plugin) {
        this.plugin = plugin;
    }

    private final Map<UUID, Long> shiftMap = new HashMap<>();
    private final Map<UUID, Boolean> cooldownMap = new HashMap<>();

//    @EventHandler
//    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
//        Player player = event.getPlayer();
//        if (!shiftMap.containsKey(player.getUniqueId())) {
//            shiftMap.put(player.getUniqueId(), System.currentTimeMillis());
//            return;
//        }
//
//        long lastShiftTime = shiftMap.get(player.getUniqueId());
//        if (((System.currentTimeMillis() - lastShiftTime) < 500) && !cooldownMap.get(player.getUniqueId())) {
//            player.setVelocity(player.getLocation().getDirection().multiply(1.5).setY(.5));
//            player.sendMessage("Dash");
//            cooldownMap.put(player.getUniqueId(), true);
//        }
//        shiftMap.remove(player.getUniqueId());
//    }

//    @EventHandler
//    public void onPlayerMovement(PlayerMoveEvent event) {
//        Player player = event.getPlayer();
//        Block block = player.getLocation().subtract(0, 1, 0).getBlock();
////        if (System.currentTimeMillis() - )
//        if (!block.isPassable() && !block.isLiquid() && block.getType() != Material.AIR) {
////        if (block.getType() != Material.AIR){
////        if(((LivingEntity) player).isOnGround()){
//            cooldownMap.put(player.getUniqueId(), false);
//        }
//    }
}
