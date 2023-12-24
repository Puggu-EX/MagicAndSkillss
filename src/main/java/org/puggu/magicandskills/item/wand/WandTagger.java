package org.puggu.magicandskills.item.wand;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.puggu.magicandskills.MagicAndSkills;

import java.util.ArrayList;
import java.util.List;

/**
 * Taken from 7smile7 on spigotmc.org
 */
public class WandTagger {
    private final NamespacedKey wandTypeKey;

    public WandTagger(MagicAndSkills plugin) {
        this.wandTypeKey = new NamespacedKey(plugin, "wand-type");
    }

    public void setTypeOfWand(ItemStack item, WandType type){
        ItemMeta meta = item.getItemMeta();
        if (meta == null){
            return;
        }

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.RED + "Fire Wand");
        lore.add("This wand gives you the power");
        lore.add("to cast fire spells");
        meta.setLore(lore);

        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(this.wandTypeKey, PersistentDataType.STRING, type.toString());
        item.setItemMeta(meta);
    }

    public WandType getTypeOfWand(ItemStack item){
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta == null){
            return null;
        }

        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        String typeValue = container.get(this.wandTypeKey, PersistentDataType.STRING);
        if (typeValue == null){
            return null;
        }
        return WandType.valueOf(typeValue);
    }
}
