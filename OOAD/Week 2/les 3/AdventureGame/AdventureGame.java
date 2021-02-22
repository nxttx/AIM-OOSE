
public class AdventureGame {
	public static void main (String[] args) { 
		Spel spel = new Spel();
		spel.meldAan("Bob");
		spel.meldAan("Kim");
		spel.telepoorteer("Bob",12 );
		spel.wisselVakje("Kim",12 );
	}
}
