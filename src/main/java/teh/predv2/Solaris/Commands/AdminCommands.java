package teh.predv2.Solaris.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import teh.predv2.Solaris.Main;
import teh.predv2.Solaris.Constants.Messages;

public class AdminCommands implements CommandExecutor
{
	private String cmdPermNode = "Solaris.Commands.Admin";
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//		if (sender.hasPermission(cmdPermNode))
//		{
//			sender.sendMessage(Messages.INVALID_PERMISSION);
//			return false;
//		}
		
		if (args.length == 0 || args[0].equalsIgnoreCase("?"))
		{
			sender.sendMessage("Admin Command Help: ");
			sender.sendMessage("/admin setmana (player) (new mana)");
			sender.sendMessage("/admin setmaxmana (player) (new max mana)");
			return true;
		}
		
		
		// Check the first argument
		// /admin setmana (player) (mana amount)
		if (args[0].equalsIgnoreCase("setmana"))
		{
			if (args.length >= 3)
			{
				Player p = Main.plugin.getServer().getPlayer(args[1]);
				Integer newMana = Integer.valueOf(args[2]);
				
				System.out.println(p);
				
				// Set the mana
				Main.manaRegenManager.setPlayerMana(p, newMana);
			}
		}
		
		if (args[0].equalsIgnoreCase("setmaxmana"))
		{
			if (args.length >= 3)
			{
				Player p = Main.plugin.getServer().getPlayer(args[1]);
				Integer newMana = Integer.valueOf(args[2]);
				
				// Set the mana
				Main.manaRegenManager.setPlayerMaxMana(p, newMana);
			}
		}
		
		
		return true;
	}
	
	
}
