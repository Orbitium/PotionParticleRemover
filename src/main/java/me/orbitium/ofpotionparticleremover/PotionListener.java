package me.orbitium.ofpotionparticleremover;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.BlockVector;

public class PotionListener implements Listener {

    @EventHandler
    public void onPotion(EntityPotionEffectEvent event) {
        if (event.getNewEffect() == null || !event.getNewEffect().hasParticles())
            return;
        Entity entity = event.getEntity();
        if (!(entity instanceof LivingEntity livingEntity))
            return;
        String worldName = event.getEntity().getWorld().getName();
        if (PotionParticleRemover.worlds.containsKey(worldName))
            if (PotionParticleRemover.worlds.get(worldName).contains(event.getNewEffect().getType())) {
                reApply(livingEntity, event.getNewEffect());
                return;
            }

        RegionManager region = WorldGuard.getInstance().getPlatform().getRegionContainer()
                .get(BukkitAdapter.adapt(event.getEntity().getWorld()));
        Location l = event.getEntity().getLocation();
        for (ProtectedRegion ar : region.getApplicableRegions(BlockVector3.at(l.getX(), l.getY(), l.getZ()))) {
            if (PotionParticleRemover.regions.containsKey(ar.getId())) {
                if (PotionParticleRemover.regions.get(ar.getId()).contains(event.getNewEffect().getType())) {
                    reApply(livingEntity, event.getNewEffect());
                    break;
                }
            }
        }
    }

    public static void reApply(LivingEntity livingEntity, PotionEffect potionEffect) {
        Bukkit.getScheduler().runTaskLater(PotionParticleRemover.getInstance(), () -> {
            livingEntity.removePotionEffect(potionEffect.getType());
            livingEntity.addPotionEffect(new PotionEffect(
                    potionEffect.getType(),
                    potionEffect.getDuration(),
                    potionEffect.getAmplifier(),
                    true,
                    false,
                    true));
        }, 1L);
    }

}
