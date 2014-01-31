package joulu.stronglytyped;

public abstract class Typed<T> {

	private final T value;

	public Typed(T value) {
		if (value == null) {
			throw new NullPointerException("Value must not be null");
		}
		this.value = value;
	}

	public final T value() {
		return value;
	}

	@Override
	public final String toString() {
		return value().toString();
	}

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Typed<?> other = (Typed<?>) obj;
		return value.equals(other.value);
	}

}
