package org.puggu.magicandskills.ability.magic;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.ability.events.EspEvent;

import java.util.List;

public class ExtrasensoryPerception extends MagicSpell implements Listener {

    private final MagicAndSkills plugin;
    private Player player;

    public ExtrasensoryPerception(MagicAndSkills plugin) {
        super(plugin, 10000, 20);
        this.plugin = plugin;
    }

    @EventHandler
    private void onExtrasensoryPerceptionEvent(EspEvent event){
        this.player = event.getPlayer();
        this.executeAbility(player);
    }

    @Override
    protected void ability() {
        List<Entity> list = player.getNearbyEntities(10, 10, 10);

        if (list.isEmpty()) {
           return;
        }

        NamespacedKey key = new NamespacedKey(plugin, "esp");
        for (Entity e : list){
            // In case server goes down between when the entity starts to glow
            // and when the glowing effect is supposed to be removed, set a key
            // that will tell to the plugin to remove the glowing effect when the
            // server is restarted
            e.getPersistentDataContainer().set(key, PersistentDataType.STRING, "esp_glowing");
            e.setGlowing(true);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Entity e : list){
                    e.setGlowing(false);
                    e.getPersistentDataContainer().remove(key);
                }
            }
        }.runTaskLater(plugin, 60);


    }
}
