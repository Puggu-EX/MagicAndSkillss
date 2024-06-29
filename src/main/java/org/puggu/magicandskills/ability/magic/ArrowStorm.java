package org.puggu.magicandskills.ability.magic;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.ability.events.ArrowStormEvent;
import org.puggu.magicandskills.common.SpawnArrow;

public class ArrowStorm extends MagicSpell implements Listener {
    public ArrowStorm(MagicAndSkills plugin) {
        super(plugin, 1000, 10);
    }

    private Player player;
    SpawnArrow spawnArrow = new SpawnArrow(plugin);

    @EventHandler
    private void onArrowStormEvent(ArrowStormEvent arrowStormEvent) {
        this.player = arrowStormEvent.getPlayer();
        executeAbility(player);
    }

    @Override
    protected void ability() {
        Location origin = player.getEyeLocation().clone().add(player.getEyeLocation().getDirection().multiply(2));
        RayTraceResult result = player.getWorld().rayTraceEntities(origin, player.getEyeLocation().getDirection(), 40.0);

        if (result == null || result.getHitEntity() == null) {
            return;
        }

        Entity target = result.getHitEntity();
        if (target instanceof Player) {
            player.playSound(target.getLocation(), Sound.ENTITY_TNT_PRIMED, 1f, -5f);
        }
        player.spawnParticle(Particle.ELECTRIC_SPARK, target.getLocation(), 20, .5f, 2f, .1f, 1);

        new BukkitRunnable() {
            int arrowsSpawned = 0;
            @Override
            public void run() {
                if (arrowsSpawned == 20) {
                    cancel();
                }
                Location loc = result.getHitEntity().getLocation().add(0, 20, 0).setDirection(new Vector(0, -1, 0));
                spawnArrow.spawnArrows(player, loc, loc.getDirection(), 3, 1, 20);
                arrowsSpawned++;
            }
        }.runTaskTimer(plugin, 30L, 2L);
    }
}
