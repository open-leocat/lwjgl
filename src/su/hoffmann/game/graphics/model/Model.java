package su.hoffmann.game.graphics.model;

public abstract class Model {

	public Model(float[] vertex, int[] index) {
	}

	public abstract void terminate();

	public abstract int getVertexCount();

}
