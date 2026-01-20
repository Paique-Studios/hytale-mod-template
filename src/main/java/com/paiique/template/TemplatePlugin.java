package com.paiique.template;

import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.paiique.template.commands.ExplodeCommand;
import com.paiique.template.events.ExampleEvent;

import javax.annotation.Nonnull;

public class TemplatePlugin extends JavaPlugin {

    public TemplatePlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }

    /**
     * Hey! You might want to change some things on resources/manifest.json before starting.
     * If you rename the package, you'll also need to change the package name in the manifest
     * as well for the name of this class.
     * Also, if you want to run the server, you can just check the Gradle task inside
     * hytale module and double-click runHytaleServer; it works with debug.
     * - Paique
     */

    @Override
    protected void setup() {
        registerEvents();
        registerCommands();
    }

    private void registerEvents() {
        this.getEventRegistry().registerGlobal(PlayerReadyEvent.class, ExampleEvent::onPlayerReady);
    }

    private void registerCommands() {
        this.getCommandRegistry().registerCommand(new ExplodeCommand("explode", "Create an explosion at your location"));
    }
}