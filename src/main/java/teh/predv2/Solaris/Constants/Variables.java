package teh.predv2.Solaris.Constants;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;

import teh.predv2.Solaris.Main;

public class Variables 
{
	// Variables used during startup to apply to the user
	public static Integer STARTING_MAX_MANA = 50;
	public static Integer STARTING_MAX_HEALTH = 50;
	
	// Variables used for persistent data container
	// Namespace keys for persistent data to prevent misuse errors
	public static NamespacedKey keyMaxMana = new NamespacedKey(Main.plugin, "MAX_MANA");
	public static NamespacedKey keyMana = new NamespacedKey(Main.plugin, "MANA");
	public static NamespacedKey keyMaxHealth = new NamespacedKey(Main.plugin, "MAX_HEALTH");
	public static NamespacedKey keyHealth = new NamespacedKey(Main.plugin, "HEALTH");

	// These abilities will be passed to a manager which will apply and remove abilities
	@SuppressWarnings("rawtypes")
	public enum Abilities
	{
		MAGIC_DAMAGE("MAGIC_DAMAGE", 10, PersistentDataType.INTEGER),
		MAGIC_RES("MAGIC_RESISTANCE", 10, PersistentDataType.INTEGER);
		
		private String name;
		private Integer amount;
		private PersistentDataType type;
		
		
		Abilities(String name, Integer amount, PersistentDataType type)
		{
			this.name = name;
			this.amount = amount;
			this.type = type;
		}
		
		public String getName()
		{
			return this.name;
		}
		
		public Integer getAmount()
		{
			return this.amount;
		}
		
		public PersistentDataType getType()
		{
			return this.type;
		}
	}
}
