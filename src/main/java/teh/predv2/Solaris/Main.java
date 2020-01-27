package teh.predv2.Solaris;

import java.util.logging.Logger;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import teh.predv2.Solaris.Commands.AdminCommands;
import teh.predv2.Solaris.EventHandlers.PlayerAnimationHandler;
import teh.predv2.Solaris.EventHandlers.PlayerJoinHandler;
import teh.predv2.Solaris.Utils.ManaRegenManager;

public class Main extends JavaPlugin
{
	public static Plugin plugin;
	public static Logger logger;
	public static ManaRegenManager manaRegenManager;
	
	@Override
	public void onEnable()
	{
		plugin = this;
		manaRegenManager = new ManaRegenManager();
		
		logger = getLogger();
		logger.info("Loading Solaris!");
		
		// Register commands
		getCommand("admin").setExecutor(new AdminCommands());
		
		// Register events
		getServer().getPluginManager().registerEvents(new PlayerAnimationHandler(), plugin);
		getServer().getPluginManager().registerEvents(new PlayerJoinHandler(), plugin);
	}
	
	@Override
	public void onDisable()
	{
		// Do shutdown events
	}
}
