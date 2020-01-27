package teh.predv2.Solaris.EventHandlers;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;
import org.bukkit.util.Vector;

import teh.predv2.Solaris.Spells.MagicSpell;

public class PlayerAnimationHandler implements Listener
{
	
	@EventHandler
	public void playerAnimation(PlayerAnimationEvent event)
	{
		// Check the animation type is arm swing
		if (event.getAnimationType() == PlayerAnimationType.ARM_SWING)
		{
			// Get the player
			Player player = event.getPlayer();
			if (player.getInventory().getItemInMainHand() != null &&
			player.getInventory().getItemInMainHand().getType() == Material.STICK)
			{
				
				Location loc = player.getLocation();
				Vector direction = loc.getDirection();
				
				// TODO: Find proper spell however wanted
				MagicSpell.animateSpell(player, loc, direction);
			}
		}
	}
}
