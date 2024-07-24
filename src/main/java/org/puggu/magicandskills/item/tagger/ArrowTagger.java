package org.puggu.magicandskills.item.tagger;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.persistence.PersistentDataType;
import org.puggu.magicandskills.MagicAndSkills;

import java.util.List;

public class ArrowTagger {
    private final NamespacedKey summonedArrow;

    public ArrowTagger(MagicAndSkills plugin) {
       this.summonedArrow = new NamespacedKey(plugin, "summoned-arrow");
    }

    public void tagArrow(Arrow arrow) {
       arrow.getPersistentDataContainer().set(this.summonedArrow, PersistentDataType.STRING, "summoned-arrow");
    }
    public void tagArrows(List<Arrow> arrows) {
        for (Arrow arrow : arrows) {
            arrow.getPersistentDataContainer().set(this.summonedArrow, PersistentDataType.STRING, "summoned-arrow");
        }
    }
}
