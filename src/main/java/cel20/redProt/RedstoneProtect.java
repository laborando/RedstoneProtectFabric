package cel20.redProt;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.Event;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.permissions.Permission;
import net.minecraft.server.permissions.Permissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RedstoneProtect implements ModInitializer {

    public static boolean redstoneProtected = true;
    public static Logger LOGGER;

    @Override
    public void onInitialize() {

        LOGGER = LoggerFactory.getLogger("MeineMod");

        //Command

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {

            dispatcher.register(Commands.literal("redProt")
                    .requires(source -> source.permissions().hasPermission(Permissions.COMMANDS_ADMIN))
                    .executes(context -> {

                toggleRedProt();

                if(redstoneProtected){
                    context.getSource().sendSuccess(() -> Component.literal("Redstone protection resumed."), true);

                    LOGGER.info("RedstoneProtect resumed by: {}", context.getSource().getTextName());
                }else{
                    context.getSource().sendSuccess(() -> Component.literal("Redstone protection paused."), true);

                    LOGGER.info("RedstoneProtect paused by: {}", context.getSource().getTextName());
                }
                return 1;
            }));

        });


        LOGGER.info("RedstoneProtect enabled");

    }

    private void toggleRedProt() {

        redstoneProtected = !redstoneProtected;

    }
}
