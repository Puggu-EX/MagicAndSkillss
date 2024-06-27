package org.puggu.magicandskills.ability.magic;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.ability.events.HellzoneEvent;

import java.util.Random;

public class HellzoneGrenade extends MagicSpell implements Listener {
    private final Random rand = new Random();

    private Player player;
    private final MagicAndSkills plugin;

    public HellzoneGrenade(MagicAndSkills plugin) {
        super(plugin, 3000, 10);
        this.plugin = plugin;
    }

    @EventHandler
    public void onHellzoneGrenadeEvent(HellzoneEvent event) {
        this.player = event.getPlayer();
        executeAbility(player);
    }

    @Override
    public void ability(){
        Location origin = player.getEyeLocation().clone().add(player.getEyeLocation().getDirection().multiply(2));
        RayTraceResult result = player.getWorld().rayTraceEntities(origin, player.getEyeLocation().getDirection(), 40.0);

        if (result != null && result.getHitEntity() != null && result.getHitEntity() != null) { // was if r.ghe instance of Mob
            Entity entity = result.getHitEntity();
            if (player.getLocation().distance(entity.getLocation()) < 5) {
                player.sendMessage("You're too close to use this!");
            }
            setOnCooldown();
            World world = player.getWorld();

            for (int i = 0; i < 20; i++) {
                Location loc = entity.getLocation();
                summonFireball(world, player, loc, 15);
            }
            Location loc = entity.getLocation();
            new BukkitRunnable() {
                @Override
                public void run() {
                    world.createExplosion(loc, 5f, true, true, player);
                }
            }.runTaskLater(plugin, 10);
        }
    }

    private void summonFireball(World world, Player player, Location loc, int radius) {
        Vector vector = getRandomLocationOnHemisphere(radius);
        loc.add(vector).setDirection(vector.multiply(-1.0));

        Fireball fireball = world.spawn(loc, Fireball.class);
        loc.getWorld().spawnParticle(Particle.SMOKE_LARGE, loc, 50, 0.5, .5, 0.5, 0);
        fireball.setGravity(false);

        fireball.setVelocity(loc.getDirection().multiply(1.5));
        fireball.setYield(0.0f);
        fireball.setShooter(player);
        fireball.setBounce(false);  // Doesnt seem to work
        fireball.setInvulnerable(true);
        new BukkitRunnable() {
            @Override
            public void run() {
                fireball.remove();
            }
        }.runTaskLater(plugin, 15);
    }


    private Vector getRandomLocationOnHemisphere(int radius) {
        double randX = ((rand.nextDouble() * 100) % radius);
        double randZ = ((rand.nextDouble() * 100) % radius);
        double randY = Math.sqrt(radius * radius - (randX * randX) - (randZ * randZ));

        if (rand.nextBoolean())
            randX = -randX;

        if (rand.nextBoolean())
            randZ = -randZ;

        if (randY < 4)
            randY += 4;

        return new Vector((int) randX, (int) randY, (int) randZ);

    }

    private int genRandNum(int max, int min) {
        int num = rand.nextInt(max - min + 1) + min;
        if (rand.nextBoolean()) {
            return num * -1;
        } else {
            return num;
        }
    }
}
