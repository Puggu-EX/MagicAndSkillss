package org.puggu.magicandskills.common;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.item.tagger.ArrowTagger;

import java.util.ArrayList;
import java.util.List;

public class SpawnArrow {

    ArrowTagger arrowTagger = null;
    public SpawnArrow(MagicAndSkills plugin) {
        arrowTagger = new ArrowTagger(plugin);
    }

    public Arrow spawnArrow(Player shooter, float power, float accuracy) {

        shooter.playSound(shooter.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1f, 1f);
        Arrow arrow = shooter.getWorld().spawnArrow(
                shooter.getEyeLocation(), shooter.getEyeLocation().getDirection(), power, accuracy
        );
        arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
        // Tag arrow with "summoned-arrow"
        arrowTagger.tagArrow(arrow);

        return arrow;
    }

    public Arrow spawnArrow(Location location, Vector vector, float power, float accuracy, Player shooter) {
        shooter.playSound(shooter.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1f, 1f);
        Arrow arrow = shooter.getWorld().spawnArrow(location, vector, power, accuracy);
        arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);

        // Tag arrow with "summoned-arrow"
        arrowTagger.tagArrow(arrow);
        return arrow;
    }
    public List<Arrow> spawnArrows(Player shooter, float amount, float power, float accuracy) {
        List<Arrow> arrows = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            shooter.playSound(shooter.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1f, 1f);
            Arrow arrow = shooter.getWorld().spawnArrow(shooter.getLocation(), shooter.getEyeLocation().getDirection(), power, accuracy);
            arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);

            arrowTagger.tagArrow(arrow);
            arrows.add(arrow);
        }
        return arrows;
    }

    public List<Arrow> spawnArrows(Player shooter, Location location, Vector vector, float amount, float power, float accuracy) {
        List<Arrow> arrows = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Arrow arrow = shooter.getWorld().spawnArrow(location, vector, power, accuracy);
            arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);

            arrowTagger.tagArrow(arrow);
            arrows.add(arrow);
        }
        return arrows;
    }

    public List<Arrow> spawnArrowsSpherical(Player shooter, Location location, float amount, float power, float accuracy) {
        List<Arrow> arrows = new ArrayList<>();
        //
        for (int i = 0; i < amount; i++) {
            Arrow arrow = shooter.getWorld().spawnArrow(location, location.getDirection(), power, accuracy);
            arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);

            arrowTagger.tagArrow(arrow);
            arrows.add(arrow);
        }
        return arrows;
    }

    public List<Arrow> spawnArrows(Player shooter, Location location, Vector vector, float amount, float power, int accuracy) {
        List<Arrow> arrows = new ArrayList<Arrow>();
        for (int i = 0; i < amount; i++) {
            shooter.playSound(shooter.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1f, 1f);
            Arrow arrow = shooter.getWorld().spawnArrow(location, vector, power, accuracy);
            arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);

            arrowTagger.tagArrow(arrow);
            arrows.add(arrow);
        }
        return arrows;
    }


}
