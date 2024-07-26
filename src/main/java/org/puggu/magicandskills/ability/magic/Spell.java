package org.puggu.magicandskills.ability.magic;

import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.ability.Ability;
import org.puggu.magicandskills.ability.ReasonForCastFail;

public abstract class Spell extends Ability {
    private final int cost;

    protected Spell(MagicAndSkills plugin, Player player, long cooldownTime, int cost, NamespacedKey abilityKey) {
        super(plugin, player, cooldownTime, cost, abilityKey);
        this.cost = cost;
    }

    public int getManaCost() {
        return cost;
    }

    @Override
    public void failedToCast(Player player, ReasonForCastFail reason) {
        switch (reason) {
            case COOLDOWN:
                player.playSound(player, Sound.BLOCK_ANVIL_LAND, .5f, 1f);
                break;
            case NOT_ENOUGH_ENERGY:
                player.playSound(player, Sound.ITEM_SHIELD_BREAK, .5f, 1f);
                break;
        }
    }

//    @Override
//    public void depleteResource(Player player, int amount) {
//        if (playerEnergyManager.hasEnergyContainers(player)){
//            playerEnergyManager.incrementPlayerMana(player, -amount);
//        }
//    }
//
//    @Override
//    public void incrementResource(Player player, int amount) {
//        if (playerEnergyManager.hasEnergyContainers(player)){
//            playerEnergyManager.incrementPlayerMana(player, -amount);
//        }
//    }
//    @Override
//    public boolean enoughResource(Player player, int cost) {
//        return playerEnergyManager.getPlayerMana(player) > cost;
//    }
}
