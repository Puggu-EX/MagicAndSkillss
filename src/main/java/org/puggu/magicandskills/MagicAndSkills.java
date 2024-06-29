package org.puggu.magicandskills;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.puggu.magicandskills.ability.magic.*;
import org.puggu.magicandskills.ability.skill.ArrowGatling;
import org.puggu.magicandskills.ability.skill.ArrowVolley;
import org.puggu.magicandskills.commands.GiveWandCommand;
import org.puggu.magicandskills.actionbar.DisplayActionBarSchedule;
import org.puggu.magicandskills.ability.events.EventController;
import org.puggu.magicandskills.ability.skill.Dash;
import org.puggu.magicandskills.ability.skill.Substitution;
import org.puggu.magicandskills.commands.SuicideCommand;
import org.puggu.magicandskills.genericlisteners.ProjectileHitListener;
import org.puggu.magicandskills.managers.EnergyRegenScheduler;
import org.puggu.magicandskills.genericlisteners.MenuListener;
import org.puggu.magicandskills.genericlisteners.OnPlayerJoinInitEnergyManagers;
import org.puggu.magicandskills.menusystem.PlayerMenuUtility;

import java.util.HashMap;
import java.util.Objects;

public final class MagicAndSkills extends JavaPlugin {

//    private static MagicAndSkills plugin;

    private int actionBarTask;
    private int energyRegenTask;

    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    @Override
    public void onEnable() {
//        plugin = this;

        this.getServer().getPluginManager().registerEvents(new MenuListener(), this);
        this.getServer().getPluginManager().registerEvents(new ProjectileHitListener(this), this);

        this.getServer().getPluginManager().registerEvents(new OnPlayerJoinInitEnergyManagers(this), this);
        this.getServer().getPluginManager().registerEvents(new EventController(this), this);
        this.getServer().getPluginManager().registerEvents(new DisplayActionBarSchedule(this), this);

        this.getServer().getPluginManager().registerEvents(new MagicFireball(this), this);
        this.getServer().getPluginManager().registerEvents(new Substitution(this), this);
        this.getServer().getPluginManager().registerEvents(new HellzoneGrenade(this), this);
        this.getServer().getPluginManager().registerEvents(new MagicLightingStrike(), this);
        this.getServer().getPluginManager().registerEvents(new BindSpell(this), this);
        this.getServer().getPluginManager().registerEvents(new Dash(this), this);
        this.getServer().getPluginManager().registerEvents(new ArrowGatling(this), this);
        this.getServer().getPluginManager().registerEvents(new ArrowVolley(this), this);
        this.getServer().getPluginManager().registerEvents(new ExtrasensoryPerception(this), this);
        this.getServer().getPluginManager().registerEvents(new ArrowStorm(this), this);
        this.getServer().getPluginManager().registerEvents(new IceShield(this), this);



        Objects.requireNonNull(this.getCommand("suicide")).setExecutor(new SuicideCommand());
        Objects.requireNonNull(this.getCommand("gw")).setExecutor(new GiveWandCommand(this));

        DisplayActionBarSchedule displayActionBarSchedule = new DisplayActionBarSchedule(this);
        actionBarTask = this.getServer().getScheduler().scheduleSyncRepeatingTask(this, displayActionBarSchedule, 0L, 20L);

        EnergyRegenScheduler energyRegenScheduler = new EnergyRegenScheduler(this);
        energyRegenTask = this.getServer().getScheduler().scheduleSyncRepeatingTask(this, energyRegenScheduler, 0L, 20L);

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

    @Override
    public void onDisable() {
        this.getServer().getScheduler().cancelTask(actionBarTask);
        this.getServer().getScheduler().cancelTask(energyRegenTask);
    }

//    public static MagicAndSkills getPlugin(){
//        return plugin;
//    }
}