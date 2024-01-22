package top.cmarco.systeminfo.commands;

import org.jetbrains.annotations.NotNull;
import top.cmarco.systeminfo.plugin.SystemInfo;
import top.cmarco.systeminfo.enums.Messages;
import top.cmarco.systeminfo.utils.Utils;
import org.bukkit.command.CommandSender;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

/**
 * The `CommandUptime` class is a Spigot command that allows players with the appropriate permission to retrieve
 * information about the JVM uptime using the "/uptime" command.
 */
public final class CommandUptime extends SystemInfoCommand {

    /**
     * Initializes a new instance of the `CommandUptime` class.
     *
     * @param systemInfo The `SystemInfo` instance associated with this command.
     */
    public CommandUptime(@NotNull SystemInfo systemInfo) {
        super(systemInfo, "uptime",
                "get the JVM uptime",
                "/<command>",
                Collections.emptyList());
    }

    /**
     * Executes the "/uptime" command, providing information about JVM uptime to the sender.
     *
     * @param sender The command sender.
     * @param name   The command name.
     * @param args   The command arguments (not used in this command).
     * @return True if the command was executed successfully; otherwise, false.
     */
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String name, String[] args) {
        if (args.length == 0) {
            if (sender.hasPermission("systeminfo.commands.uptime")) {
                uptime(sender);
                return true;
            } else {
                sender.sendMessage(Messages.NO_PERMISSIONS.value(true));
            }
        } else {
            sender.sendMessage(Messages.OUT_OF_ARGS.value(true));
        }
        return false;
    }

    /**
     * Sends information about JVM uptime to the specified command sender.
     *
     * @param sender The command sender.
     */
    private void uptime(CommandSender sender) {
        final long uptime = ChronoUnit.MINUTES.between(systemInfo.getsT(), LocalDateTime.now());
        sender.sendMessage(Utils.color("&2»» &7Machine uptime &2««"));
        sender.sendMessage(Utils.color("&2» &7JVM Uptime: &a" + uptime + " min."));
    }
}
