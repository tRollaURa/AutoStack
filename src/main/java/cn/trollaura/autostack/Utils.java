package cn.trollaura.autostack;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

public class Utils extends Listener {
    public static String color(String text ) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    public static Plugin config() {
        return cn.trollaura.autostack.AutoStack.getProvidingPlugin(cn.trollaura.autostack.AutoStack.class);
    }
}
