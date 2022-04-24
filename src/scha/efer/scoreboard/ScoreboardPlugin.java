package scha.efer.scoreboard;

import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import scha.efer.scoreboard.adapter.Assemble;
import scha.efer.scoreboard.adapter.AssembleStyle;
import scha.efer.scoreboard.board.AtomBoard;
import scha.efer.scoreboard.vault.Chat;
import scha.efer.scoreboard.vault.Economy;
import scha.efer.scoreboard.vault.Permission;

import java.util.logging.Logger;

public class ScoreboardPlugin extends JavaPlugin implements Listener {

    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ = null;
    private static Permission perms = null;
    private static Chat chat = null;


    public void onEnable() {
        // instance'i baslatmamiz gerekiyor
        Assemble assemble = new Assemble(this, new AtomBoard());
        // crash problemini fixlemek icin bir delay belirlemek gerek (tick per second gibi)
        assemble.setTicks(2);
        // stil secimi (-1 den baslar veya 0 dan veya 1)
        assemble.setAssembleStyle(AssembleStyle.ALLAH_TEKTIR);

        if (!setupEconomy() ) {
            log.severe(String.format("[%s] Vault bulunamadi!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupEconomy();
        setupPermissions();
        setupChat();
    }

    public void onDisable() {

    }
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static Permission getPermissions() {
        return perms;
    }

    public static Chat getChat() {
        return chat;
    }

}
