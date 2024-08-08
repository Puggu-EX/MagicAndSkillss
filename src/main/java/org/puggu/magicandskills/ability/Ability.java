package org.puggu.magicandskills.ability;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.ability.experience.ExperienceUtil;
import org.puggu.magicandskills.actionbar.UpdateActionBarEvent;

public abstract class Ability {
    protected final MagicAndSkills plugin;
    protected final NamespacedKey abilityKey;
    protected final Player player; // Caster of spell
    protected final int cost;   // Cost of spell
    protected int level;  // Progression level of spell (defaults to 1)

    protected Ability(MagicAndSkills plugin, Player player, int cost, NamespacedKey abilityKey) {
        this.plugin = plugin;
        this.cost = cost;
        this.player = player;
        this.abilityKey = abilityKey;
        this.level = 1;
    }

    protected Ability(MagicAndSkills plugin, Player player, int cost, NamespacedKey abilityKey, int level) {
        this.plugin = plugin;
        this.cost = cost;
        this.player = player;
        this.abilityKey = abilityKey;
        this.level = level;
    }

//    private long lastUseTime = 0;

    public void setOnCooldown() {
//        cooldowns.put(player.getUniqueId(), System.currentTimeMillis());
    }

    public long getCooldown() {
//        if (cooldowns.get(player.getUniqueId()) == null) {
//            return -1;
//        }

//        return cooldowns.get(player.getUniqueId());
        return -1L;
    }

    /**
     * The ability's main process
     */
    protected abstract void ability();

    /**
     * Deplete player resource (Mana/Stamina)
     */
    protected void depleteResource(Player player, int amount) {
        //TODO: Include armor stats (Spell Efficiency)
        player.giveExp(-amount);
    }

    /**
     * Increments player resource
     */
    protected void incrementResource(Player player, int amount) {
        player.giveExp(amount);
    }

    /**
     * Does the user have enough resources to cast ability
     *
     * @return whether there's enough energy
     */
    public boolean enoughResource(Player player, int cost) {
//        player.sendMessage(player.getLevel() + " | " + player.getExp());
        return ExperienceUtil.calculateTotalXP(player.getLevel(), player.getExp()) >= cost;
    }

    /**
     * Implemented by Spell/Skill's uniquely to handle cast fails for different reasons
     *
     * @param player player
     * @param reason reason
     */
    public abstract void failedToCast(Player player, ReasonForCastFail reason);

    /**
     * Checks if player has resources and if ability is on cooldown
     * Depletes resource, calls ability, sets ability on cooldown
     * requests update for action bar
     */
    public void executeAbility() {
        if (!enoughResource(player, cost)) {
            failedToCast(player, ReasonForCastFail.NOT_ENOUGH_ENERGY);
            return;
        }

        // Successful cast
        player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1f, 1f);
        depleteResource(player, cost);
        ability();
        // TODO: Set action bar sequence back to default after casting spell
        // Bukkit.getServer().getPluginManager().callEvent(new UpdateActionBarEvent(player));
    }

    public void setLevel(int level){
        this.level = level;
    }

    public NamespacedKey getAbilityKey() {
        return abilityKey;
    }
}
