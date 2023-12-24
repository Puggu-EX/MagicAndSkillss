package org.puggu.magicandskills.common;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.puggu.magicandskills.MagicAndSkills;


public class Despawn {

    public void despawnEffect(Entity entity, Particle particle, Location location, int delay, MagicAndSkills plugin){

        new BukkitRunnable() {
            @Override
            public void run() {
                location.getWorld().spawnParticle(particle, location, 50, 0.5, .5, 0.5, 0);
                entity.remove();
            }
        }.runTaskLater(plugin, delay);
    }
}
