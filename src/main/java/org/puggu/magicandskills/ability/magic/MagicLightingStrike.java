package org.puggu.magicandskills.ability.magic;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.RayTraceResult;

public class MagicLightingStrike implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (event.getHand() == EquipmentSlot.HAND && item != null && item.getType() == Material.BONE && event.getAction().name().contains("RIGHT_CLICK")) {
            Location origin = player.getEyeLocation().clone().add(player.getEyeLocation().getDirection().multiply(2));
            RayTraceResult result = player.getWorld().rayTraceEntities(origin, player.getEyeLocation().getDirection(), 20.0);
            if (result != null && result.getHitEntity() instanceof Entity) {
                Entity entity = result.getHitEntity();
                player.sendMessage("You interacted with entity: " + entity.getType().name());

                World world = player.getWorld();
                LightningStrike lightningStrike = world.spawn(entity.getLocation(), LightningStrike.class);
            }
        }
    }

}
