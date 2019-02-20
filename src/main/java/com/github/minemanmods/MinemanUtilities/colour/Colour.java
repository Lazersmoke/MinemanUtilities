package com.github.minemanmods.MinemanUtilities.colour;

import org.bukkit.ChatColor;
import org.bukkit.Color;

import java.util.Objects;

public enum Colour {
    RESET(ChatColor.RESET, "\u001B[0m", "§r"),
    BLACK(ChatColor.BLACK,"\u001B[30m", "§0"),
    DARK_BLUE(ChatColor.DARK_BLUE, "\u001B[34m", "§1"),
    DARK_GREEN(ChatColor.DARK_GREEN,"\u001B[32m", "§2"),
    DARK_AQUA(ChatColor.DARK_AQUA,"\u001B[36m", "§3"),
    DARK_RED(ChatColor.DARK_RED,"\u001B[31m", "§4"),
    DARK_PURPLE(ChatColor.DARK_PURPLE,"\u001B[35m", "§5"),
    GOLD(ChatColor.GOLD,"\u001B[33m", "§6"),
    GREY(ChatColor.GRAY,"\u001B[37m", "§7"),
    GRAY(ChatColor.GRAY,"\u001B[37m", "§7"),
    DARK_GREY(ChatColor.DARK_GRAY,"\u001B[90m", "§8"),
    DARK_GRAY(ChatColor.DARK_GRAY,"\u001B[90m", "§8"),
    BLUE(ChatColor.BLUE,"\u001B[94m", "§9"),
    GREEN(ChatColor.GREEN,"\u001B[92m", "§a"),
    AQUA(ChatColor.AQUA,"\u001B[96m", "§b"),
    RED(ChatColor.RED,"\u001B[91m", "§c"),
    LIGHT_PURPLE(ChatColor.LIGHT_PURPLE,"\u001B[95m", "§d"),
    YELLOW(ChatColor.YELLOW, "\u001B[93m", "§e"),
    WHITE(ChatColor.WHITE,"\u001B[97m", "§f"),
    STRIKETHROUGH(ChatColor.STRIKETHROUGH,"\u001B[9m", "§m"),
    UNDERLINE(ChatColor.UNDERLINE,"\u001B[4m", "§n"),
    BOLD(ChatColor.BOLD,"\u001B[1m", "§l"),
    ITALIC(ChatColor.ITALIC,"\u001B[3m", "§o"),
    OBFUSCATED(ChatColor.MAGIC,"", "§k");

    private final ChatColor bukkit;
    private final String command;
    private final String game;

    Colour(ChatColor bukkit, String command, String game) {
        this.bukkit = bukkit;
        this.command = command;
        this.game = game;
    }

    public ChatColor getBukkitColour() {
        return this.bukkit;
    }

    public String getCommandColour() {
        return this.command;
    }

    public String getGameColour() {
        return this.game;
    }

    public static boolean isSameColour(Colour lhs, Colour rhs) {
        return Objects.equals(lhs, rhs);
    }

    public static boolean isSameColour(Color lhs, Colour rhs) {
        return Objects.equals(lhs, rhs.bukkit);
    }

    public static boolean isSameColour(String lhs, Colour rhs) {
        return Objects.equals(lhs, rhs.command) || Objects.equals(lhs, rhs.game);
    }

}
