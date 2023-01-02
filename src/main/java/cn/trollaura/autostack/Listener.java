// plugins made by trollaura
// L plugins
// QQ1650108081
package cn.trollaura.autostack;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public class Listener implements CommandExecutor {
    /**
     * @Author tRollaURa_
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        PlayerInventory item = (player.getInventory());
        if (sender.hasPermission("autostack.use")) {
            if (player.getInventory().getItemInMainHand().getType() != Material.AIR || player.getInventory().getItemInMainHand().getType() != Material.TOTEM) {
                if(player.getInventory().getItemInMainHand().getAmount() != 64) {
                    player.getInventory().getItemInMainHand().setAmount(Utils.config().getConfig().getInt("Amounts"));
                    sender.sendMessage(Utils.color("&6[AutoStack]" + " " + "&aOK now!"));
                }else{
                    sender.sendMessage(Utils.color("&6[AutoStack]" + " " + "&4No 64 items!"));
                }
            }else {
                sender.sendMessage(Utils.color("&6[AutoStack]" + " " + "&4No air or totem!"));
            }
            return false;
        }else{
            sender.sendMessage(Utils.color("&6[AutoStack]" + " " + "&4You have not permission!"));
        }
        return false;
    }
}
