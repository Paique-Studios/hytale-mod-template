package com.paiique.template.events;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;

public class ExampleEvent {

    public static void onPlayerReady(PlayerReadyEvent event) {
    event.getPlayer().sendMessage(Message.raw("Hytale test mod running!"));
    }

}
