package org.puggu.magicandskills;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.puggu.magicandskills.commands.GiveWandCommand;
import org.puggu.magicandskills.actionbar.DisplayActionBarSchedule;
import org.puggu.magicandskills.commands.LearnAbility;
import org.puggu.magicandskills.commands.SuicideCommand;
import org.puggu.magicandskills.genericlisteners.PlayerItemSwapListener;
import org.puggu.magicandskills.genericlisteners.ProjectileHitListener;
import org.puggu.magicandskills.managers.EnergyRegenScheduler;
import org.puggu.magicandskills.genericlisteners.MenuListener;
import org.puggu.magicandskills.genericlisteners.OnPlayerJoinInit;
import org.puggu.magicandskills.managers.PlayerClickManager;
import org.puggu.magicandskills.managers.PlayerCooldownManager;
import org.puggu.magicandskills.menusystem.PlayerMenuUtility;

import java.util.HashMap;
import java.util.Objects;

public final class MagicAndSkills extends JavaPlugin {

    private int actionBarTask;
//    private int energyRegenTask;

    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    private static PlayerCooldownManager playerCooldownManager;

    @Override
    public void onEnable() {

        this.getServer().getPluginManager().registerEvents(new MenuListener(), this);
        this.getServer().getPluginManager().registerEvents(new ProjectileHitListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerItemSwapListener(), this);

        this.getServer().getPluginManager().registerEvents(new OnPlayerJoinInit(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerClickManager(this), this);
        this.getServer().getPluginManager().registerEvents(new DisplayActionBarSchedule(this), this);


        Objects.requireNonNull(this.getCommand("suicide")).setExecutor(new SuicideCommand());
        Objects.requireNonNull(this.getCommand("gw")).setExecutor(new GiveWandCommand(this));
        Objects.requireNonNull(this.getCommand("spell")).setExecutor(new LearnAbility(this));

        DisplayActionBarSchedule displayActionBarSchedule = new DisplayActionBarSchedule(this);
        actionBarTask = this.getServer().getScheduler().scheduleSyncRepeatingTask(this, displayActionBarSchedule, 0L, 20L);

//        EnergyRegenScheduler energyRegenScheduler = new EnergyRegenScheduler(this);
//        energyRegenTask = this.getServer().getScheduler().scheduleSyncRepeatingTask(this, energyRegenScheduler, 0L, 20L);

        playerCooldownManager = new PlayerCooldownManager(this);
    }

    public static PlayerMenuUtility getPlayerMenuUtility(Player p) {
        PlayerMenuUtility playerMenuUtility;
        if (!(playerMenuUtilityMap.containsKey(p))) { //See if the player has a playermenuutility "saved" for them

            //This player doesn't. Make one for them add it to the hashmap
            playerMenuUtility = new PlayerMenuUtility(p);
            playerMenuUtilityMap.put(p, playerMenuUtility);

            return playerMenuUtility;
        } else {
            return playerMenuUtilityMap.get(p); //Return the object by using the provided player
        }
    }

    public static PlayerCooldownManager getPlayerCooldownManager() {
        return playerCooldownManager;
    }

    @Override
    public void onDisable() {
        this.getServer().getScheduler().cancelTask(actionBarTask);
//        this.getServer().getScheduler().cancelTask(energyRegenTask);
    }
}