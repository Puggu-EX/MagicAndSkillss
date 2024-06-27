package org.puggu.magicandskills.ability.magic;

import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class Structure {
    public void Structure(Location loc){
        this.anchor = loc;
    }

    Location anchor;
    List<Location> blocks = new ArrayList<>();

    public List<Location> getStructure() {
        blocks.add(anchor.clone().add(1, 1, 1));

        blocks.add(anchor.clone().add(1, 1, 0));
        blocks.add(anchor.clone().add(1, 0, 1));
        blocks.add(anchor.clone().add(0, 1, 1));

        blocks.add(anchor.clone().add(0, 1, 0));
        blocks.add(anchor.clone().add(0, 0, 1));
        blocks.add(anchor.clone().add(0, 1, 0));

        return blocks;
    }
    public void setStructure(List<Block> blocks) {}
}
