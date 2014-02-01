package joulu.optional;

final class Absent<T> extends Optional<T> {

	@Override
	public T get() {
		throw new IllegalStateException("Absent object has no value");
	}

	@Override
	public boolean isPresent() {
		return false;
	}

	@Override
	public boolean isAbsent() {
		return true;
	}
	
	@Override
	public int hashCode() {
		return 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		return getClass().equals(obj.getClass());
	}
}
