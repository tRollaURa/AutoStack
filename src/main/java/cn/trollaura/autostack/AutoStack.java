package cn.trollaura.autostack;

import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.Delayed;

public final class AutoStack extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("[AutoStack] Loading... by tRollaURa_");
        getCommand("dd").setExecutor(new Listener());
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
