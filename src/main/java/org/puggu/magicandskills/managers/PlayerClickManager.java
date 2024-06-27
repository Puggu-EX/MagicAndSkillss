package org.puggu.magicandskills.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.ability.events.UpdateActionBarEvent;
import org.puggu.magicandskills.clicks.CastClick;
import org.puggu.magicandskills.clicks.PlayerClick;

import java.util.*;

public class PlayerClickManager {
    private final MagicAndSkills plugin;

    private static final HashMap<UUID, List<PlayerClick>> playerClicks = new HashMap<>();

    public PlayerClickManager(MagicAndSkills plugin) {
        this.plugin = plugin;
    }

    public void clearClicks(Player player) {
        playerClicks.remove(player.getUniqueId());
    }

    public void addClick(Player player, CastClick click) {
        List<PlayerClick> clicks = getPlayerClicks(player);

        if (clicks == null || clicks.size() >= 3 || System.currentTimeMillis() - clicks.getLast().getTime() > 1000) {
//            player.sendMessage("Invalid click sequence detected, creating new list");
            clicks = new ArrayList<>();
        }

//        System.out.println("Putting: " + click.toString());
        clicks.add(new PlayerClick(click, System.currentTimeMillis()));
        playerClicks.put(player.getUniqueId(), clicks);


//        StringBuilder s = new StringBuilder();
//        for (PlayerClick pc : clicks) {
//            CastClick c = pc.getClick();
//            s.append("(").append(c.toString()).append(" ").append(pc.getTime()).append(")");
//        }
//        System.out.println("Current Cast Sequence: " + s);

//        System.out.println("Sending UpdateActionBarEvent");
        Bukkit.getServer().getPluginManager().callEvent(new UpdateActionBarEvent(player));
        if (clicks.size() == 3) {
            // Call EventManager/CastManager to handle spell execution
//            player.sendMessage("Attempting to Cast: " + getCastSequenceAsString(player));
            PlayerCastManager castManager = new PlayerCastManager(plugin);
            castManager.castHandler(player, getCastSequenceAsString(player));
            clearClicks(player);
        }

    }

    public List<PlayerClick> getPlayerClicks(Player player) {
//        List<PlayerClick> clicks = playerClicks.get(player.getUniqueId());
//        if (clicks != null) {
//            StringBuilder message = new StringBuilder();
//            for (PlayerClick click : clicks) {
//                String s = (click.getClick() == CastClick.LEFT) ? "left" : "right";
//                message.append(s);
//            }
//            player.sendMessage("From PCM: " + message);
//        }
//        return clicks;
        return playerClicks.get(player.getUniqueId());
    }

    public List<CastClick> getCastSequence(Player player) {
        List<PlayerClick> clicks = getPlayerClicks(player);
        List<CastClick> castClicks = new ArrayList<>();
        for (PlayerClick click : clicks) {
            castClicks.add(click.getClick());
        }
        return castClicks;
    }

    public String getCastSequenceAsString(Player player) {
        List<PlayerClick> clicks = playerClicks.get(player.getUniqueId());

        // no clicks or last click was longer than a second ago
        if (clicks == null || System.currentTimeMillis() - clicks.getLast().getTime() > 1000) {
            return "###";
        }

        StringBuilder castClicks = new StringBuilder();
        for (PlayerClick click : clicks) {
            char clickAsChar = (click.getClick() == CastClick.LEFT) ? 'L' : 'R';
            castClicks.append(clickAsChar);
        }
        return castClicks.toString();
    }
}
