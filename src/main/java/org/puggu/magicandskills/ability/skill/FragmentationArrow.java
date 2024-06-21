package org.puggu.magicandskills.ability.skill;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.ability.events.FragmentationArrowEvent;

import java.util.ArrayList;
import java.util.List;

public class FragmentationArrow extends Skill implements Listener {

    public FragmentationArrow(MagicAndSkills plugin) {
        super(plugin, 2000, 10);
    }

    private Player player;

    @EventHandler
    public void onArrowStormEvent(FragmentationArrowEvent event) {
        System.out.println("Arrow Storm Event Heard");
        player = event.getPlayer();
        executeAbility(player);
    }

    @Override
    protected void ability() {
        // Spawn an arrow at the calculated location
        Arrow arrow = player.getWorld().spawnArrow(
                player.getEyeLocation(), player.getEyeLocation().getDirection(), 2.0f, 1.0f
        );
        arrow.setShooter(player);
        arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);

        List<Arrow> arrows = new ArrayList<>();

        new BukkitRunnable() {
            @Override
            public void run() {
                // Spawn 20 arrows random directions
                if (!arrow.isValid()){
                    cancel();
                }
                Location location = arrow.getLocation();
                for (int i = 0; i < 20; i++){
                     Arrow a = player.getWorld().spawnArrow(
                                    location, player.getEyeLocation().getDirection(), 2.0f, 20.0f);
                     arrows.add(a);
                     a.setShooter(player);
                     a.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                }
            }
        }.runTaskLater(plugin, 10L);

        new BukkitRunnable() {
            @Override
            public void run() {
                // Despawn spawned arrows
                for (Arrow a : arrows){
                    // Spawn cloud in arrow location
                    a.getWorld().spawnParticle(Particle.CLOUD,a.getLocation(), 30, .5, .5, .5, 0);
                    a.remove();
                }
            }
        }.runTaskLater(plugin, 100L);
    }
}
