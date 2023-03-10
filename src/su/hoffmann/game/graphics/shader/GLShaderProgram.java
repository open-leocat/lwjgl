package su.hoffmann.game.graphics.shader;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import su.hoffmann.game.Game;

public class GLShaderProgram extends ShaderProgram {

	private final int program;

	public GLShaderProgram(Game game) {
		super(game);

		program = GL20.glCreateProgram();
	}

	@Override
	public void attach(Shader shader) {
		GLShader nativeShader = (GLShader) shader;
		GL20.glAttachShader(program, nativeShader.getShader());
	}

	@Override
	public void link() {
		GL20.glLinkProgram(program);
		GL20.glValidateProgram(program);

		if (GL20.glGetProgrami(program, GL20.GL_LINK_STATUS) != GL11.GL_TRUE) {
			log.error("Shader Linkage Error: " + GL20.glGetShaderInfoLog(program));
		}
		
		bindAttribute(0, "position");
	}

	private void bindAttribute(int index, String name) {
		GL20.glBindAttribLocation(program, index, name);
	}

	@Override
	public void terminate() {
		GL20.glUseProgram(0);
		GL20.glDeleteProgram(program);
	}

	@Override
	public void enable() {
		GL20.glUseProgram(program);
	}

	@Override
	public void disable() {
		GL20.glUseProgram(0);
	}

	@Override
	public void setUniform(String name, int value) {
		GL20.glUniform1i(GL20.glGetUniformLocation(program, name), value);
	}

	@Override
	public void setUniform(String name, float value) {
		GL20.glUniform1f(GL20.glGetUniformLocation(program, name), value);
	}

}
