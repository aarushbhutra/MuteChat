package me.anon695.mutechat.events;

import me.anon695.mutechat.MuteChat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatTalk implements Listener {

    private MuteChat muteChat;
    public ChatTalk(MuteChat muteChat) {
        this.muteChat = muteChat;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        if (muteChat.getConfig().getBoolean("mutechat") == true) {
            if (player.hasPermission("mutechat.bypass")) {
                return;
            } else {
                e.setCancelled(true);
                player.sendMessage(muteChat.getConfig().getString("messages.mutedchat"));
            }
        } else {
            return;
        }

    }
}
