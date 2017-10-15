package com.master0r0.github.dynamicgreycommands;

import com.master0r0.github.dynamicgreycommands.Commands.CommandRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.modules.IModule;

public class DynamicGreyCommandsModule implements IModule {

    public static Logger logger = LoggerFactory.getLogger("DynGreyCommands");

    private static DynamicGreyCommandsModule instance;
    private IDiscordClient client;

    @Override
    public boolean enable(IDiscordClient client) {
        this.client = client;
        instance = this;
        logger.info("Dynamic Commands Module Started!");
        new CommandRegistry();
        return true;
    }

    public static DynamicGreyCommandsModule getInstance() {
        return instance;
    }

    @Override
    public void disable() {
        logger.info("Dynamic Commands Module Stopping!");
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
