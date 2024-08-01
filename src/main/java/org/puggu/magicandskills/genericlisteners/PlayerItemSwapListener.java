package org.puggu.magicandskills.genericlisteners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.puggu.magicandskills.actionbar.ClearActionBarEvent;
import org.puggu.magicandskills.actionbar.UpdateActionBarEvent;

import java.util.Objects;

public class PlayerItemSwapListener implements Listener {

    @EventHandler
    public void onPlayerItemSwap(PlayerItemHeldEvent e) {
        ItemStack item = e.getPlayer().getInventory().getItem(e.getNewSlot());

        if (item != null && item.getType() == Material.STICK && Objects.requireNonNull(item.getItemMeta()).hasItemFlag(ItemFlag.HIDE_ENCHANTS)) {
            Bukkit.getPluginManager().callEvent(new UpdateActionBarEvent(e.getPlayer()));
        } else {
            Bukkit.getPluginManager().callEvent(new ClearActionBarEvent(e.getPlayer()));
        }
    }
}
