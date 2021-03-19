import java.util.ArrayList;
import java.util.Random;

public class SpelbordKlein implements ISpelbord {
	public static final int MAATX = 4;
	public static final int MAATY = 16;
	private ArrayList<Vakje> vakjes = new ArrayList<Vakje>();

	public SpelbordKlein() {
		for (int i=0; i<MAATX*MAATY; i++) {
			vakjes.add(new Vakje (i));
		}
	}
	
	public void plaatsOpVrijVakje(Karakter k) {
		Vakje v = kiesVrijVakje();
		k.setVakje(v);
	}
	
	private Vakje kiesVrijVakje() {
		Random rand = new Random();
		Vakje v;
		do {
			int vaknr = rand.nextInt(SpelbordKlein.MAATX*SpelbordKlein.MAATY);
			v = vakjes.get(vaknr);
		} while (!v.isLeeg());
		return v;
	}

	public Vakje getVakje(int i) {
		return vakjes.get(i);
	}
}
