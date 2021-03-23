public class Woordenlijst {

	private String[] woorden;

	public void sorteer() {
		ISorteerStrategy soorteerStrategy = new MergeSort();
		woorden = soorteerStrategy.sorteer(woorden);
	}

}
