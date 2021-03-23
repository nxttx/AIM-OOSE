import java.util.ArrayList;

public class Directory{

	private ArrayList<File> files;

	private IZipper iZipper= new Zip4UAdapter();

	public void createArchive() {
		iZipper.compress(files);
	}

}
