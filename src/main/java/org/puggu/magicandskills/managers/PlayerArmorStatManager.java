package org.puggu.magicandskills.managers;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.item.tags.ArmorTags;

import java.util.ArrayList;
import java.util.List;

/**
 * Stats: Magic Damage, Attack Damage, Resource Capacity, etc.
 * int: ######
 */
public class PlayerArmorStatManager {
//    private  NamespacedKey manaCapacity;
//    private  NamespacedKey staminaCapacity;
//    private  NamespacedKey resourceCapacity;
//    private  NamespacedKey cooldownReduction;
//    private  NamespacedKey magicDamage;
//    private  NamespacedKey attackDamage;
//    private  NamespacedKey manaEfficiency;
//    private  NamespacedKey staminaEfficiency;

    List<NamespacedKey> armorStats = new ArrayList<>();

    public PlayerArmorStatManager(MagicAndSkills plugin){
//        armorStats.add(manaCapacity = new NamespacedKey(plugin, ArmorTags.Mana_Capacity.getName()));
//        armorStats.add(staminaCapacity = new NamespacedKey(plugin, ArmorTags.Stamina_Capacity.getName()));
//        armorStats.add(resourceCapacity = new NamespacedKey(plugin, ArmorTags.Resource_Capacity.getName()));
//        armorStats.add(cooldownReduction = new NamespacedKey(plugin, ArmorTags.Cooldown_Reduction.getName()));
//        armorStats.add(magicDamage = new NamespacedKey(plugin, ArmorTags.Magic_Damage.getName()));
//        armorStats.add(attackDamage = new NamespacedKey(plugin, ArmorTags.Attack_Damage.getName()));
//        armorStats.add(manaEfficiency = new NamespacedKey(plugin, ArmorTags.Mana_Efficiency.getName()));
//        armorStats.add(staminaEfficiency = new NamespacedKey(plugin, ArmorTags.Stamina_Efficiency.getName()));
    }

    public boolean hasAllArmorStats(Player player){
        for (NamespacedKey key : armorStats){
            if (player.getPersistentDataContainer().get(key, PersistentDataType.INTEGER) == null){
                return false;
            }
        }
        return true;
    }

    public void initPlayerStats(Player player) {
        PersistentDataContainer container = player.getPersistentDataContainer();
        for (NamespacedKey key : armorStats){
            if (player.getPersistentDataContainer().get(key, PersistentDataType.INTEGER) == null){
                container.set(key, PersistentDataType.INTEGER, 0);
            }
        }
    }

    public void setManaCapacityLevel(Player player, int level){
        PersistentDataContainer container = player.getPersistentDataContainer();
//        container.set(manaCapacity, PersistentDataType.INTEGER, level);
    }

//    public boolean hasManaCapacityStat(Player player){
//        int s = player.getPersistentDataContainer().get(manaCapacity, PersistentDataType.INTEGER);
//        return s != null && !s.isEmpty();
//    }
//
//    public boolean hasStaminaCapacityStat(Player player){
//        int s = player.getPersistentDataContainer().get(staminaCapacity, PersistentDataType.INTEGER);
//        return s != null && !s.isEmpty();
//    }
//
//    public boolean hasResourceCapacityStat(Player player){
//        int s = player.getPersistentDataContainer().get(resourceCapacity, PersistentDataType.INTEGER);
//        return s != null && !s.isEmpty();
//    }
//
//    public boolean hasCooldownReductionStat(Player player){
//        int s = player.getPersistentDataContainer().get(cooldownReduction, PersistentDataType.INTEGER);
//        return s != null && !s.isEmpty();
//    }
//
//    public boolean hasMagicDamageStat(Player player){
//        int s = player.getPersistentDataContainer().get(magicDamage, PersistentDataType.INTEGER);
//        return s != null && !s.isEmpty();
//    }
//
//    public boolean hasAttackDamageStat(Player player){
//        int s = player.getPersistentDataContainer().get(attackDamage, PersistentDataType.INTEGER);
//        return s != null && !s.isEmpty();
//    }
//
//    public boolean hasManaEfficiencyStat(Player player){
//        int s = player.getPersistentDataContainer().get(manaEfficiency, PersistentDataType.INTEGER);
//        return s != null && !s.isEmpty();
//    }
//
//    public boolean hasStaminaEfficiencyStat(Player player){
//        int s = player.getPersistentDataContainer().get(staminaEfficiency, PersistentDataType.INTEGER);
//        return s;
//    }

}
