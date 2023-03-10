package su.hoffmann.game.graphics.shader;

import su.hoffmann.game.Game;
import su.hoffmann.game.Log;

public abstract class Shader {

	protected final Game game;
	protected final Log log;
	protected final ShaderType type;

	public Shader(Game game, ShaderType type) {
		this.game = game;
		this.log = game.getLog();
		this.type = type;
	}

	public abstract void load(ShaderSource source);

	public abstract void terminate();

}
