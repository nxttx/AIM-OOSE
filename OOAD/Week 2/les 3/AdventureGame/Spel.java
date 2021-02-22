import java.util.ArrayList;

public class Spel {
	private ArrayList<Karakter> karakters = new ArrayList<Karakter>();
	private Spelbord bord = new Spelbord();
	
	public void meldAan (String naamKarakter) {
		Karakter k = new Karakter(naamKarakter);
		karakters.add(k);
		bord.plaatsOpVrijVakje(k);
	}

	public Karakter getKarakter (String naam) {
		for (Karakter k : karakters) {
			if (naam.equals (k.getNaam())) {
				return k;
			}
		};
		return null;
	}

	public void telepoorteer(String naamKarakter, int vaknr){
		Karakter karakter = getKarakter(naamKarakter);
		bord.setCustomVakje(vaknr, karakter);
		karakter.setEnergie(karakter.getEnergie()-20);
	}

	public void wisselVakje(String naamKarakter, int vaknr){
		Karakter karakter = getKarakter(naamKarakter);
		if(karakter.getEnergie()>=20){
			Vakje vak = bord.getVakje(vaknr);
			if(vak.isLeeg()){
				System.out.println(naamKarakter+": Er staat niemand op dit vakje");
			}else{
				for (Karakter oudKarakter : karakters) {
					if (vak == oudKarakter.getVakje()) {
						Vakje karakterVakje = karakter.getVakje();
						karakter.setVakje(vak);
						oudKarakter.setVakje(karakterVakje);
					}
				};
				karakter.setEnergie(karakter.getEnergie()-20);
			}
		}else{
			System.out.println(naamKarakter+" heeft niet genboeg energie voor deze actie.");
		}

	}
}
