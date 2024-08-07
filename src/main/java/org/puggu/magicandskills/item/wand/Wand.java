package org.puggu.magicandskills.item.wand;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.puggu.magicandskills.MagicAndSkills;

import java.util.ArrayList;
import java.util.List;

public abstract class Wand extends ItemStack {

    private final ItemStack wand = new ItemStack(Material.STICK);

    public Wand(MagicAndSkills plugin, WandType type, String wandName){
//        this.wandTypeKey = new NamespacedKey(plugin, "wand-type");
//        this.wandType = type;
//        this.wandName = wandName;
//        createWand();
    }
}

