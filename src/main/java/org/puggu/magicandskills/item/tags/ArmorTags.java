package org.puggu.magicandskills.item.tags;

/**
 * These tags are to be applied to a piece of armor and are used to enhance the stats of a player
 */
public enum ArmorTags {
    Mana_Capacity("Mana Capacity"), // Increases Mana capacity
    Stamina_Capacity("Stamina Capacity"), // Increases Stamina capacity
    Resource_Capacity("Resource Capacity"), // Increases Mana/Stamina capacity

    Cooldown_Reduction("Cooldown Reduction"), // Decreases Cooldown

    Magic_Damage("Magic Damage"), // Increases damage output of Mana
    Attack_Damage("Attack Damage"), // Increases damage output of Stamina

    Mana_Efficiency("Mana Efficiency"),
    Stamina_Efficiency("Stamina Efficiency"),
    ;

    private String name;

    ArmorTags(String name) {
    }
    public String getName() {
        return name;
    }
}
