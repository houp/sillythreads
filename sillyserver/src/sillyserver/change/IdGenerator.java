package sillyserver.change;

public class IdGenerator {
	int id = 0;

	private static final IdGenerator instance = new IdGenerator();

	private IdGenerator() {
	}

	public static IdGenerator getInstance() {
		return instance;
	}

	public synchronized int getNewId() {
		id++;
		return id;
	}

}
