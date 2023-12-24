package org.puggu.magicandskills.energy;

import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.puggu.magicandskills.MagicAndSkills;

import java.util.ArrayList;
import java.util.List;

public class PlayerClickManager {
    private final MagicAndSkills plugin;

    private final String metaDataKeyClick = "clicks";

    public PlayerClickManager(MagicAndSkills plugin) {
        this.plugin = plugin;
    }

    public void resetClicks(Player player){
        List<Character> clicks = new ArrayList<>();
        clicks.add('#');
        clicks.add('#');
        clicks.add('#');
        player.setMetadata(this.metaDataKeyClick, new FixedMetadataValue(plugin, clicks));
    }

    public void setPlayerClicks(Player player, List<Character> clicks){
        while (clicks.size() < 3){
            clicks.add('#');
        }
        player.setMetadata(this.metaDataKeyClick, new FixedMetadataValue(plugin, clicks));
    }

    public List<Character> getPlayerClicks(Player player){
        if (player.hasMetadata(metaDataKeyClick)){
            MetadataValue metadataValue = player.getMetadata(metaDataKeyClick).get(0);
            return (List<Character>) metadataValue.value();
        }
        return null;
    }
}
