package org.puggu.magicandskills.ability.skill;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.ability.events.ArrowVolleyEvent;
import org.puggu.magicandskills.common.SpawnArrow;

import java.util.ArrayList;
import java.util.List;

public class ArrowVolley extends Skill implements Listener {

    SpawnArrow spawnArrow = new SpawnArrow();

    public ArrowVolley(MagicAndSkills plugin) {
        super(plugin, 2000, 10);
    }

    private Player player;

    @EventHandler
    public void onArrowVolleyEvent(ArrowVolleyEvent event) {
        player = event.getPlayer();
        executeAbility(player);
    }

    @Override
    protected void ability() {
        // Spawn an arrow at the calculated location
        Arrow arrow = player.getWorld().spawnArrow(
                player.getEyeLocation(), player.getEyeLocation().getDirection(), 1.0f, 1.0f
        );
        arrow.setGravity(false);
        arrow.setShooter(player);
        arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);

        player.playSound(player, Sound.ENTITY_ARROW_SHOOT, 3f, 1f);

        List<Arrow> arrows = new ArrayList<>();

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!arrow.isValid()) {
                    cancel();
                }

//                 spawnArrow.spawnArrows(player, arrow.getLocation(), 40, 3f, 1f);

                Location location = arrow.getLocation();
                for (int i = 0; i < 40; i++){
                     Arrow a = player.getWorld().spawnArrow(
                                    location, arrow.getVelocity(), 2.0f, 20.0f);
                     a.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                     arrows.add(a);
                }
            }
        }.runTaskLater(plugin, 10L);

//        new BukkitRunnable() {
//            @Override
//            public void run() {
//                // Despawn spawned arrows
//                if (arrow.isValid()){
//                    arrow.remove();
//                }
//                for (Arrow a : arrows){
//                    // Spawn cloud in arrow location
//                    a.getWorld().spawnParticle(Particle.CLOUD,a.getLocation(), 30, .5, .5, .5, 0);
////                    a.getWorld().createExplosion(a.getLocation(), 5f, false, false, player);
//                    a.remove();
//                }
//            }
//        }.runTaskLater(plugin, 100L);
    }
}
