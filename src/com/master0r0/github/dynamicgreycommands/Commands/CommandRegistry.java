package com.master0r0.github.dynamicgreycommands.Commands;

import com.github.master0r0.greycommands.GreyCommands;
import com.github.master0r0.greycommands.Registry.Commands.BaseCommand;
import com.master0r0.github.dynamicgreycommands.DynamicGreyCommands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {

    public static Map<String,BaseCommand> commands;

    private String jarPath = CommandRegistry.class.getProtectionDomain().getCodeSource().getLocation().getPath();

    public CommandRegistry() {
        commands = new HashMap<>();
        if(loadFile()) {
            DynamicGreyCommands.logger.info("Commands loaded successfully!");
        }else
            DynamicGreyCommands.logger.warn("Commands could not be loaded!");
        GreyCommands.getCommandRegistry().registerCommand(new ReloadCommand());
    }

    private boolean loadFile(){
        jarPath = jarPath.substring(1,jarPath.lastIndexOf("/") );
        File directory = new File(jarPath+"/DynCommands");
        if(!directory.isDirectory())
            if(!directory.mkdir())
                DynamicGreyCommands.logger.error("Directory could not be created");
        File file = new File(directory.getPath()+"/commands.txt");
        if(!file.exists()){
            file = createFile(file);
            if(file==null)
                DynamicGreyCommands.logger.error("Command File could not be created!");
        }else{
            if(file.canRead()){
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line = reader.readLine();
                    while (line!=null) {
                        String cmd = line.split(":")[0];
                        String output = line.split(":")[1];
                        if (generateCommand(cmd, output))
                            DynamicGreyCommands.logger.info(String.format("Command %s was added successfully", cmd));
                        else
                            DynamicGreyCommands.logger.info(String.format("Command %s failed to be created", cmd));
                        line = reader.readLine();
                    }
                    reader.close();
                    for(String cmd:commands.keySet()){
                        DynamicGreyCommands.logger.info(cmd);
                    }
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                DynamicGreyCommands.logger.error("Command File could not be read!");
            }
        }
        return false;
    }

    public boolean generateCommand(String cmd, String output){
        DynCommand command = new DynCommand();
        command.setName(cmd);
        command.setOutput(output);
        commands.put(command.getName(),command);
        GreyCommands.getCommandRegistry().registerCommand(command);
        if(commands.containsKey(cmd))
            return true;
        return false;
    }

    private static File createFile(File file){
        try{
            if(file.createNewFile()){
                DynamicGreyCommands.logger.info("Commands File Created!");
                return file;
            }else{
                DynamicGreyCommands.logger.warn("Command File could not be created!");
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            DynamicGreyCommands.logger.error("File Creation Failed!");
            return null;
        }
    }
}
