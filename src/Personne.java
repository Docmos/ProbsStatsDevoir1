public class Personne {

    private final String id;
    private final int jourDeFete;

    public Personne(String id, int jourDeFete) {
        this.id = id;
        this.jourDeFete = jourDeFete;
    }

    public String getId() {
        return id;
    }

    public int getJourDeFete() {
        return jourDeFete;
    }
}
