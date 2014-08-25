package supports;

public interface ToMove<K, E> extends ToCollision{
	boolean isVisible();
	boolean move();
}
