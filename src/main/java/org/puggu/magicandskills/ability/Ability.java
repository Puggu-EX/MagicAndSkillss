package org.puggu.magicandskills.ability;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.ability.events.UpdateActionBarEvent;
import org.puggu.magicandskills.actionbar.DisplayActionBarSchedule;
import org.puggu.magicandskills.energy.PlayerEnergyManager;

public abstract class Ability {
    protected final long cooldownTime;
    protected double cost;
    protected final MagicAndSkills plugin;
    protected final PlayerEnergyManager playerEnergyManager;


    protected Ability(MagicAndSkills plugin, long cooldownTime, double cost) {
        this.plugin = plugin;
        this.playerEnergyManager = new PlayerEnergyManager(plugin);
        this.cooldownTime = cooldownTime;
        this.cost = cost;
    }

    private long lastUseTime = 0;

    public void setOnCooldown() {
        lastUseTime = System.currentTimeMillis();
    }

    public boolean isOnCooldown() {
        return timeSinceLastUsed() < cooldownTime;
    }

    public long timeSinceLastUsed() {
        return System.currentTimeMillis() - lastUseTime;
    }

    /**
     * The ability's main process
//     * @return whether the ability should be cast or not
     */
    protected abstract void ability();

    /**
     * Deplete player resource (Mana/Energy/Stamina)
     */
    public abstract void depleteResource(Player player, Double amount);

    /**
     * Get user Mana/Energy/Ki/Stamina
     * @return whether there's enough energy
     */
    public abstract boolean enoughResource(Player player, Double cost);

    /**
     * Implemented by MagicSpell/Skill's uniquely to handle cast fails for different reasons
     * @param player player
     * @param reason reason
     */
    public abstract void failedToCast(Player player, ReasonForCastFail reason);

    /**
     * Checks if player has resources and if ability is on cooldown
     * Depletes resource, calls ability, sets ability on cooldown
     * requests update for action bar
     * @param player entity
     */
    public void executeAbility(Player player) {
        if (!enoughResource(player, cost)) {
            failedToCast(player, ReasonForCastFail.NOT_ENOUGH_ENERGY);
            return;
        }
        if (isOnCooldown()) {
            failedToCast(player, ReasonForCastFail.COOLDOWN);
            return;
        }

        depleteResource(player, cost);
        ability();
        setOnCooldown();

        Bukkit.getServer().getPluginManager().callEvent(new UpdateActionBarEvent(player));
//        DisplayActionBarSchedule.updateEnergyBar(player,
//                playerEnergyManager.getPlayerMana(player),
//                playerEnergyManager.getPlayerStamina(player));
    }
}
