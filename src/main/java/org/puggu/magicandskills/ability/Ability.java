package org.puggu.magicandskills.ability;

import org.bukkit.entity.Player;
import org.puggu.magicandskills.MagicAndSkills;
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

    public boolean isOnCooldown() {
        return timeRemaining() < cooldownTime;
    }

    public void setOnCooldown() {
        lastUseTime = System.currentTimeMillis();
    }

    public long timeRemaining() {
        return System.currentTimeMillis() - lastUseTime;
    }

    /**
     * The ability's main process
     * @return whether the ability should be cast or not
     */
    protected abstract boolean ability();

    /**
     * Deplete player resource (Mana/Ki/Energy/Stamina)
     */
    public abstract void depleteResource(Player player, Double amount);

    /**
     * Get user Mana/Energy/Ki/Stamina
     *
     * @return whether there's enough energy
     */
    public abstract boolean enoughResource(Player player, Double cost);

    public abstract void failedToCast(Player player, ReasonForCastFail reason);

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

        DisplayActionBarSchedule.updateEnergyBar(player,
                playerEnergyManager.getPlayerMana(player),
                playerEnergyManager.getPlayerKi(player));
    }
}
