package me.anon695.mutechat;

import me.anon695.mutechat.commands.MuteCommand;
import me.anon695.mutechat.events.ChatTalk;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class MuteChat extends JavaPlugin {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        System.out.println(ChatColor.GREEN + "MuteCommand has been enabled! Made by Anon695");
        getCommand("mutechat").setExecutor(new MuteCommand(this));
        Bukkit.getPluginManager().registerEvents(new ChatTalk(this), this);
        getLogger().info("MuteChat has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
