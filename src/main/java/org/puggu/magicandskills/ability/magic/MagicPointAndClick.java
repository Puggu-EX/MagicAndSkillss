package org.puggu.magicandskills.ability.magic;

import org.bukkit.entity.Player;
import org.puggu.magicandskills.MagicAndSkills;

public abstract class MagicPointAndClick extends MagicSpell{
    protected MagicPointAndClick(MagicAndSkills plugin, long cooldownTime, double manaCost) {
        super(plugin, cooldownTime, manaCost);
    }

    protected abstract boolean isTargetingEntity(Player player);
}
