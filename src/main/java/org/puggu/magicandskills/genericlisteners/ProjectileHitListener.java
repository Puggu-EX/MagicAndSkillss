package org.puggu.magicandskills.genericlisteners;

import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.puggu.magicandskills.common.Despawn;

import java.util.Set;

public class ProjectileHitListener implements Listener {

    Despawn despawn = new Despawn();

    @EventHandler
    public void onProjectileHit(final ProjectileHitEvent event) {

        Set<NamespacedKey> keys = event.getEntity().getPersistentDataContainer().getKeys();

        for (NamespacedKey key : keys) {
            if (!key.getKey().equals("summoned-arrow")) {
//                event.getEntity().remove();
                return;
            }
        }

        if (event.getHitEntity() != null) {
            despawn.despawnEffectOnHit(event.getEntity());
        } else {
            despawn.despawnEffectOnHit(event.getEntity(), Particle.CLOUD);
        }
    }
}
