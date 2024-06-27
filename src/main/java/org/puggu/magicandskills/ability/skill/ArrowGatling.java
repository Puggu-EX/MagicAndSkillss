package org.puggu.magicandskills.ability.skill;

import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.ability.events.ArrowGatlingEvent;
import org.puggu.magicandskills.common.SpawnArrow;
import org.puggu.magicandskills.item.ArrowTagger;
import org.puggu.magicandskills.util.DespawnLater;

import java.util.ArrayList;
import java.util.List;

public class ArrowGatling extends Skill implements Listener {

    SpawnArrow spawnArrow = new SpawnArrow();

    public ArrowGatling(MagicAndSkills plugin) {
        super(plugin, 2000, 10);
    }

    private Player player;

    @EventHandler
    public void onArrowGatlingEvent(ArrowGatlingEvent event) {
        player = event.getPlayer();
        executeAbility(player);
    }

    @Override
    protected void ability() {
        int numberOfArrows = 10;

//        List<Entity> arrows = new ArrayList<>();
//        DespawnLater despawnLater = new DespawnLater();
        ArrowTagger arrowTagger = new ArrowTagger(MagicAndSkills.getPlugin());

        new BukkitRunnable() {
            int arrowsSpawned = 0;

            @Override
            public void run() {
                if (arrowsSpawned < numberOfArrows) {
                    List<Arrow> arrows = new ArrayList<>();

                    Arrow arrow1 = spawnArrow.spawnArrow(player, 2, 7);
                    Arrow arrow2 = spawnArrow.spawnArrow(player, 2, 7);

                    arrows.add(arrow1);
                    arrows.add(arrow2);

                    arrowTagger.tagArrows(arrows);

//                    despawnLater.despawnLater(arrows, 80L);

                    arrowsSpawned++;
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 2L);

//        DespawnLater despawnLater = new DespawnLater();
//        despawnLater.despawnLater(arrows, 80L);
    }
}
