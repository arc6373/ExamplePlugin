package teh.predv2.Solaris.EventHandlers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import teh.predv2.Solaris.Constants.Variables;

public class PlayerJoinHandler implements Listener
{
	@EventHandler
	public void playerJoined(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		PersistentDataContainer playerCont = player.getPersistentDataContainer();
		// Check if the key exists,  if it doesnt set it to the starting value
		if (!(playerCont.has(Variables.keyMaxMana, PersistentDataType.INTEGER)))
		{
			playerCont.set(Variables.keyMaxMana, PersistentDataType.INTEGER, Variables.STARTING_MAX_MANA);
			playerCont.set(Variables.keyMana, PersistentDataType.INTEGER, Variables.STARTING_MAX_MANA);
		}
		
		// Check if the key exists,  if it doesnt set it to the starting value
		if (!(playerCont.has(Variables.keyMaxHealth, PersistentDataType.INTEGER)))
		{
			playerCont.set(Variables.keyMaxHealth, PersistentDataType.INTEGER, Variables.STARTING_MAX_HEALTH);
			playerCont.set(Variables.keyHealth, PersistentDataType.INTEGER, Variables.STARTING_MAX_HEALTH);
		}
		
		// Should we set mana back to max mana?
		Integer tmpMaxMana = playerCont.get(Variables.keyMaxMana, PersistentDataType.INTEGER);
		Integer tmpMana = playerCont.get(Variables.keyMana, PersistentDataType.INTEGER);
		if (tmpMaxMana != tmpMana)
		{
			playerCont.set(Variables.keyMana, PersistentDataType.INTEGER, tmpMaxMana);
		}
		
		// Set the players experience level to match the mana count!
		player.setLevel(tmpMaxMana);
	}
}
