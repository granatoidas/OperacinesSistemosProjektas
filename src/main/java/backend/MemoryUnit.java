package backend;

public class MemoryUnit {
	public Byte[][] memory = new Byte[256][256];

	public MemoryUnit() {
		for (int i = 0; i < 256; ++i) {
			for (int j = 0; j < 256; ++j) {
				memory[i][j] = new Byte((byte) 0);
			}
		}

	}
}
