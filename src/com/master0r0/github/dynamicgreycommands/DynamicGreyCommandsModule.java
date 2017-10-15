package com.master0r0.github.dynamicgreycommands;

import com.master0r0.github.dynamicgreycommands.Commands.CommandRegistry;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.modules.IModule;

import java.net.URISyntaxException;

public class DynamicGreyCommandsModule implements IModule {

    private static DynamicGreyCommandsModule instance;
    private IDiscordClient client;

    @Override
    public boolean enable(IDiscordClient client) {
        this.client = client;
        DynamicGreyCommands.logger.info("Dynamic Commands Module Started!");
        try {
            new CommandRegistry();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        System.out.println("WTF");
        return true;
    }

    public static DynamicGreyCommandsModule getInstance() {
        return instance;
    }

    @Override
    public void disable() {
        DynamicGreyCommands.logger.info("Dynamic Commands Module Stopping!");
    }

    @Override
    public String getName() {
        return "DynGreyCommands";
    }

    @Override
    public String getAuthor() {
        return "Master0r0";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String getMinimumDiscord4JVersion() {
        return "2.9.1";
    }

    public IDiscordClient getClient() {
        return client;
    }
}
