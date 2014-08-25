package supports;

import java.awt.Rectangle;

public interface ToCollision {
	public boolean isVisible();
	public Rectangle getBounds();
	public <K> void collisionAction(K object);
}
