package su.hoffmann.game.graphics.shader;

import su.hoffmann.game.Game;
import su.hoffmann.game.Log;

public abstract class ShaderProgram {

	protected final Game game;
	protected final Log log;

	public ShaderProgram(Game game) {
		this.game = game;
		this.log = game.getLog();
	}

	public abstract void attach(Shader shader);

	public abstract void link();
	public abstract void terminate();
	
	public abstract void enable();
	public abstract void disable();

	public abstract void setUniform(String name, int value);
	public abstract void setUniform(String name, float value);
}
