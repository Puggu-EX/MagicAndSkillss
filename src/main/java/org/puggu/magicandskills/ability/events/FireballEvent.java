package org.puggu.magicandskills.ability.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;

public final class FireballEvent extends AbilityEvent {
    public FireballEvent(Player player) {
        super(player);
    }
}
