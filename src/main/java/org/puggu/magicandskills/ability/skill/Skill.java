package org.puggu.magicandskills.ability.skill;

import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.ability.Ability;
import org.puggu.magicandskills.ability.ReasonForCastFail;

public abstract class Skill extends Ability {
    private final int staminaCost;
    protected Skill(MagicAndSkills plugin, Player player, long cooldownTime, int staminaCost, NamespacedKey abilityKey) {
        super(plugin, player, cooldownTime, staminaCost, abilityKey);
        this.staminaCost = staminaCost;
    }

//    @Override
//    public void depleteResource(Player player, int amount) {
//        if (playerEnergyManager.hasEnergyContainers(player)){
//            playerEnergyManager.incrementPlayerStamina(player, -amount);
//        }
//    }
//
//    @Override
//    protected void incrementResource(Player player, int amount) {
//        return;
//    }
//
//    @Override
//    public boolean enoughResource(Player player, int cost) {
//        double availableStamina = playerEnergyManager.getPlayerStamina(player);
//        return availableStamina > cost;
//    }

    @Override
    public void failedToCast(Player player, ReasonForCastFail reason) {
        switch (reason){
            case COOLDOWN:
                player.playSound(player, Sound.BLOCK_ANVIL_LAND, .5f, 1f);
                break;
            case NOT_ENOUGH_ENERGY:
                player.playSound(player, Sound.ITEM_SHIELD_BREAK, .5f, 1f);
                break;
        }
    }

    public int getStaminaCost() {
        return staminaCost;
    }
}
