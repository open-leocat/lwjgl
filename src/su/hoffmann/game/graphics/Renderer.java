package su.hoffmann.game.graphics;

import su.hoffmann.game.Game;
import su.hoffmann.game.Log;
import su.hoffmann.game.Settings;
import su.hoffmann.game.graphics.model.Model;

public abstract class Renderer {

	protected final Game game;
	protected final Settings settings;
	protected final Log log;

	public Renderer(Game game) {
		this.game = game;
		this.settings = game.getSettings();
		this.log = game.getLog();
	}

	public abstract void initialize();

	public abstract void render(Model model);

	public abstract void viewport();

	public abstract void clear();

}
