package org.puggu.magicandskills.actionbar;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.energy.PlayerClickManager;
import org.puggu.magicandskills.energy.PlayerEnergyManager;

import java.util.List;

public class DisplayActionBarSchedule implements Runnable {

    private final MagicAndSkills plugin;
    private final PlayerEnergyManager playerEnergyManager;
    private final PlayerClickManager playerClickManager;

    public DisplayActionBarSchedule(MagicAndSkills plugin) {
        this.plugin = plugin;
        this.playerEnergyManager = new PlayerEnergyManager(plugin);
        this.playerClickManager = new PlayerClickManager(plugin);
    }

    public void updateActionBar(Player player, String text) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(text));
    }

    public static void sendActionBar(List<Player> players, String text) {
        for (Player player : players) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(text));
        }
    }

    public static void updateEnergyBar(Player player, Double mana, Double ki) {
        String middle = "# - # - #";
        String actionBarMessage = ChatColor.AQUA + "Mana: " + mana + "/100" +
                ChatColor.GREEN + " | " + ChatColor.WHITE + middle + ChatColor.GREEN + " | " +
                ChatColor.YELLOW + "Ki: " + ki + "/100";
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(actionBarMessage));
    }

    public static void updateEnergyBar(Player player, Double mana, Double ki, List<Character> clicks) {
        while (clicks.size() < 3){
            clicks.add('#');
        }
        String middle = clicks.get(0) + " - " + clicks.get(1) + " - " + clicks.get(2);
        String actionBarMessage = ChatColor.AQUA + "Mana: " + mana + "/100" +
                ChatColor.GREEN + " | " + ChatColor.WHITE + middle + ChatColor.GREEN + " | " +
                ChatColor.YELLOW + "Ki: " + ki + "/100";
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(actionBarMessage));
    }


    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            double mana = playerEnergyManager.getPlayerMana(player);
            double ki = playerEnergyManager.getPlayerKi(player);
            List<Character> clicks = playerClickManager.getPlayerClicks(player);

            updateEnergyBar(player, mana, ki, clicks);
        }
    }

    private ActionBarType actionBarType(Player player) {
        return ActionBarType.WIZARD;
    }

}

