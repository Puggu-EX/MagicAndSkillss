package org.puggu.magicandskills.actionbar;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.checkerframework.checker.nullness.qual.NonNull;

public class UpdateActionBarEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;

    public UpdateActionBarEvent(Player player){
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
