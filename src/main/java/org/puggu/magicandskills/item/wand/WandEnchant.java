package org.puggu.magicandskills.item.wand;

public enum WandEnchant {
    MAGIC_EFFICIENCY("Magic Efficiency");

    final String name;

    WandEnchant(String name){
        this.name = name;
    }

    public String asString() {
        return name;
    }
}

