package org.puggu.magicandskills.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.item.tagger.WandTagger;
import org.puggu.magicandskills.item.wand.WandType;


public class GiveWandCommand implements CommandExecutor {

    private final MagicAndSkills plugin;
    private final WandTagger tagger;

    public GiveWandCommand(MagicAndSkills plugin) {
        this.plugin = plugin;
        tagger = new WandTagger(plugin);
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
                tagger.setTypeOfWand(wand, WandType.FIRE);
//                inventory.addItem(new FireWand(plugin).getWand());
                inventory.addItem(wand);
            }
        }
        return true;
    }
}
