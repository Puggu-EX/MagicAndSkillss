package org.puggu.magicandskills.ability.magic;

import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.puggu.magicandskills.MagicAndSkills;


public class MagicFireball extends Spell implements Listener {

    public MagicFireball(MagicAndSkills plugin, Player player) {
        super(plugin, player, 2000, new NamespacedKey(plugin, "MagicFireball"));
    }


    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event){
        if (event.getEntity().getMetadata("MagicFireball").isEmpty()){
            return;
        }
        Object value = event.getEntity().getMetadata("MagicFireball").get(0).value();

        if ((boolean) value){
            event.getEntity().getWorld().spawnParticle(Particle.FLAME,event.getEntity().getLocation(), 50, 1, 1, 1, .2);
        }
    }

    @Override
    public void ability() {
        Fireball fireball = player.getWorld().spawn(player.getEyeLocation(), Fireball.class);
        fireball.setShooter(player);
        fireball.setVelocity(player.getEyeLocation().getDirection().multiply(1.5f)); // Adjust the speed of the fireball as desired
        fireball.setYield(5.0f);
        fireball.setIsIncendiary(false);
        fireball.setMetadata("MagicFireball", new FixedMetadataValue(plugin, true));

        player.playSound(player, Sound.ITEM_FIRECHARGE_USE, 1, 1);

        new BukkitRunnable(){
            @Override
            public void run() {
                if (!fireball.isDead()) {
                    fireball.getWorld().spawnParticle(Particle.CLOUD,fireball.getLocation(), 50, .5, .5, .5, 0);
                    fireball.remove();
                }
            }
        }.runTaskLater(plugin, 30);
    }
}
//
//            Block block = player.getTargetBlock(null, 10);
//            Location location = block.getLocation();
//            double x = block.getX();
//            double y = block.getY();
//            double z = block.getZ();
//
//            Location loc = new Location(block.getWorld(), x, y, z).setDirection(new Vector(0, 0, 0));
//
//            ArmorStand armorStand = location.getWorld().spawn(loc, ArmorStand.class, armorStand1 -> {
//                armorStand1.setVisible(false);
//                armorStand1.setGravity(false);
//            });
//            armorStand.setRotation(player.getLocation().getYaw(), player.getLocation().getPitch());
////            loc.getWorld().spawnParticle(Particle.FLAME, loc.add(0, 2, 0), 50, 0.5, .5, 0.5, 0);
//            loc.getWorld().spawnParticle(Particle.SMOKE_LARGE, loc.add(0, 1.80, 0), 50, 0.5, .5, 0.5, 0);
//
//            ItemStack fireballItem = new ItemStack(Material.FIRE_CHARGE);
//            Objects.requireNonNull(armorStand.getEquipment()).setHelmet(fireballItem);
//
//            new BukkitRunnable() {
//                @Override
//                public void run() {
//                    loc.getWorld().spawnParticle(Particle.CLOUD, loc, 50, 0.5, .5, 0.5, 0);
//                    armorStand.remove();
//                }
//            }.runTaskLater(plugin, 60);
