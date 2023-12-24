package org.puggu.magicandskills.energy;

import org.bukkit.NamespacedKey;
import org.puggu.magicandskills.MagicAndSkills;

public class PlayerCastManager {

    private final MagicAndSkills plugin;
    private final NamespacedKey playerCastManager;

    public PlayerCastManager(MagicAndSkills plugin, NamespacedKey playerCastManager) {
        this.plugin = plugin;
        this.playerCastManager = new NamespacedKey(plugin, "cast-manager");
    }
}
