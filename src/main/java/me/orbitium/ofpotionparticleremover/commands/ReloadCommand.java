package me.orbitium.ofpotionparticleremover.commands;

import me.orbitium.ofpotionparticleremover.PotionParticleRemover;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Collections;
import java.util.List;

public class ReloadCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp())
            return true;
        if (!args[0].equals("reload"))
            return true;
        PotionParticleRemover.getInstance().reloadConfig();
        PotionParticleRemover.load();
        sender.sendMessage(ChatColor.GREEN + "Reload completed!");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return Collections.singletonList("reload");
    }
}
