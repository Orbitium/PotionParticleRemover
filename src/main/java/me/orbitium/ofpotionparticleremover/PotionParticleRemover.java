package me.orbitium.ofpotionparticleremover;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public final class PotionParticleRemover extends JavaPlugin {

    private static PotionParticleRemover instance;
    public static Map<String, List<PotionEffectType>> worlds = new HashMap<>();
    public static Map<String, List<PotionEffectType>> regions = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        instance = this;
        for (Object o : getConfig().getList("worlds")) {
            Map<String, List<String>> map = (Map<String, List<String>>) o;
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                for (String s : entry.getValue()) {
                    PotionEffectType potionEffectType = PotionEffectType.getByName(s);
                    if (potionEffectType == null) {
                        getLogger().log(Level.SEVERE, "Potion effect couldn't found! Name is: " + s);
                        getLogger().log(Level.SEVERE, "Potion effect couldn't found! Name is: " + s);
                        getLogger().log(Level.SEVERE, "Potion effect couldn't found! Name is: " + s);
                        getLogger().log(Level.SEVERE, "Potion effect couldn't found! Name is: " + s);
                        getLogger().log(Level.SEVERE, "Potion effect couldn't found! Name is: " + s);
                    } else {
                        List<PotionEffectType> l = worlds.getOrDefault(entry.getKey(), new ArrayList<>());
                        l.add(potionEffectType);
                        worlds.put(entry.getKey(), l);
                    }
                }
            }
        }

        for (Object o : getConfig().getList("regions")) {
            Map<String, List<String>> map = (Map<String, List<String>>) o;
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                for (String s : entry.getValue()) {
                    PotionEffectType potionEffectType = PotionEffectType.getByName(s);
                    if (potionEffectType == null) {
                        getLogger().log(Level.SEVERE, "Potion effect couldn't found! Name is: " + s);
                        getLogger().log(Level.SEVERE, "Potion effect couldn't found! Name is: " + s);
                        getLogger().log(Level.SEVERE, "Potion effect couldn't found! Name is: " + s);
                        getLogger().log(Level.SEVERE, "Potion effect couldn't found! Name is: " + s);
                        getLogger().log(Level.SEVERE, "Potion effect couldn't found! Name is: " + s);
                    } else {
                        List<PotionEffectType> l = regions.getOrDefault(entry.getKey(), new ArrayList<>());
                        l.add(potionEffectType);
                        regions.put(entry.getKey(), l);
                    }
                }
            }
        }

        getServer().getPluginManager().registerEvents(new PotionListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static PotionParticleRemover getInstance() {
        return instance;
    }


}
