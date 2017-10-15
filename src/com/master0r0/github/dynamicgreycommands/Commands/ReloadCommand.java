package com.master0r0.github.dynamicgreycommands.Commands;

import com.github.master0r0.greycommands.GreyCommands;
import com.github.master0r0.greycommands.Permissions.SuperUserPermission;
import com.github.master0r0.greycommands.Registry.Commands.BaseCommand;
import com.github.master0r0.greycommands.Registry.Permissions.BasePermission;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class ReloadCommand extends BaseCommand {

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public boolean execute(MessageReceivedEvent evt, String[] args) {
        for(String cmd : CommandRegistry.commands.keySet()){
            if(GreyCommands.getCommandRegistry().getCommands().containsKey(cmd))
                GreyCommands.getCommandRegistry().unregisterCommand(cmd);
        }
        new CommandRegistry();
        if(evt.getChannel()!=null){
            evt.getChannel().sendMessage("Commands Reloaded!");
        }else{
            evt.getAuthor().getOrCreatePMChannel().sendMessage("Commands Reloaded!");
        }
        return true;
    }

    @Override
    public String getHelp() {
        return "Reloads the dynamic commands";
    }

    @Override
    public boolean guildOnly() {
        return false;
    }

    @Override
    public BasePermission requiredPermission() {
        return new SuperUserPermission();
    }

}
