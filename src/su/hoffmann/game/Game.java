package su.hoffmann.game;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.lwjgl.opengl.GL11;

import su.hoffmann.game.graphics.Display;
import su.hoffmann.game.graphics.GLRenderer;
import su.hoffmann.game.graphics.Renderer;
import su.hoffmann.game.graphics.model.GLModel;
import su.hoffmann.game.graphics.model.Model;
import su.hoffmann.game.graphics.shader.GLSLShaderSource;
import su.hoffmann.game.graphics.shader.GLShader;
import su.hoffmann.game.graphics.shader.GLShaderProgram;
import su.hoffmann.game.graphics.shader.Shader;
import su.hoffmann.game.graphics.shader.ShaderProgram;
import su.hoffmann.game.graphics.shader.ShaderType;

public class Game {

	private Settings settings;
	private Log log;

	private Display display;
	private Renderer renderer;

	private Model model;
	private Shader vertexShader, fragmentShader;
	private ShaderProgram shaderProgram;

	public Game() {
		settings = new Settings(1920, 1080);
		log = new Log();

		display = new Display(this);
		display.initialize();

		renderer = new GLRenderer(this);
		renderer.initialize();

		model = new GLModel(
				new float[] { 
						-0.5f, 0.5f, 0.0f, -0.5f, -0.5f, 0.0f, 0.5f, -0.5f, 0.0f, 0.5f, 0.5f, 0.0f, 
				},
				new int[] {
						0, 1, 3, 3, 1, 2, 
				});

		
		
		vertexShader = new GLShader(this, ShaderType.VERTEX);
		fragmentShader = new GLShader(this, ShaderType.FRAGMENT);
		
		try {
			vertexShader.load(new GLSLShaderSource(Files.readString(Paths.get(Game.class.getResource("/shader/example.vert").toURI()))));
			fragmentShader.load(new GLSLShaderSource(Files.readString(Paths.get(Game.class.getResource("/shader/example.frag").toURI()))));
		} catch(Exception e) { e.printStackTrace(); }

		shaderProgram = new GLShaderProgram(this);
		shaderProgram.attach(vertexShader);
		shaderProgram.attach(fragmentShader);
		shaderProgram.link();

		vertexShader.terminate();
		fragmentShader.terminate();
		
		while (!display.shouldClose()) {
			display.update();

			renderer.clear();
			
			shaderProgram.enable();
			renderer.render(model);
			shaderProgram.disable();
		}

		shaderProgram.terminate();
		
		model.terminate();
		
		display.terminate();
	}

	public static void main(String[] args) {
		new Game();
	}

	public String getTitle() {
		return "Game";
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}
}
