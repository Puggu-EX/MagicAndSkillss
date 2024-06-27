package org.puggu.magicandskills.ability.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.checkerframework.checker.nullness.qual.NonNull;

public class AbilityEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;

    public AbilityEvent(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return this.player;
    }

    @NonNull
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
