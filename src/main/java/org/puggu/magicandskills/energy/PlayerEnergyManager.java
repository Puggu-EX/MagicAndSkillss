package org.puggu.magicandskills.energy;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.puggu.magicandskills.MagicAndSkills;

public class PlayerEnergyManager {
    private final NamespacedKey manaKey;
    private final NamespacedKey kiKey;

    public PlayerEnergyManager(MagicAndSkills plugin) {
        this.manaKey = new NamespacedKey(plugin, "mana-key");
        this.kiKey = new NamespacedKey(plugin, "ki-key");
    }

    public void initPlayerEnergy(Player player){
        PersistentDataContainer playerContainer = player.getPersistentDataContainer();
        playerContainer.set(manaKey, PersistentDataType.DOUBLE, 20d);
        playerContainer.set(kiKey, PersistentDataType.DOUBLE, 20d);
    }

    public boolean playerHasEnergyContainers(Player player){
        PersistentDataContainer playerContainer = player.getPersistentDataContainer();
        return playerContainer.has(kiKey, PersistentDataType.DOUBLE) && playerContainer.has(manaKey, PersistentDataType.DOUBLE);
    }

    public double getPlayerMana(Player player){
        PersistentDataContainer playerContainer = player.getPersistentDataContainer();
        if (playerContainer.has(manaKey, PersistentDataType.DOUBLE)){
            return playerContainer.get(manaKey, PersistentDataType.DOUBLE);
        }
        return -1d;
    }

    public double getPlayerKi(Player player){
        PersistentDataContainer playerContainer = player.getPersistentDataContainer();
        if (playerContainer.has(kiKey, PersistentDataType.DOUBLE)){
            return playerContainer.get(kiKey, PersistentDataType.DOUBLE);
        }
        return -1d;
    }

    public void incrementPlayerMana(Player player, Double amount){
        PersistentDataContainer playerContainer = player.getPersistentDataContainer();
        if (playerContainer.has(manaKey, PersistentDataType.DOUBLE)){
            double mana = playerContainer.get(manaKey, PersistentDataType.DOUBLE);
            mana += amount;
            playerContainer.set(manaKey, PersistentDataType.DOUBLE, mana);
        }
    }

    public void incrementPlayerKi(Player player, Double amount){
        PersistentDataContainer playerContainer = player.getPersistentDataContainer();
        if (playerContainer.has(kiKey, PersistentDataType.DOUBLE)){
            double ki = playerContainer.get(kiKey, PersistentDataType.DOUBLE);
            ki += amount;
            playerContainer.set(kiKey, PersistentDataType.DOUBLE, ki);
        }
    }
}
