package joulu.optional;

public final class Optional<T> {

	private static final Optional<?> ABSENT = new Optional<Object>(null);

	private final T value;

	public Optional(T value) {
		this.value = value;
	}

	public static <T> Optional<T> of(T value) {
		if (value == null) {
			return absent();
		}
		return new Optional<T>(value);
	}

	@SuppressWarnings("unchecked")
	public static <T> Optional<T> absent() {
		return (Optional<T>) ABSENT;
	}

	public T get() {
		if (isAbsent()) {
			throw new NullPointerException("Absent object has no value");
		} else {
			return value;
		}
	}

	public boolean isPresent() {
		return !isAbsent();
	}

	public boolean isAbsent() {
		return value == null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Optional<?> other = (Optional<?>) obj;
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}

}
