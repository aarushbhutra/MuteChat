package me.anon695.mutechat.commands;

import me.anon695.mutechat.MuteChat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class MuteCommand implements CommandExecutor {

    private MuteChat muteChat;
    public MuteCommand(MuteChat muteChat) {
        this.muteChat = muteChat;
    }
    //TODO: Add chatcolor
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("mutechat.mute")) {
                if(muteChat.getConfig().getBoolean("mutechat") == true) {
                    player.sendMessage(muteChat.getConfig().getString("message.enabled"));
                } else {
                    Collection<? extends Player> playerArrayList = Bukkit.getOnlinePlayers();
                    muteChat.getConfig().set("mutechat", true);
                    if(playerArrayList.isEmpty()) {
                        player.sendMessage(muteChat.getConfig().getString("message.noplayers"));
                    } else {
                        for (Player p : playerArrayList) {
                            //TODO: Add NMS and send titles/actionbars/bossbars
                            p.sendMessage(muteChat.getConfig().getString("message.player").replace("%player%", player.getName()));
                        }
                    }
                    muteChat.saveConfig();
                }
            } else {
                player.sendMessage(muteChat.getConfig().getString("message.permission"));
            }
        } else {
            if(muteChat.getConfig().getBoolean("mutechat") == true) {
                sender.sendMessage(muteChat.getConfig().getString("message.enabled"));
            } else {
                sender.sendMessage(muteChat.getConfig().getString("message.muted"));
                Collection<? extends Player> playerArrayList = Bukkit.getOnlinePlayers();
                muteChat.getConfig().set("mutechat", true);
                if(playerArrayList.isEmpty()) {
                    sender.sendMessage(muteChat.getConfig().getString("message.noplayers"));
                } else {
                    for(Player p : playerArrayList) {
                        p.sendMessage(muteChat.getConfig().getString("message.console"));
                    }
                }
                muteChat.saveConfig();
            }

        }

        return false;
    }
}
