package org.puggu.magicandskills.ability.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;

public final class SubstitutionEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final EquipmentSlot hand;
    private final ItemStack item;
    private final Action action;


    public EquipmentSlot getHand() {
        return hand;
    }

    public ItemStack getItem() {
        return item;
    }


    public Material getMaterial() {
        return item.getType();
    }

    public Action getAction() {
        return action;
    }

    public SubstitutionEvent(Player player, EquipmentSlot hand, ItemStack item, Action action){
        this.player = player;
        this.hand = hand;
        this.item = item;
        this.action = action;
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
