package org.puggu.magicandskills.ability.skill;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.puggu.magicandskills.MagicAndSkills;

public class Substitution extends Skill implements Listener {

    public Substitution(MagicAndSkills plugin, Player player) {
        super(plugin, player, 4000, new NamespacedKey(plugin, "Substitution"));
    }

    @EventHandler
    private void onSubLogBreak(BlockBreakEvent event) {
        if (event.getBlock().hasMetadata("SubstitutionLog")) {
            event.setCancelled(true);
        }
    }

    @Override
    protected void ability() {
        player.sendMessage("Location saved, you'll be summoned back here in 3 seconds");

        Location location = player.getLocation();
        new BukkitRunnable() {
            @Override
            public void run() {
                Location substitutedLocationBottom = player.getLocation();
                Location substitutedLocationTop = player.getLocation().add(0, 1, 0);
                World world = player.getWorld();

                player.getWorld().spawnParticle(Particle.CLOUD, substitutedLocationBottom, 70, 1, 1, 1, 0);

                Block oldBottomBlock = player.getWorld().getBlockAt(substitutedLocationBottom);
                BlockData oldBottomBlockData = oldBottomBlock.getBlockData();

                Block topBottomBlock = player.getWorld().getBlockAt(substitutedLocationTop);
                BlockData topBottomBlockData = topBottomBlock.getBlockData();


                Block block = substitutedLocationBottom.getBlock();
                block.setType(Material.OAK_LOG);
                block.setMetadata("SubstitutionLog", new FixedMetadataValue(plugin, true));

                block = substitutedLocationTop.getBlock();
                block.setType(Material.OAK_LOG);
                block.setMetadata("SubstitutionLog", new FixedMetadataValue(plugin, true));

                world.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.5f, 0.2f);
                world.playSound(player, Sound.ENTITY_ENDERMAN_TELEPORT, 0.5f, 0.2f);
                player.teleport(location);

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        world.spawnParticle(Particle.CLOUD, substitutedLocationBottom, 70, 1, 1, 1, 0);
                        world.setBlockData(substitutedLocationBottom, oldBottomBlockData);
                        world.setBlockData(substitutedLocationTop, topBottomBlockData);
                    }
                }.runTaskLater(plugin, 40);
            }
        }.runTaskLater(plugin, 60);
    }
}
