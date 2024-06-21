package org.puggu.magicandskills.ability.magic;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.RayTraceResult;
import org.puggu.magicandskills.ability.Ability;
import org.puggu.magicandskills.MagicAndSkills;

public class BindSpell extends MagicSpell implements Listener {
    // TODO: Currently restricting player movement based on the PlayerMoveEvent, this works but could be costly as it checks every time the player moves

    private final MagicAndSkills plugin;

    public BindSpell(MagicAndSkills plugin) {
        super(plugin, 10000, 10);
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (event.getHand() == EquipmentSlot.HAND && item != null && item.getType() == Material.ARROW && event.getAction().name().contains("RIGHT_CLICK")) {
            Location origin = player.getEyeLocation().clone().add(player.getEyeLocation().getDirection().multiply(2));
            RayTraceResult result = player.getWorld().rayTraceEntities(origin, player.getEyeLocation().getDirection(), 90.0);

            if (result != null && result.getHitEntity() != null) {
                Entity entity = result.getHitEntity();
                if (entity instanceof Mob) {
                    player.playSound(player, Sound.ENTITY_LEASH_KNOT_PLACE, 1f, 1f);
                    Mob mob = (Mob) entity;
                    mob.setAI(false);
                    mob.setGravity(false);

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.playSound(player, Sound.ENTITY_LEASH_KNOT_BREAK, 1f, 1f);
                            mob.setAI(true);
                            mob.setGravity(true);
                        }
                    }.runTaskLater(plugin, 80);
                } else if (entity instanceof Player) {
                    player.playSound(player, Sound.ENTITY_LEASH_KNOT_PLACE, 1f, 1f);
                    Player targetPlayer = (Player) entity;

                    targetPlayer.setMetadata("CannotMove", new FixedMetadataValue(plugin, true));
//                    targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 2, true, false, true));

                    targetPlayer.playSound(targetPlayer, Sound.ENTITY_LEASH_KNOT_PLACE, 1f, 1f);
                    targetPlayer.sendMessage("You have been BOUNDED!");
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.playSound(player, Sound.ENTITY_LEASH_KNOT_BREAK, 1f, 1f);
                            targetPlayer.playSound(targetPlayer, Sound.ENTITY_LEASH_KNOT_BREAK, 1f, 1f);
                            targetPlayer.removeMetadata("CannotMove", plugin);
                        }
                    }.runTaskLater(plugin, 80);
                }
            }
        }
    }

    @Override
    public void ability(){

    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.hasMetadata("CannotMove")) {
            event.setCancelled(true);
        }
    }
}
