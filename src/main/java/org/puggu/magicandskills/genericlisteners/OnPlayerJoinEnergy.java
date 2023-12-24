package org.puggu.magicandskills.genericlisteners;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.energy.PlayerClickManager;
import org.puggu.magicandskills.energy.PlayerEnergyManager;

public class OnPlayerJoinEnergy implements Listener {

    private final PlayerEnergyManager playerEnergyManager;
    private final PlayerClickManager playerClickManager;

    public OnPlayerJoinEnergy(MagicAndSkills plugin) {
        this.playerEnergyManager = new PlayerEnergyManager(plugin);
        this.playerClickManager = new PlayerClickManager(plugin);
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event){
        System.out.println("Welcome " + event.getPlayer());
        Player player = event.getPlayer();
        if (!playerEnergyManager.playerHasEnergyContainers(player) ||
                playerEnergyManager.getPlayerMana(player) == -1d ||
                playerEnergyManager.getPlayerKi(player) == -1d){
            playerEnergyManager.initPlayerEnergy(player);
        }
        playerClickManager.resetClicks(player);
    }
}
