package cn.trollaura.autostack;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (sender.hasPermission("autostack.use.as") || Utils.config().getConfig().getBoolean("EveryoneHasPermission")) {
            if (args.length == 0) {
                sender.sendMessage(Utils.color("&6[AutoStack] &bYou can use /autostack reload. or /autostack notify <on/off>"));
            }
            if (args.length == 1) {
                if (args[0].equals("reload")) {
                    Utils.config().reloadConfig();
                    sender.sendMessage(Utils.color("&6[AutoStack] &aConfigs reloaded."));
                }
            }
            if(args.length == 2 && args[0].equals("notify")){
                if(args[1].equals("on")){
                    Utils.config().getConfig().set("Notify",true);
                    Utils.config().saveConfig();
                    Utils.config().reloadConfig();
                }
                if(args[1].equals("off")){
                    Utils.config().getConfig().set("Notify",false);
                    Utils.config().saveConfig();
                    Utils.config().reloadConfig();
                }

            }
            return false;
        }else{
            sender.sendMessage(Utils.color("&6[AutoStack]" + " " + "&4You have not permission!"));
        }
        return false;
    }
}
