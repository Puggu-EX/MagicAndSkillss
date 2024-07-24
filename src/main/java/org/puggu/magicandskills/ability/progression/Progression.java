package org.puggu.magicandskills.ability.progression;

import org.bukkit.NamespacedKey;

public class Progression {
    NamespacedKey abilityKey;
    int level;
    String sequence;

    public Progression(NamespacedKey abilityKey, int level, String sequence) {
        this.abilityKey = abilityKey;
        this.level = level;
        this.sequence = sequence;
    }

    public NamespacedKey getAbilityKey() {
        return abilityKey;
    }

    public int getLevel() {
        return level;
    }

    public String getSequence() {
        return sequence;
    }
}
