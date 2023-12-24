package org.puggu.magicandskills.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.item.wand.FireWand;


public class GiveWand implements CommandExecutor {

    private final MagicAndSkills plugin;

    public GiveWand(MagicAndSkills plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("gw")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                Inventory inventory = player.getInventory();
//                ItemStack wand = new ItemStack(Material.STICK);
//                ItemMeta itemMeta = wand.getItemMeta();
//                itemMeta.setDisplayName("MAGIC WAND");
//                itemMeta.getPersistentDataContainer();
//                wand.setItemMeta(itemMeta);
                ItemStack wand = new ItemStack(Material.STICK);

//                inventory.addItem(new FireWand(plugin).getWand());
            }
        }
        return true;
    }
}
