package org.puggu.magicandskills.genericlisteners;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.common.Despawn;

import java.util.Set;

/**
 * Handle types of arrow actions on landing.
 * i.e. make "explosion-arrow"s explode and "fragmentation-arrows" blow up into fragments
 **/
public class ProjectileHitListener implements Listener {

    private MagicAndSkills plugin;

    public ProjectileHitListener(MagicAndSkills plugin) {
        this.plugin = plugin;
    }

    Despawn despawn = new Despawn(plugin);

    @EventHandler
    public void onProjectileHit(final ProjectileHitEvent event) {

//        Set<NamespacedKey> keys = event.getEntity().getPersistentDataContainer().getKeys();

//        for (NamespacedKey key : keys) {
//            if (!key.getKey().equals("summoned-arrow"))
//                return;
//        }

        if (event.getHitEntity() != null) {
            despawn.despawnEffectOnHit(event.getEntity());
        } else {
            despawn.despawnEffectOnHit(event.getEntity(), Particle.CLOUD);
        }
    }

    public void registerProjectileListener(){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void unregisterProjectileListener(){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
}
