package org.puggu.magicandskills.util;

import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;
import org.puggu.magicandskills.MagicAndSkills;

import java.util.List;

public class DespawnLater {
    private MagicAndSkills plugin;

    public DespawnLater(MagicAndSkills plugin) {
        this.plugin = plugin;
    }

    public void despawnLater(Entity entity, long tickDelay) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (entity.isValid()) {
                    entity.getWorld().spawnParticle(Particle.CLOUD, entity.getLocation(), 29, .5, .5, .5, 0);
                    entity.remove();
                }
            }
        }.runTaskLater(plugin, tickDelay);
    }

    public void despawnLater(List<Entity> entities, long tickDelay) {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Entity e : entities) {
                    if (!e.isValid()) {
                        continue;
                    }
                    e.getWorld().spawnParticle(Particle.CLOUD, e.getLocation(), 10, .1, .1, .1, 0);
                    e.remove();
                }
            }
        }.runTaskLater(plugin, tickDelay);
    }

    public void despawnLater(List<Entity> entities, long tickDelay, boolean despawnParticles) {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Entity e : entities) {
                    if (!e.isValid()) {
                        continue;
                    }
                    if (despawnParticles) {
                        e.getWorld().spawnParticle(Particle.CLOUD, e.getLocation(), 30, .5, .5, .5, 0);
                        e.remove();
                    }
                }
            }
        }.runTaskLater(plugin, tickDelay);
    }
}
