package eu.baumistlustig.varo.utils;

import eu.baumistlustig.varo.Varo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer {
    private boolean gameRunning;
    private int gameTime;

    public Timer (boolean gameRunning, int gameTime) {
        this.gameRunning = gameRunning;
        this.gameTime = gameTime;
        run();
    }

    public boolean isRunning() {
        return gameRunning;
    }

    public void setRunning(boolean running) {
        this.gameRunning = running;
    }

    public int getTime() {
        return gameTime;
    }

    public void setTime(int time) {
        this.gameTime = time;
    }

    public void sendActionBar() {
        int playerCount = 0;
        for (Player p : Bukkit.getOnlinePlayers()) {

            if (!isRunning()) {
                continue;
            }

            if (p.getGameMode().equals(GameMode.SURVIVAL)) playerCount += 1;

            ActionBar bar = new ActionBar();

            if (playerCount == 1) {
                bar.sendActionBar(p, ChatColor.GRAY + "Läuft seit: "
                        + ChatColor.BOLD + ChatColor.AQUA + getTime()
                        + ChatColor.RESET + ChatColor.GREEN + " ┃ "
                        + ChatColor.GRAY + "Noch " + ChatColor.GREEN + ChatColor.BOLD + playerCount + ChatColor.RESET + ChatColor.GRAY + " Spieler übrig");
                return;
            }

            bar.sendActionBar(p, ChatColor.GRAY + "Läuft seit: "
                    + ChatColor.BOLD + ChatColor.AQUA + getTime()
                    + ChatColor.RESET + ChatColor.GREEN + " ┃ "
                    + ChatColor.GRAY + "Noch " + ChatColor.GREEN + ChatColor.BOLD + playerCount + ChatColor.RESET + ChatColor.GRAY + " Spieler übrig");
        }
    }

    private void run() {
        new BukkitRunnable() {
            @Override
            public void run() {

                sendActionBar();

                if (!isRunning()) {
                    return;
                }

                setTime(getTime() + 1);
            }
        }.runTaskTimer(Varo.getPlugin(), 20, 20);
    }
}
