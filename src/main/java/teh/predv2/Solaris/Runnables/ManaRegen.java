package teh.predv2.Solaris.Runnables;

import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import teh.predv2.Solaris.Constants.Variables;

public class ManaRegen extends BukkitRunnable
{
	private Player player;
	
	public ManaRegen(Player p)
	{
		player = p;
	}
	
	@Override
	public void run()
	{
		Integer tmpMana = player.getPersistentDataContainer().get(Variables.keyMana, PersistentDataType.INTEGER);
		Integer tmpManaMax = player.getPersistentDataContainer().get(Variables.keyMaxMana, PersistentDataType.INTEGER);
		
		if (tmpMana >= tmpManaMax)
		{
			this.cancel();
		}
		else
		{
			// This number can be scalable to match different regen rates and whatnot
			tmpMana += 1;
			player.setLevel(tmpMana);
			player.getPersistentDataContainer().set(Variables.keyMana, PersistentDataType.INTEGER, tmpMana);
		}
	}
}
