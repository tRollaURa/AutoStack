// plugins made by trollaura
// L plugins
// QQ1650108081
package cn.trollaura.autostack;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class Listener implements CommandExecutor , org.bukkit.event.Listener {
    /**
     * @Author tRollaURa_
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("autostack.use.dd") || Utils.config().getConfig().getBoolean("EveryoneHasPermission")) {
                if (player.getInventory().getItemInMainHand().getType() != Material.AIR && player.getInventory().getItemInMainHand().getType() != Material.TOTEM) {
                    if (player.getInventory().getItemInMainHand().getAmount() != Utils.config().getConfig().getInt("Amounts")) {
                        if (!(player.getInventory().getItemInMainHand().getAmount() > Utils.config().getConfig().getInt("Amounts"))){
                            player.getInventory().getItemInMainHand().setAmount(Utils.config().getConfig().getInt("Amounts"));
                            sender.sendMessage(Utils.color("&6[AutoStack]" + " " + "&aOK now!"));
                            if(Utils.config().getConfig().getBoolean("Notify")){
                                Bukkit.getLogger().info(Utils.color(("&6[AutoStack] &c" + player.getDisplayName() + " &b is using /dd now!") + "&a X:"+Math.round(player.getLocation().getX()) + "&a Y:"+ Math.round(player.getLocation().getY()) + "&a Z:" + Math.round(player.getLocation().getZ())));
                            }
                    }else {
                            sender.sendMessage(Utils.color("&6[AutoStack]" + " " + "&4No " + ">" + String.valueOf(Utils.config().getConfig().getInt("Amounts")) + " items!"));
                        }
                    } else {
                        sender.sendMessage(Utils.color("&6[AutoStack]" + " " + "&4No " + String.valueOf(Utils.config().getConfig().getInt("Amounts")) + " items!"));
                    }
                } else {
                    sender.sendMessage(Utils.color("&6[AutoStack]" + " " + "&4No air or totem!"));
                }
                return false;
            } else {
                sender.sendMessage(Utils.color("&6[AutoStack]" + " " + "&4You have not permission!"));
            }
            return false;
        }else {
            sender.sendMessage("Not Here:(");
        }
        return false;
    }


    @EventHandler
    public void onDrop(PlayerDropItemEvent evt){
        if(Utils.config().getConfig().getBoolean("AntiDropStackItems")) {
            if ((evt.getPlayer().getInventory().getItemInMainHand().getAmount() > evt.getPlayer().getInventory().getItemInMainHand().getType().getMaxStackSize() && evt.getItemDrop().getItemStack().getType() == evt.getPlayer().getInventory().getItemInMainHand().getType())) {
                evt.getItemDrop().setItemStack(new ItemStack(Material.AIR));
            }
        }
    }

    @EventHandler
    public void onPickUp(PlayerPickupItemEvent evt) {
        if (Utils.config().getConfig().getBoolean("FixStackItemsPickUp")) {
            if (evt.getItem().getItemStack().getAmount() >= evt.getPlayer().getInventory().getItemInMainHand().getMaxStackSize()) {
                evt.setCancelled(true);
                evt.getPlayer().getInventory().addItem(evt.getItem().getItemStack());
                evt.getItem().setItemStack(new ItemStack(Material.AIR));
            }
        }
    }

    @EventHandler
    public void onMainHandTotem(PlayerMoveEvent evt){
        Player player = evt.getPlayer();
        if(Utils.config().getConfig().getBoolean("AntiOnMainHandStackTotem")) {
            if (evt.getPlayer().getInventory().getItemInMainHand().getAmount() > 1 && evt.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.TOTEM)) {
                evt.getPlayer().getInventory().getItemInMainHand().setAmount(1);
                Bukkit.getLogger().info(Utils.color(("&6[AutoStack] &c" + player.getDisplayName() + " &b is holding >1 totem now!") + "&a X:" + Math.round(player.getLocation().getX()) + "&a Y:" + Math.round(player.getLocation().getY()) + "&a Z:" + Math.round(player.getLocation().getZ())));
            }
        }
    }

    @EventHandler
    public void onRegularPlayers(PlayerMoveEvent evt) {
        Player player = evt.getPlayer();
        if(Utils.config().getConfig().getBoolean("AntiDefaultPlayerOnMainHand")){
        if (!player.hasPermission("autostack.use.dd")) {
            if (evt.getPlayer().getInventory().getItemInMainHand().getAmount() > evt.getPlayer().getInventory().getItemInMainHand().getMaxStackSize()) {
                evt.getPlayer().getInventory().getItemInMainHand().setAmount(1);
                Bukkit.getLogger().info(Utils.color(("&6[AutoStack] &c" + player.getDisplayName() + " &b is holding >1 illegalitems now!") + "&a X:" + Math.round(player.getLocation().getX()) + "&a Y:" + Math.round(player.getLocation().getY()) + "&a Z:" + Math.round(player.getLocation().getZ())));
            }
        }
        }
    }
}
