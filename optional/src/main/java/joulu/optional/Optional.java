package joulu.optional;

public abstract class Optional<T> {

	public static <T> Optional<T> of(T value) {
		return new Present<T>(value);
	}

	public abstract T get();

	public static <T> Optional<T> absent() {
		return new Absent<T>();
	}

	public abstract boolean isPresent();

	public abstract boolean isAbsent();

}
