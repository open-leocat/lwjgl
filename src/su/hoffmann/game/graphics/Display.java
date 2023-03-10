package su.hoffmann.game.graphics;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.system.MemoryUtil;

import su.hoffmann.game.Game;
import su.hoffmann.game.Log;
import su.hoffmann.game.Settings;

public class Display {

	private final Game game;
	private final Settings settings;
	private final Log log;

	private long window;

	private class DisplayErrorCallback extends GLFWErrorCallback {
		@Override
		public void invoke(int error, long description) {
			log.error("GLFW error: " + MemoryUtil.memUTF8(description));
			terminate();
		}
	}

	public Display(Game game) {
		this.game = game;
		this.settings = game.getSettings();
		this.log = game.getLog();
	}

	public void initialize() {
		try (GLFWErrorCallback callback = new DisplayErrorCallback()) {
			GLFW.glfwSetErrorCallback(callback);

			if (!GLFW.glfwInit()) {
				log.error("Failed to initialize GLFW");
				terminate();
				return;
			}

			log.note("LWJGL version: " + Version.getVersion());
			log.note("GLFW version: " + GLFW.glfwGetVersionString());

			createWindow();
		}
	}

	private void createWindow() {
		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);
//		GLFW.glfwWindowHint(GLFW.GLFW_SAMPLES, 4); /* Anti-aliasing */

		window = GLFW.glfwCreateWindow(settings.getWidth(), settings.getHeight(), game.getTitle(), 0, 0);
		if (window == 0) {
			log.error("Failed to create a GLFW window");
			terminate();
			return;
		}

		GLFW.glfwMakeContextCurrent(window);
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 4);
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 5);
		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GLFW.GLFW_TRUE);

//		GLFW.glfwSwapInterval(1); /* V-Sync */
	}

	public void terminate() {
		GLFW.glfwDestroyWindow(window);
		GLFW.glfwTerminate();
	}

	public void update() {
		GLFW.glfwSwapBuffers(window);
		GLFW.glfwPollEvents();
	}

	public boolean shouldClose() {
		return GLFW.glfwWindowShouldClose(window);
	}

}
