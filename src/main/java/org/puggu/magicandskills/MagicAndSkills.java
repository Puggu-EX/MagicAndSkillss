package org.puggu.magicandskills;

import org.bukkit.plugin.java.JavaPlugin;
import org.puggu.magicandskills.ability.skill.ArrowStorm;
import org.puggu.magicandskills.ability.skill.FragmentationArrow;
import org.puggu.magicandskills.commands.GiveWand;
import org.puggu.magicandskills.actionbar.DisplayActionBarSchedule;
import org.puggu.magicandskills.ability.events.EventController;
import org.puggu.magicandskills.ability.magic.BindSpell;
import org.puggu.magicandskills.ability.magic.HellzoneGrenade;
import org.puggu.magicandskills.ability.magic.MagicFireball;
import org.puggu.magicandskills.ability.magic.MagicLightingStrike;
import org.puggu.magicandskills.ability.skill.Dash;
import org.puggu.magicandskills.ability.skill.Substitution;
import org.puggu.magicandskills.energy.EnergyRegenScheduler;
import org.puggu.magicandskills.genericlisteners.OnPlayerJoinInitEnergyManagers;

import java.util.Objects;

public final class MagicAndSkills extends JavaPlugin {

    private int actionBarTask;
    private int energyRegenTask;

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new MagicFireball(this), this);
        this.getServer().getPluginManager().registerEvents(new Substitution(this), this);
        this.getServer().getPluginManager().registerEvents(new HellzoneGrenade(this), this);
        this.getServer().getPluginManager().registerEvents(new MagicLightingStrike(), this);
        this.getServer().getPluginManager().registerEvents(new BindSpell(this), this);
        this.getServer().getPluginManager().registerEvents(new EventController(this), this);
        this.getServer().getPluginManager().registerEvents(new Dash(this), this);
        this.getServer().getPluginManager().registerEvents(new OnPlayerJoinInitEnergyManagers(this), this);
        this.getServer().getPluginManager().registerEvents(new ArrowStorm(this), this);
        this.getServer().getPluginManager().registerEvents(new DisplayActionBarSchedule(this), this);
        this.getServer().getPluginManager().registerEvents(new FragmentationArrow(this), this);

        Objects.requireNonNull(this.getCommand("gw")).setExecutor(new GiveWand(this));

        DisplayActionBarSchedule displayActionBarSchedule = new DisplayActionBarSchedule(this);
        actionBarTask = this.getServer().getScheduler().scheduleSyncRepeatingTask(this, displayActionBarSchedule, 0L, 20L);

        EnergyRegenScheduler energyRegenScheduler = new EnergyRegenScheduler(this);
        energyRegenTask = this.getServer().getScheduler().scheduleSyncRepeatingTask(this, energyRegenScheduler, 0L, 20L);

    }

    @Override
    public void onDisable() {
        this.getServer().getScheduler().cancelTask(actionBarTask);
        this.getServer().getScheduler().cancelTask(energyRegenTask);
    }
}