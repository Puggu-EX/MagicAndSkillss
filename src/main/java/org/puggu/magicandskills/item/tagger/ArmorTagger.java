package org.puggu.magicandskills.item.tagger;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.puggu.magicandskills.MagicAndSkills;

public class ArmorTagger {
    private final NamespacedKey masArmor;

    public ArmorTagger(MagicAndSkills plugin) {
        this.masArmor = new NamespacedKey(plugin, "summoned-arrow");
    }

    public void tagArmor(ItemStack item, String tag) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;
        meta.getPersistentDataContainer().set(masArmor, PersistentDataType.STRING, tag);
    }
}
