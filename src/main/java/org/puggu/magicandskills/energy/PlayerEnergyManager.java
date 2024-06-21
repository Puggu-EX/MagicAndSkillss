package org.puggu.magicandskills.energy;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.puggu.magicandskills.MagicAndSkills;

public class PlayerEnergyManager {
    private final NamespacedKey manaKey;
    private final NamespacedKey staminaKey;

    public PlayerEnergyManager(MagicAndSkills plugin) {
        this.manaKey = new NamespacedKey(plugin, "mana-key");
        this.staminaKey = new NamespacedKey(plugin, "stamina-key");
    }

    public void initPlayerEnergy(Player player){
        PersistentDataContainer playerContainer = player.getPersistentDataContainer();
        playerContainer.set(manaKey, PersistentDataType.DOUBLE, 20d);
        playerContainer.set(staminaKey, PersistentDataType.DOUBLE, 20d);
    }

    public boolean playerHasEnergyContainers(Player player){
        PersistentDataContainer playerContainer = player.getPersistentDataContainer();
        return playerContainer.has(staminaKey, PersistentDataType.DOUBLE) && playerContainer.has(manaKey, PersistentDataType.DOUBLE);
    }

    public double getPlayerMana(Player player){
        PersistentDataContainer playerContainer = player.getPersistentDataContainer();
        if (playerContainer.has(manaKey, PersistentDataType.DOUBLE)){
            return playerContainer.get(manaKey, PersistentDataType.DOUBLE);
        }
        return -1d;
    }

    public double getPlayerStamina(Player player){
        PersistentDataContainer playerContainer = player.getPersistentDataContainer();
        if (playerContainer.has(staminaKey, PersistentDataType.DOUBLE)){
            return playerContainer.get(staminaKey, PersistentDataType.DOUBLE);
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

    public void incrementPlayerStamina(Player player, Double amount){
        PersistentDataContainer playerContainer = player.getPersistentDataContainer();
        if (playerContainer.has(staminaKey, PersistentDataType.DOUBLE)){
            double stamina = playerContainer.get(staminaKey, PersistentDataType.DOUBLE);
            stamina += amount;
            playerContainer.set(staminaKey, PersistentDataType.DOUBLE, stamina);
        }
    }
}
