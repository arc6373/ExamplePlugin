package teh.predv2.Solaris.Commands;

import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import teh.predv2.Solaris.Main;

public class ProfileTest implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player))
		{
			// Sender is not a player and wont work!
			return false;
		}
		
		Player player = (Player)sender;

		NamespacedKey key = new NamespacedKey(Main.plugin, "MAX_MANA");
		PersistentDataContainer playerCont = player.getPersistentDataContainer();
		
		if (playerCont.has(key, PersistentDataType.INTEGER))
		{
			Integer playerMaxMana = playerCont.get(key, PersistentDataType.INTEGER);
			System.out.println("Max mana found! " + playerMaxMana);
		}
		else
		{
			playerCont.set(key, PersistentDataType.INTEGER, 50);
			System.out.println("No max mana was found! Setting it!");
		}
		
		return true;
	}
	
}
