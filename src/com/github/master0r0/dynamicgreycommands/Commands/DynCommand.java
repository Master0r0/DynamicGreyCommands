package com.github.master0r0.dynamicgreycommands.Commands;

import com.github.master0r0.greycommands.Permissions.MinimalPermission;
import com.github.master0r0.greycommands.Registry.Commands.BaseCommand;
import com.github.master0r0.greycommands.Registry.Permissions.BasePermission;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class DynCommand extends BaseCommand {

    private String name;

    private String output;

    public void setName(String name) {
        this.name = name;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean execute(MessageReceivedEvent evt, String[] args) {
        evt.getChannel().sendMessage(output);
        return true;
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public boolean guildOnly() {
        return true;
    }

    @Override
    public BasePermission requiredPermission() {
        return new MinimalPermission();
    }

}
