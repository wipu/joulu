package joulu.optional;

import joulu.stronglytyped.Typed;

final class Present<T> extends Optional<T> {
	private Typed<T> value;
	
	public Present(T value) {
		this.value = new PresentTyped<T>(value);
	}
	
	private static class PresentTyped<T> extends Typed<T> {
		public PresentTyped(T value) {
			super(value);
		}		
	}

	@Override
	public T get() {
		return value.value();
	}

	@Override
	public boolean isPresent() {
		return true;
	}

	@Override
	public boolean isAbsent() {
		return false;
	}
	
	@Override
	public int hashCode() {
		return value.value().hashCode();
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
		Present<?> present = (Present<?>)obj;
		return value.value().equals(present.value.value());
	}
}
