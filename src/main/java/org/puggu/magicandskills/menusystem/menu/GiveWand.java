package org.puggu.magicandskills.menusystem.menu;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.puggu.magicandskills.menusystem.Menu;
import org.puggu.magicandskills.menusystem.PlayerMenuUtility;

public class GiveWand extends Menu {
    public GiveWand(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Give Wand";
    }

    @Override
    public int getSlots() {
        return 0;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

    }

    @Override
    public void setMenuItems() {

    }
}
