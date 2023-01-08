package cn.trollaura.autostack;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AutoStack extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info(Utils.color("&6[AutoStack] &aLoading... by tRollaURa_"));
        Bukkit.getLogger().info(Utils.color("&6[AutoStack] &aNow Version: 1.1"));
        getCommand("dd").setExecutor(new Listener());
        getCommand("autostack").setExecutor(new MainCommand());
        getServer().getPluginManager().registerEvents(new Listener(),this);
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(Utils.color("&6[AutoStack] &aDisabling... by tRollaURa_"));
    }
}
