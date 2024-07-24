package org.puggu.magicandskills.managers;

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

    public void initPlayerEnergy(Player player) {
        PersistentDataContainer playerContainer = player.getPersistentDataContainer();
        playerContainer.set(manaKey, PersistentDataType.INTEGER, 100);
        playerContainer.set(staminaKey, PersistentDataType.INTEGER, 100);
    }

    public boolean hasEnergyContainers(Player player) {
        return hasManaContainer(player) && hasStaminaContainer(player);
    }

    public boolean hasManaContainer(Player player) {
       return player.getPersistentDataContainer().has(manaKey, PersistentDataType.INTEGER);
    }

    public boolean hasStaminaContainer(Player player) {
        return player.getPersistentDataContainer().has(staminaKey, PersistentDataType.INTEGER);
    }

    public int getPlayerMana(Player player) {
        if (!hasManaContainer(player))
            return -1;

        PersistentDataContainer playerContainer = player.getPersistentDataContainer();

        if (playerContainer.has(manaKey, PersistentDataType.INTEGER)) {
            return playerContainer.get(manaKey, PersistentDataType.INTEGER);
        }
        return -1;
    }

    public int getPlayerStamina(Player player) {
        if (!hasStaminaContainer(player))
            return -1;

        PersistentDataContainer playerContainer = player.getPersistentDataContainer();

        if (playerContainer.has(staminaKey, PersistentDataType.INTEGER)) {
            return playerContainer.get(staminaKey, PersistentDataType.INTEGER);
        }
        return -1;
    }

    public void incrementPlayerMana(Player player, int amount) {
        if (!hasEnergyContainers(player)) {
            System.out.println("Player has no mana container");
            return;
        }

        PersistentDataContainer playerContainer = player.getPersistentDataContainer();
        if (playerContainer.has(manaKey, PersistentDataType.INTEGER)) {
            int mana = playerContainer.get(manaKey, PersistentDataType.INTEGER);
            mana += amount;
            playerContainer.set(manaKey, PersistentDataType.INTEGER, mana);
        }
    }

    public void incrementPlayerStamina(Player player, int amount) {
        if (!hasEnergyContainers(player)) {
            System.out.println("Player has no stamina container");
            initPlayerEnergy(player);
        }

        PersistentDataContainer playerContainer = player.getPersistentDataContainer();
        if (playerContainer.has(staminaKey, PersistentDataType.INTEGER)) {
            int stamina = playerContainer.get(staminaKey, PersistentDataType.INTEGER);
            stamina += amount;
            playerContainer.set(staminaKey, PersistentDataType.INTEGER, stamina);
        }
    }


    public static int getTotalXP(int level, int points) {
        int totalXP = 0;

        if (level <= 16) {
            totalXP = (level * level + 6 * level) / 2;
        } else if (level <= 31) {
            totalXP = (5 * level * level - 81 * level + 720) / 2;
        } else {
            totalXP = (9 * level * level - 325 * level + 2220) / 2;
        }

        return totalXP + points;
    }
}
