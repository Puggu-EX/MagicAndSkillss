package org.puggu.magicandskills;

import org.bukkit.NamespacedKey;
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

import javax.naming.Name;
import java.util.HashMap;
import java.util.Objects;

public final class MagicAndSkills extends JavaPlugin {

    private int actionBarTask;

    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    public static  NamespacedKey magicFireballKey;
    public static NamespacedKey substitutionKey;
    public static NamespacedKey arrowGatling;

    @Override
    public void onEnable() {

        this.getServer().getPluginManager().registerEvents(new MenuListener(), this);
        this.getServer().getPluginManager().registerEvents(new ProjectileHitListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerItemSwapListener(), this);

        this.getServer().getPluginManager().registerEvents(new OnPlayerJoinInit(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerClickManager(this), this);
        this.getServer().getPluginManager().registerEvents(new DisplayActionBarSchedule(this), this);

        // Keys for all abilities
        magicFireballKey = new NamespacedKey(this, "MagicFireball");
        substitutionKey = new NamespacedKey(this, "Substitution");
        arrowGatling = new NamespacedKey(this, "ArrowGatling");


        Objects.requireNonNull(this.getCommand("suicide")).setExecutor(new SuicideCommand());
        Objects.requireNonNull(this.getCommand("gw")).setExecutor(new GiveWandCommand(this));
        Objects.requireNonNull(this.getCommand("spell")).setExecutor(new LearnAbility(this));

        DisplayActionBarSchedule displayActionBarSchedule = new DisplayActionBarSchedule(this);
        actionBarTask = this.getServer().getScheduler().scheduleSyncRepeatingTask(this, displayActionBarSchedule, 0L, 20L);
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
//        this.getServer().getScheduler().cancelTask(energyRegenTask);
    }
}