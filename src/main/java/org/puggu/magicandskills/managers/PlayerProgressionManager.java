package org.puggu.magicandskills.managers;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;

public class PlayerProgressionManager implements Listener {
    // A magic sequence is 5 clicks. 2 options for each click. 32 different combinations.
    private final HashMap<String, String> spellSequences = new HashMap<>(32);

    public PlayerProgressionManager() {
        // In the future the spells and their sequences will be stored in a document and initialized
        // in the onEnable() function.
        spellSequences.put("LLLLL", "MagicFireball");
        spellSequences.put("LLLLR", "ArrowGatling");
        spellSequences.put("RRRRR", "Substitution");
//        spellSequences.put("LLLRR", "Bind");
    }

    public void playerLearnAbility(Player player, NamespacedKey abilityKey){
        if (!player.getPersistentDataContainer().has(abilityKey, PersistentDataType.INTEGER)){
            player.getPersistentDataContainer().set(abilityKey, PersistentDataType.INTEGER, 1);
            player.sendMessage("You learned " + abilityKey.getKey());
        }
    }

    public boolean playerLearnedAbility(Player player, NamespacedKey abilityKey){
        return player.getPersistentDataContainer().has(abilityKey, PersistentDataType.INTEGER);
    }

    public boolean playerLearnedAbility(Player player, String abilityKeyAsString){
        for (NamespacedKey key: player.getPersistentDataContainer().getKeys()){
            if (key.getKey().equals(abilityKeyAsString.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    public void playerForgetAbility(Player player, NamespacedKey abilityKey){
        if (playerLearnedAbility(player, abilityKey)){
            player.getPersistentDataContainer().remove(abilityKey);
            player.sendMessage("You have forgotten " + abilityKey.toString());
        }
        System.out.println("Player never knew " + abilityKey.toString());
    }

    public void playerIncrementAbilityLevel(Player player, NamespacedKey abilityKey){

    }

    public String getSpell(Player player, String sequence){
        System.out.println(player.getName() + ": " + sequence);
        if (!spellSequences.containsKey(sequence)){
            return "FAIL";
        }

        if (playerLearnedAbility(player, spellSequences.get(sequence))){
            return spellSequences.get(sequence);
        }

        return "FAIL";
    }
}
