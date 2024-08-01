package org.puggu.magicandskills.actionbar;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.managers.PlayerClickManager;
import org.puggu.magicandskills.managers.PlayerEnergyManager;

import java.util.List;
import java.util.Objects;

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

    public void updateEnergyBar(Player player, int mana, int stamina) {
        String actionBarMessage = ChatColor.AQUA + "Mana: " + mana + "/100" +
                ChatColor.WHITE + " | " + ChatColor.WHITE + "# - # - #" + ChatColor.GREEN + " | " +
                ChatColor.YELLOW + "Stamina: " + stamina + "/100";
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(actionBarMessage));
    }

    public void updateEnergyBar(Player player, String clicks) {
        // Fill the rest of the list with '#'s
        StringBuilder clicksBuilder = new StringBuilder(clicks);
        while (clicksBuilder.length() < 5) {
            clicksBuilder.append("#");
        }
        clicks = clicksBuilder.toString();

        clicksBuilder = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            clicksBuilder.append(clicks.charAt(i));
            if (i < 4) {
                clicksBuilder.append(" - ");
            }
        }

        clicks = clicksBuilder.toString();

        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(clicks));
    }

    @EventHandler
    public void handler(UpdateActionBarEvent event) {
        Player player = event.getPlayer();
        updateEnergyBar(player,
//                playerEnergyManager.getPlayerMana(player),
//                playerEnergyManager.getPlayerStamina(player),
                playerClickManager.getCastSequenceAsString(player));
    }

    @EventHandler
    public void handler(ClearActionBarEvent event) {
        event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(""));
    }

    @Override
    public void run() {
        // Change this so that instead of:
        // cycling through a list of all players
        // We instead:
        // update the action bar for every player in a given list. Players are added and removed from
        // this list when they switch to a wand.

        // "Cycling through every player and checking if they're holding a wand"
        // vs
        // "Every time a player switches items, if it's a wand, add them to the list"


        for (Player player : Bukkit.getOnlinePlayers()) {
//            double mana = playerEnergyManager.getPlayerMana(player);
//            double stamina = playerEnergyManager.getPlayerStamina(player);
//            String clicks = playerClickManager.getCastSequenceAsString(player);
//            updateEnergyBar(player, mana, stamina, clicks);

            ItemStack item = player.getInventory().getItemInMainHand();

            if (item.getType() == Material.STICK && Objects.requireNonNull(item.getItemMeta()).hasItemFlag(ItemFlag.HIDE_ENCHANTS)) {
                Bukkit.getPluginManager().callEvent(new UpdateActionBarEvent(player));
            }
        }
    }
}

