import java.util.ArrayList;

public class Zip4UAdapter implements IZipper {


	@Override
	public void compress(ArrayList<File> files) {
		Zip4U zip4u = new Zip4U();
		zip4u.zipfiles(files);
		
	}

	

}
