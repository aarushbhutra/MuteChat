package me.anon695.mutechat.commands;

import me.anon695.mutechat.MuteChat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class UnmuteCommand implements CommandExecutor {

    private MuteChat muteChat;
    public UnmuteCommand(MuteChat muteChat) {
        this.muteChat = muteChat;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("mutechat.use")) {
                if (muteChat.getConfig().getBoolean("mutechat") == false){
                    player.sendMessage(muteChat.getConfig().getString("messages.nounmuted"));
                } else {
                    Collection<? extends Player> playerArrayList = Bukkit.getOnlinePlayers();
                    muteChat.getConfig().set("mutechat", false);
                    if(playerArrayList.isEmpty()) {
                        player.sendMessage(muteChat.getConfig().getString("message.noplayers"));
                    } else {
                        for (Player p : playerArrayList) {
                            //TODO: Add NMS and send titles/actionbars/bossbars
                            p.sendMessage(muteChat.getConfig().getString("message.ummuteplayer").replace("%player%", player.getName()));
                        }
                    }
                    muteChat.saveConfig();
                }
            } else {
                player.sendMessage(muteChat.getConfig().getString("message.permission"));
            }
        } else {
            if (muteChat.getConfig().getBoolean("mutechat") == false){
                sender.sendMessage(muteChat.getConfig().getString("messages.nounmuted"));
            } else {
                Collection<? extends Player> playerArrayList = Bukkit.getOnlinePlayers();
                muteChat.getConfig().set("mutechat", false);
                if(playerArrayList.isEmpty()) {
                    sender.sendMessage(muteChat.getConfig().getString("message.noplayers"));
                } else {
                    for (Player p : playerArrayList) {
                        p.sendMessage(muteChat.getConfig().getString("message.ummuteconsole"));
                    }
                }
            }
        }
        return false;
    }
}
