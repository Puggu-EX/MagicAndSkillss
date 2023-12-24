package org.puggu.magicandskills;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.List;

public class Title {

    public static void sendActionBar(Player player, String text){
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(text));
    }

    public static void sendActionBar(List<Player> players, String text){
        for(Player player: players){
            sendActionBar(player, text);
        }
    }
}
