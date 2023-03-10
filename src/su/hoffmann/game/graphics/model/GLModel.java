package su.hoffmann.game.graphics.model;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

public class GLModel extends Model {

	private int vertexCount, vao, vertexVBO, indexVBO;

	public GLModel(float[] vertex, int[] index) {
		super(vertex, index);

		vertexCount = index.length;

		FloatBuffer vertexBuffer = MemoryUtil.memAllocFloat(vertex.length).put(vertex).flip();
		IntBuffer indexBuffer = MemoryUtil.memAllocInt(index.length).put(index).flip();

		/* Create VAO */
		vao = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vao);

		/* Create vertex VBO */
		vertexVBO = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexVBO);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertexBuffer, GL15.GL_STATIC_DRAW);

		/* Create index VBO */
		indexVBO = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indexVBO);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL15.GL_STATIC_DRAW);

		/* Finish VAO */
		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
		GL30.glBindVertexArray(0);

		/* Free */
		MemoryUtil.memFree(vertexBuffer);
		MemoryUtil.memFree(indexBuffer);
	}

	@Override
	public void terminate() {
		/* Delete VBO */
		GL20.glDisableVertexAttribArray(0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL15.glDeleteBuffers(vertexVBO);
		GL15.glDeleteBuffers(indexVBO);

		/* Delete VAO */
		GL30.glBindVertexArray(0);
		GL30.glDeleteVertexArrays(vao);
	}

	@Override
	public int getVertexCount() {
		return vertexCount;
	}

	public int getVAO() {
		return vao;
	}

	public int getVBO() {
		return vertexVBO;
	}

}
