package org.puggu.magicandskills.energy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.puggu.magicandskills.MagicAndSkills;

public class EnergyRegenScheduler implements Runnable{
    private final MagicAndSkills plugin;
    private final PlayerEnergyManager playerEnergyManager;

    public EnergyRegenScheduler(MagicAndSkills plugin) {
        this.plugin = plugin;
        this.playerEnergyManager = new PlayerEnergyManager(plugin);
    }

    @Override
    public void run() {
        for (Player player: Bukkit.getOnlinePlayers()){
            if (playerEnergyManager.getPlayerMana(player) < 100){
                playerEnergyManager.incrementPlayerMana(player, 1d);
            }
            if (playerEnergyManager.getPlayerStamina(player) < 100){
                playerEnergyManager.incrementPlayerStamina(player, 1d);
            }
        }
    }
}
