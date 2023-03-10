package su.hoffmann.game.graphics;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import su.hoffmann.game.Game;
import su.hoffmann.game.graphics.model.GLModel;
import su.hoffmann.game.graphics.model.Model;

public class GLRenderer extends Renderer {

	public GLRenderer(Game game) {
		super(game);
	}

	@Override
	public void initialize() {
		GL.createCapabilities();

		log.note("OpenGL version: " + GL11.glGetString(GL11.GL_VERSION));
		log.note("GLSL version: " + GL11.glGetString(GL20.GL_SHADING_LANGUAGE_VERSION));

		GL11.glClearColor(0, 0, 0, 1);
	}

	@Override
	public void render(Model model) {
		GLModel nativeModel = (GLModel) model;

		GL30.glBindVertexArray(nativeModel.getVAO());
		GL20.glEnableVertexAttribArray(0);

		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);

		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}

	@Override
	public void viewport() {
		GL11.glViewport(0, 0, settings.getWidth(), settings.getHeight());
	}

	@Override
	public void clear() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
}
