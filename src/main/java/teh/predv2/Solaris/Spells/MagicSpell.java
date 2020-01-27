package teh.predv2.Solaris.Spells;

import java.util.Collection;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import teh.predv2.Solaris.Main;
import teh.predv2.Solaris.Constants.Messages;
import teh.predv2.Solaris.Constants.Variables;


public class MagicSpell
{
	static int distance = 10;
	static int damage = 5;
	static int manaCost = 10;
	
	public static boolean manaCheckAndReduce(Player p)
	{
		if (p.getPersistentDataContainer().get(Variables.keyMana, PersistentDataType.INTEGER) >= manaCost)
		{
			// Reduce the mana of the player and also start the regen task
			Main.manaRegenManager.reducePlayerMana(p, manaCost);
			// Return true to run the spell
			return true;
		}
		// Not enough mana!
		return false;
	}
	
	public static void animateSpell(Player p, Location loc, Vector direction)
	{
		// Check if the player has enough mana or not
		if (manaCheckAndReduce(p) == false)
		{
			p.sendMessage(Messages.NOT_ENOUGH_MANA);
			return;
		}
		
		// Do the spell!
		int loopIndex = 0;
		Collection<Entity> nearbyEntities;
		// Increase the spell to eye level
		loc.add(0, 1.5, 0);
		
		for (loopIndex = 0; loopIndex < distance; loopIndex++)
		{
			loc.add(direction);				
			// Spawn the particle
			DustOptions dustOptions = new DustOptions(Color.fromRGB(0, 127, 255), 1);
			loc.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, dustOptions);
				
			if (loc.getBlock().getType().isSolid())
			{
				// Check to see if it is a block
				MagicSpell.doDamage(loc);
					
				// No longer to need to finish the loop
				return;
			}

			nearbyEntities = loc.getWorld().getNearbyEntities(loc, .5, .5, .5);
			
			for (Entity e : nearbyEntities)
			{
				if (e.getType().isAlive() && e != p)
				{
					// The spell is close to an entity do the damage!
					MagicSpell.doDamage(loc);
					return;
				}
			}
		}
		MagicSpell.doDamage(loc);
		return;
	}
	
	public static void doDamage(Location spellLoc)
	{
		for (Entity e : spellLoc.getWorld().getNearbyEntities(spellLoc, 2, 2, 2))
		{
			if (e.getType().isAlive())
			{
				Damageable d = (Damageable)e;
				d.damage(damage);
			}
		}
	}
}
