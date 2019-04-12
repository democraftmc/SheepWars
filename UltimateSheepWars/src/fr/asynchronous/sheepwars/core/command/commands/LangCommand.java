package fr.asynchronous.sheepwars.core.command.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.asynchronous.sheepwars.core.SheepWarsPlugin;
import fr.asynchronous.sheepwars.core.data.PlayerData;
import fr.asynchronous.sheepwars.core.message.Language;

public class LangCommand implements CommandExecutor {

	public SheepWarsPlugin plugin;
	
	public LangCommand(SheepWarsPlugin plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Please connect on the server, then do this command again.");
            return true;
        }
        if (!plugin.isConfigured()) {
    		sender.sendMessage(ChatColor.RED + "You can't use this command until the plugin isn't fully configured.");
    		return true;
    	}
    	final Player player = (Player)sender;
    	final PlayerData data = PlayerData.getPlayerData(player);
    	if (args.length != 0) {
            final String sub = args[0];
            if (Language.getLanguage(sub) != null)
            {
            	data.setLanguage(Language.getLanguage(sub));
            } else {
            	player.sendMessage(ChatColor.RED + "This language doesn't exist.");
            }
    	} else {
    		Language.listAvailableLanguages(player);
    		return true;
        }
		return false;
	}
}
