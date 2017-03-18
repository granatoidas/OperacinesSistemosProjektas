import backend.MissingLink;

public class EntryPoint {

	public static void main(String[] args) {
		Byte b = new Byte((byte)-128);
		b--;
		System.out.println(b);
		MissingLink mainController = new MissingLink();
	}

}
