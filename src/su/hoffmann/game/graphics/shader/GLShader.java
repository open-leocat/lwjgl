package su.hoffmann.game.graphics.shader;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import su.hoffmann.game.Game;

public class GLShader extends Shader {

	private final int shader;

	public GLShader(Game game, ShaderType type) {
		super(game, type);

		shader = GL20.glCreateShader(switch (type) {
		case VERTEX -> GL20.GL_VERTEX_SHADER;
		case FRAGMENT -> GL20.GL_FRAGMENT_SHADER;
		});
	}

	@Override
	public void load(ShaderSource source) {
		GLSLShaderSource nativeSource = (GLSLShaderSource) source;

		GL20.glShaderSource(shader, nativeSource.getContent());
		GL20.glCompileShader(shader);

		if (GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS) != GL11.GL_TRUE) {
			log.error("Shader Compilation Error: " + GL20.glGetShaderInfoLog(shader));
		}
	}

	@Override
	public void terminate() {
		GL20.glDeleteShader(shader);
	}

	public int getShader() {
		return shader;
	}
}
