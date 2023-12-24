package org.puggu.magicandskills.ability.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;

public final class HellzoneEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final Player player;

    public HellzoneEvent(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return this.player;
    }

    @NonNull
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList(){
        return handlers;
    }
}
