package org.puggu.magicandskills.common;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;
import org.puggu.magicandskills.MagicAndSkills;


public class Despawn {

    private final MagicAndSkills plugin;

    public Despawn(MagicAndSkills plugin) {
        this.plugin = plugin;
    }

    public void despawnEffectOnHit(Entity entity, Particle particle, Location location, int delay) {

        new BukkitRunnable() {
            @Override
            public void run() {
                location.getWorld().spawnParticle(particle, location, 50, 0.5, .5, 0.5, 0);
                entity.remove();
            }
        }.runTaskLater(plugin, delay);
    }

    public void despawnEffectOnHit(Entity entity) {
//        entity.getWorld().spawnParticle(Particle.CLOUD, entity.getLocation(), 1, .1, .1, .1, 0);
        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(200, 0, 50), 1F);
        entity.getWorld().spawnParticle(Particle.REDSTONE, entity.getLocation(), 7, dustOptions);
        entity.remove();
    }

    public void despawnEffectOnHit(Entity entity, Particle particle) {
        entity.getWorld().spawnParticle(particle, entity.getLocation(), 5, .1, .1, .1, 0);
        entity.remove();
    }
}
