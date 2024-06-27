package org.puggu.magicandskills.ability.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.checkerframework.checker.nullness.qual.NonNull;

public class EspEvent extends AbilityEvent {
    public EspEvent(Player player) {
        super(player);
    }
}
