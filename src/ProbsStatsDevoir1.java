import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProbsStatsDevoir1 {

    public static void main(String[] args) {
        final var nombreDeSimulations = 500000;
        final var nombreDePersonnesDansLeGroupe = 30;
        final var nombrePersonnesAvecMemeJourDeFete = 3;

        final var groupesAvecNPersonnesAyantMemeJourDeFete = new ArrayList<Boolean>();
        for (int i = 0; i < nombreDeSimulations; i++) {
            final var groupe = genererGroupeDePersonnes(nombreDePersonnesDansLeGroupe);
            groupesAvecNPersonnesAyantMemeJourDeFete.add(aNPersonnesAvecMemeJourDeFete(groupe, nombrePersonnesAvecMemeJourDeFete));
        }

        final var nombreDeResultatsPositifs = groupesAvecNPersonnesAyantMemeJourDeFete
                .stream().filter(r -> r).collect(Collectors.toList()).size();

        final double pourcentage = (double)(100 * nombreDeResultatsPositifs) / nombreDeSimulations;

        System.out.println("Nombre de personnes dans le groupe: " + nombreDePersonnesDansLeGroupe);
        System.out.println("Nombre de personnes dans le groupe ayant le meme jour de fete: " + nombrePersonnesAvecMemeJourDeFete);
        System.out.println("Nombre de simulations: " + nombreDeSimulations + "\n");
        System.out.println("Nombre de groupes ayant " + nombrePersonnesAvecMemeJourDeFete + " personnes avec le meme jour de fete: " + nombreDeResultatsPositifs);
        System.out.println("Probabilite de resultat positif: " + pourcentage + "%");
    }

    private static List<Personne> genererGroupeDePersonnes(int grandeurDuGroupe) {
        final var groupe = new ArrayList<Personne>();
        for (int i = 0; i < grandeurDuGroupe; i++) {
            groupe.add(new Personne(UUID.randomUUID().toString(), new Random().nextInt(365 - 1 ) + 1));
        }
        return groupe;
    }

    private static boolean aNPersonnesAvecMemeJourDeFete(List<Personne> groupe, int nombrePersonnesAvecMemeJourDeFete) {
        var aNPersonnesAvecMemeJourDeFete = false;
        final var groupeCopie = new ArrayList<>(groupe);
        for (final var personne : groupe) {
            var personnesAvecMemeJourDeFete = 1;
            groupeCopie.removeIf(p -> p.getId().endsWith(personne.getId()));
            for (final var autrePersonne : groupeCopie) {
                if (personne.getJourDeFete() == autrePersonne.getJourDeFete()) {
                    personnesAvecMemeJourDeFete++;
                }
                if (personnesAvecMemeJourDeFete == nombrePersonnesAvecMemeJourDeFete) {
                    aNPersonnesAvecMemeJourDeFete = true;
                    break;
                }
            }
            if (personnesAvecMemeJourDeFete == nombrePersonnesAvecMemeJourDeFete) {
                break;
            }
        }
        return aNPersonnesAvecMemeJourDeFete;
    }
}
