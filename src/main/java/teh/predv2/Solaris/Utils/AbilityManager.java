package teh.predv2.Solaris.Utils;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import teh.predv2.Solaris.Main;
import teh.predv2.Solaris.Constants.Variables.Abilities;

public class AbilityManager 
{
	
	@SuppressWarnings("unchecked")
	public void applyAbility(Player p, Abilities ability)
	{
		NamespacedKey tmpName = new NamespacedKey(Main.plugin, ability.getName());
		Object tmpVal = p.getPersistentDataContainer().get(tmpName, ability.getType());
		
		if (ability.getType() == PersistentDataType.INTEGER)
		{
			Integer tmpValInt = (Integer)tmpVal;
			tmpValInt += ability.getAmount();
			tmpVal = (Object)tmpValInt;
		}
		else if (ability.getType() == PersistentDataType.DOUBLE)
		{
			Double tmpValDbl = (Double)tmpVal;
			tmpValDbl += ability.getAmount();
			tmpVal = (Object)tmpValDbl;
		}

		p.getPersistentDataContainer().set(tmpName, ability.getType(), tmpVal);
	}
	
	@SuppressWarnings("unchecked")
	public void removeAbility(Player p, Abilities ability)
	{
		NamespacedKey tmpName = new NamespacedKey(Main.plugin, ability.getName());
		Object tmpVal = p.getPersistentDataContainer().get(tmpName, ability.getType());
		
		if (ability.getType() == PersistentDataType.INTEGER)
		{
			Integer tmpValInt = (Integer)tmpVal;
			tmpValInt -= ability.getAmount();
			tmpVal = (Object)tmpValInt;
		}
		else if (ability.getType() == PersistentDataType.DOUBLE)
		{
			Double tmpValDbl = (Double)tmpVal;
			tmpValDbl -= ability.getAmount();
			tmpVal = (Object)tmpValDbl;
		}
		
		p.getPersistentDataContainer().set(tmpName, ability.getType(), tmpVal);
	} 
}
