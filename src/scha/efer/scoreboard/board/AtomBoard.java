package scha.efer.scoreboard.board;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import scha.efer.scoreboard.ScoreboardPlugin;
import scha.efer.scoreboard.adapter.AssembleAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AtomBoard implements AssembleAdapter {

    @Override
    public String getTitle(Player player) {
        return "§e   §lATOMPVP §e&l5.5 §7" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getServer().getMaxPlayers();
    }

    @Override
    public List<String> getLines(Player player) {
        final List<String> toReturn = new ArrayList<>();

        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy/HH:mm");

        if (player.getInventory().contains(Material.ICE)) {
            toReturn.add("&b&lDONUYORSUN USTA!!");
        }
        toReturn.add(" ");
        if (player.getWorld().getName().equalsIgnoreCase("uzayarena")) {
            toReturn.add("&fDünya: &eTitanya Limanı");
        } else if (player.getWorld().getName().equalsIgnoreCase("takimlipandora")) {
            toReturn.add("&fDünya: &eTakımlı Pandora");
        } else if (player.getWorld().getName().equalsIgnoreCase("duello")) {
            toReturn.add("&fDünya: &eDüello");
        } else {
            toReturn.add("&fDünya: &e" + player.getWorld().getName());
        }
        toReturn.add("&fKinas: &e31");
        toReturn.add(" ");
        toReturn.add("&7" + format.format(now));
        toReturn.add("&e   atomcraft.pw");
        toReturn.add(" ");

        return toReturn;
    }

}