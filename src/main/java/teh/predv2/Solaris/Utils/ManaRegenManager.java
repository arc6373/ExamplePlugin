package teh.predv2.Solaris.Utils;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import teh.predv2.Solaris.Main;
import teh.predv2.Solaris.Constants.Variables;
import teh.predv2.Solaris.Runnables.ManaRegen;

public class ManaRegenManager 
{
	public Map<Player, ManaRegen> regenMap;
	
	public ManaRegenManager()
	{
		regenMap = new HashMap<Player, ManaRegen>();
	}
	
	public void startRegenTask(Player p)
	{
		if (regenMap.containsKey(p))
		{
			// A regen task is already created
			ManaRegen tmpTask = regenMap.get(p);
			
			// If the task is cancelled, restart it!
			if (tmpTask.isCancelled() == true)
			{
				tmpTask = new ManaRegen(p);
				regenMap.put(p, tmpTask);
				// These can be scalable
				tmpTask.runTaskTimer(Main.plugin, 20, 20);
			}
		}
		// No task was found for that player, create one
		else
		{
			ManaRegen tmpTask = new ManaRegen(p);
			// Add new task to a map and start it
			regenMap.put(p, tmpTask);
			tmpTask.runTaskTimer(Main.plugin, 20, 20);
		}
	}
	
	public void stopRegenTask(Player p)
	{
		if (regenMap.containsKey(p))
		{
			ManaRegen tmpTask = regenMap.get(p);
			tmpTask.cancel();
		}
	}
	
	public void reducePlayerMana(Player p, Integer reduceManaAmt)
	{
		// Reduce the mana
		Integer tmpMana = p.getPersistentDataContainer().get(Variables.keyMana, PersistentDataType.INTEGER);
		tmpMana -= reduceManaAmt;
		p.getPersistentDataContainer().set(Variables.keyMana, PersistentDataType.INTEGER, tmpMana);
		// Update the mana level the user sees
		p.setLevel(tmpMana);
	
		// Start the regen task from here
		this.startRegenTask(p);
	}
	
	public void resetPlayerMana(Player p)
	{
		
	}
	
	public void setPlayerMana(Player p, Integer newMana)
	{
		p.getPersistentDataContainer().set(Variables.keyMana, PersistentDataType.INTEGER, newMana);
		p.setLevel(newMana);
	}
	
	public void setPlayerMaxMana(Player p, Integer newMaxMana)
	{
		p.getPersistentDataContainer().set(Variables.keyMaxMana, PersistentDataType.INTEGER, newMaxMana);
		p.getPersistentDataContainer().set(Variables.keyMana, PersistentDataType.INTEGER, newMaxMana);
		p.setLevel(newMaxMana);
	}
}
