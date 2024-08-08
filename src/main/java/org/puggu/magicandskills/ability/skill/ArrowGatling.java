package org.puggu.magicandskills.ability.skill;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.ability.magic.Spell;
import org.puggu.magicandskills.common.SpawnArrow;
import org.puggu.magicandskills.item.tagger.ArrowTagger;

import java.util.ArrayList;
import java.util.List;

public class ArrowGatling extends Spell {

    public ArrowGatling(MagicAndSkills plugin, Player player) {
        super(plugin, player, 2000, new NamespacedKey(plugin, "ArrowGatling"));
    }

    @Override
    protected void ability() {
        int numberOfArrows = 10;

        ArrowTagger arrowTagger = new ArrowTagger(plugin);

        new BukkitRunnable() {
            SpawnArrow spawnArrow = new SpawnArrow(plugin);
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
    }
}
