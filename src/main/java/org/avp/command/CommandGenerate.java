package org.avp.command;

import com.arisux.mdxlib.MDX;
import com.arisux.mdxlib.lib.game.Chat;
import com.arisux.mdxlib.lib.world.CoordData;
import com.arisux.mdxlib.lib.world.Structure;
import com.arisux.mdxlib.lib.world.StructureGenerationHandler;
import com.arisux.mdxlib.lib.world.entity.player.Players;
import com.arisux.mdxlib.lib.world.storage.Schematic;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

public class CommandGenerate extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "genschematic";
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender)
    {
        return "Generates a schematic that is currently loaded.";
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args)
    {
        final EntityPlayer player = Players.getPlayerForCommandSender(commandSender);

        if (args.length == 1 || args.length == 4)
        {
            String schematicTargetName = args[0];

            for (Schematic schematic : MDX.getSchematicRegistry())
            {
                String schematicFileName = schematic.getFile().getName();
                final String schematicName = schematicFileName.replace(".schematic", "");

                if (schematicTargetName.equals(schematicName))
                {
                    CoordData data = null;

                    if (args.length == 1)
                    {
                        data = new CoordData(player.posX, player.posY, player.posZ);
                    }
                    else if (args.length == 4)
                    {
                        double posX = Double.parseDouble(args[1]);
                        double posY = Double.parseDouble(args[2]);
                        double posZ = Double.parseDouble(args[3]);
                        data = new CoordData(posX, posY, posZ);
                    }

                    WorldServer worldServer = MinecraftServer.getServer().worldServerForDimension(player.dimension);
                    Structure structure = new Structure(schematic, worldServer, data)
                    {
                        @Override
                        public String getName()
                        {
                            return schematicName;
                        }

                        @Override
                        public void onProcessing()
                        {
                            ;
                        }

                        @Override
                        public void onProcessingComplete()
                        {
                            player.addChatMessage(Chat.component("Generation of " + this.getName() + " completed."));
                        }
                    };

                    StructureGenerationHandler.addStructureToQueue(structure);
                    commandSender.addChatMessage(Chat.component("Started generation of " + schematicName));
                }
            }
        }
    }
}
