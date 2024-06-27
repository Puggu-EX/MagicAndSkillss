package org.puggu.magicandskills.ability.magic;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.ability.events.IceShieldEvent;

public class IceShield extends MagicSpell implements Listener {

    public IceShield(MagicAndSkills plugin) {
        super(plugin, 1000, 10);
    }

    private Player player;

    @EventHandler
    private void onIceShieldEvent(IceShieldEvent event) {
        this.player = event.getPlayer();
        executeAbility(player);
    }

    @Override
    protected void ability() {
        Vector faceDirection  = player.getLocation().getDirection();
        double x = faceDirection.getX();
        double y = faceDirection.getY();
        double z = faceDirection.getZ();
        player.sendMessage("X: " +x);
        player.sendMessage("Y: "+y);
        player.sendMessage("Z: "+z);

        spawnSphere(player, player.getLocation(), 5, 1);
    }

    public void spawnSphere(Player p, Location center, double radius, double density) {

        for (double x = -radius; x < radius; x += density) {
            for (double z = -radius; z < radius; z += density) {
                double y = Math.sqrt(radius*radius - x*x - z*z);
//                p.spawnParticle(Particle.CLOUD, center.clone().add(-x, y, -z), 1, 0, 0, 0, 0);
//                p.spawnParticle(Particle.CLOUD, center.clone().subtract(-x, y -1, -z), 1, 0, 0, 0, 0);
                center.clone().add(-x, y, -z).getBlock().setType(Material.ICE);
//                center.clone().subtract(-x, y -1, -z).getBlock().setType(Material.ICE);
            }
        }
    }
    public void mySpawnSphere(Player p, Location center, double radius, double density) {
        // Level 1: r: 3 sample: 20 theta: 18
        // Level 2:
        // Level 3:

        int sampleSize = 20;
        // 5 because 3 for the gap + 2 for the top and current altitude
        for (double i = 0; i < 5; i++) {
            double x = radius * Math.cos(18);
            double z = radius * Math.sin(18);
        }
    }
}
