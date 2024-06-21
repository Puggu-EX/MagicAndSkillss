package org.puggu.magicandskills.genericlisteners;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.energy.PlayerClickManager;
import org.puggu.magicandskills.energy.PlayerEnergyManager;

public class OnPlayerJoinInitEnergyManagers implements Listener {

    private final PlayerEnergyManager playerEnergyManager;
    private final PlayerClickManager playerClickManager;

    public OnPlayerJoinInitEnergyManagers(MagicAndSkills plugin) {
        this.playerEnergyManager = new PlayerEnergyManager(plugin);
        this.playerClickManager = new PlayerClickManager(plugin);
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event){
        System.out.println("Welcome " + event.getPlayer());
        Player player = event.getPlayer();
        // If player doesnt have energy or has an invalid amount of energy
        // initialize player energy.
        // TODO: consider what the player's max energy is
        if (!playerEnergyManager.playerHasEnergyContainers(player) ||
                playerEnergyManager.getPlayerMana(player) == -1d ||
                playerEnergyManager.getPlayerStamina(player) == -1d){
            playerEnergyManager.initPlayerEnergy(player);
        }
        playerClickManager.clearClicks(player);
    }
}
