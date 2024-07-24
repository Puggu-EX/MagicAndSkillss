package org.puggu.magicandskills.genericlisteners;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.managers.PlayerClickManager;
import org.puggu.magicandskills.managers.PlayerEnergyManager;
import org.puggu.magicandskills.managers.PlayerArmorStatManager;

public class OnPlayerJoinInit implements Listener {

    private final PlayerEnergyManager playerEnergyManager;
    private final PlayerClickManager playerClickManager;
    private final PlayerArmorStatManager playerArmorStatManager;

    public OnPlayerJoinInit(MagicAndSkills plugin) {
        this.playerEnergyManager = new PlayerEnergyManager(plugin);
        this.playerClickManager = new PlayerClickManager(plugin);
        this.playerArmorStatManager = new PlayerArmorStatManager(plugin);
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        // If player doesnt have energy or has an invalid amount of energy
        // initialize player energy.
        // TODO: consider what the player's max energy is
        if (!playerEnergyManager.hasEnergyContainers(player) ||
                playerEnergyManager.getPlayerMana(player) < 0 ||
                playerEnergyManager.getPlayerStamina(player) < 0){
            playerEnergyManager.initPlayerEnergy(player);
        }

        if (!playerArmorStatManager.hasAllArmorStats(player)){
            playerArmorStatManager.initPlayerStats(player);
        }

        playerClickManager.clearClicks(player);

    }
}
