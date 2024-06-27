package org.puggu.magicandskills.actionbar;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.ability.events.UpdateActionBarEvent;
import org.puggu.magicandskills.managers.PlayerClickManager;
import org.puggu.magicandskills.managers.PlayerEnergyManager;

import java.util.List;

public class DisplayActionBarSchedule implements Runnable, Listener {

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

    public void updateEnergyBar(Player player, Double mana, Double stamina) {
        String actionBarMessage = ChatColor.AQUA + "Mana: " + mana + "/100" +
                ChatColor.WHITE + " | " + ChatColor.WHITE + "# - # - #" + ChatColor.GREEN + " | " +
                ChatColor.YELLOW + "Stamina: " + stamina + "/100";
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(actionBarMessage));
    }

    public void updateEnergyBar(Player player, Double mana, Double stamina, String clicks) {
        // Fill the rest of the list with '#'s

        StringBuilder clicksBuilder = new StringBuilder(clicks);
        while (clicksBuilder.length() < 3) {
            clicksBuilder.append("#");
        }
        clicks = clicksBuilder.toString();
//        clicks = playerClickManager.getCastSequenceAsString(player);

        String middle = clicks.charAt(0) + " - " + clicks.charAt(1) + " - " + clicks.charAt(2);
        String actionBarMessage = ChatColor.AQUA + "Mana: " + mana + "/100" +
                ChatColor.GREEN + " | " + ChatColor.WHITE + middle + ChatColor.GREEN + " | " +
                ChatColor.YELLOW + "Stamina: " + stamina + "/100";
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(actionBarMessage));
    }

    @EventHandler
    public void handler(UpdateActionBarEvent event) {
//        String clicks = playerClickManager.getCastSequenceAsString(event.getPlayer());
//        List<PlayerClick> clicks = playerClickManager.playerClicks.get(event.getPlayer());

//        StringBuilder s = new StringBuilder();
//        for (PlayerClick pc : clicks) {
//            CastClick c = pc.getClick();
//            s.append("(").append(c.toString()).append(" ").append(pc.getTime()).append(")");
//        }
//        System.out.println("Handling UpdateActionBarEvent");
//        System.out.println("Clicks in Handler " + s);

        Player player = event.getPlayer();
        updateEnergyBar(player, playerEnergyManager.getPlayerMana(player), playerEnergyManager.getPlayerStamina(player), playerClickManager.getCastSequenceAsString(player));
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
//            double mana = playerEnergyManager.getPlayerMana(player);
//            double stamina = playerEnergyManager.getPlayerStamina(player);
//            String clicks = playerClickManager.getCastSequenceAsString(player);

//            updateEnergyBar(player, mana, stamina, clicks);
            Bukkit.getPluginManager().callEvent(new UpdateActionBarEvent(player));
//            playerClickManager.clearClicks(player);
        }
    }

    private ActionBarType actionBarType(Player player) {
        return ActionBarType.WIZARD;
    }

}

