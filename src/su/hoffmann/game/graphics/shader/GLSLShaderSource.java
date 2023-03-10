package su.hoffmann.game.graphics.shader;

public class GLSLShaderSource extends ShaderSource {

	private String content;

	public GLSLShaderSource(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}
}
