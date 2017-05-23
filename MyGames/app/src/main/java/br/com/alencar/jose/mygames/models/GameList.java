package br.com.alencar.jose.mygames.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by jose on 22/05/17.
 */

public class GameList {
    private static final List<Game> games = new ArrayList<>(Arrays.asList(
            new Game("Gravity Rush 2", new GregorianCalendar(2017, Calendar.JANUARY, 18), "https://i1.wp.com/mestregeek.com.br/wp-content/uploads/2017/01/Gravity-Rush-2.jpg?w=696&ssl=1"),
            new Game("Kingdom Hearts HD 2.8 Final Chapter Prologue", new GregorianCalendar(2017, Calendar.JANUARY, 24), "https://i0.wp.com/mestregeek.com.br/wp-content/uploads/2017/01/Kingdom-Hearts-HD-2.8-Final-Chapter-Prologue.jpg?w=696&ssl=1"),
            new Game("Tales of Berseria", new GregorianCalendar(2017, Calendar.JANUARY, 27), "https://i2.wp.com/mestregeek.com.br/wp-content/uploads/2017/01/Tales-of-Berseria.jpg?w=696&ssl=1"),
            new Game("Yakuza 0", new GregorianCalendar(2017, Calendar.JANUARY, 24), "https://i2.wp.com/mestregeek.com.br/wp-content/uploads/2017/01/Yakuza-0.jpg?w=696&ssl=1"),
            new Game("Double Dragon IV", new GregorianCalendar(2017, Calendar.JANUARY, 29), "https://i0.wp.com/mestregeek.com.br/wp-content/uploads/2017/01/Double-Dragon-IV.png?w=696&ssl=1"),
            new Game("NiOh", new GregorianCalendar(2017, Calendar.FEBRUARY, 8), "https://i0.wp.com/mestregeek.com.br/wp-content/uploads/2017/01/NiOh.jpg?w=696&ssl=1"),
            new Game("Horizon: Zero Dawn", new GregorianCalendar(2017, Calendar.MARCH, 1), "https://i1.wp.com/mestregeek.com.br/wp-content/uploads/2017/01/Horizon-Zero-Dawn.jpg?w=696&ssl=1"),
            new Game("Nier Automata", new GregorianCalendar(2017, Calendar.MARCH, 10), "https://i0.wp.com/mestregeek.com.br/wp-content/uploads/2017/01/Nier-Automata.jpg?w=696&ssl=1"),
            new Game("Persona 5", new GregorianCalendar(2017, Calendar.APRIL, 4), "https://i2.wp.com/mestregeek.com.br/wp-content/uploads/2017/01/Persona-5.jpg?w=696&ssl=1"),
            new Game("Dragon Quest Heroes II", new GregorianCalendar(2017, Calendar.APRIL, 28), "https://i1.wp.com/mestregeek.com.br/wp-content/uploads/2017/01/Dragon-Quest-Heroes-II.jpg?w=696&ssl=1"),
            new Game("Ace Combat 7", null, "https://i0.wp.com/mestregeek.com.br/wp-content/uploads/2017/01/Ace-Combat-7.jpg?w=696&ssl=1"),
            new Game("Crash Bandicoot: N.Sane Trilogy", null, "https://i2.wp.com/mestregeek.com.br/wp-content/uploads/2017/01/Crash-Bandicoot-N.Sane-Trilogy.jpg?w=696&ssl=1"),
            new Game("Farpoint", null, "https://i0.wp.com/mestregeek.com.br/wp-content/uploads/2017/01/Farpoint.jpg?w=696&ssl=1"),
            new Game("Gran Turismo Sport", null, "https://i2.wp.com/mestregeek.com.br/wp-content/uploads/2017/01/Gran-Turismo-Sport.jpg?w=696&ssl=1"),
            new Game("Hellblade: Senua’s Sacrifice", null, "https://i0.wp.com/mestregeek.com.br/wp-content/uploads/2017/01/Hellblade-Senuas-Sacrifice.jpg?w=696&ssl=1"),
            new Game("Knack 2", null, "https://i0.wp.com/mestregeek.com.br/wp-content/uploads/2017/01/Knack-2.jpg?w=696&ssl=1"),
            new Game("Ni no Kuni II: Revenant Kingdom", null, "https://i1.wp.com/mestregeek.com.br/wp-content/uploads/2017/01/Ni-no-Kuni-II-Revenant-Kingdom.jpg?w=696&ssl=1"),
            new Game("Pyre", null, "https://i1.wp.com/mestregeek.com.br/wp-content/uploads/2017/01/Pyre.jpg?w=696&ssl=1"),
            new Game("Shenmue III", null, "https://i1.wp.com/mestregeek.com.br/wp-content/uploads/2017/01/Shenmue-III.jpeg?w=696&ssl=1"),
            new Game("Uncharted: The Lost Legacy", null, "https://i1.wp.com/mestregeek.com.br/wp-content/uploads/2017/01/Uncharted-The-Lost-Legacy.png?w=696&ssl=1"),
            new Game("Wipeout Omega Collection", null, "https://i2.wp.com/mestregeek.com.br/wp-content/uploads/2017/01/Wipeout-Omega-Collection.jpg?w=696&ssl=1")
    ));

    public static List<Game> getAll() {
        return games;
    }

    public static void remove(int position) {
        games.remove(position);
    }

    public static void update(Game game) {
        for (Game g : games) {
            if (g.getNome().equals(game.getNome())) {
                g.setFavorito(game.isFavorito());
                break;
            }
        }
    }

    public String getCSV() {
        StringBuilder builder = new StringBuilder();

        for (Game game : games) {
            builder.append(game.getCSV() + "\n");
        }

        return builder.toString();
    }
}
