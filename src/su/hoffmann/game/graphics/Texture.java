package su.hoffmann.game.graphics;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import su.hoffmann.game.Log;

public class Texture {

	public Texture() {
		
	}
	
	public static ByteBuffer load(Log log, String name) {
		try(MemoryStack stack = MemoryStack.stackPush()) {
			IntBuffer width = stack.mallocInt(1), height = stack.mallocInt(1), compression = stack.mallocInt(1);
			
			if(!STBImage.stbi_info_from_memory(null, width, height, compression)) {
				log.error("Failed to read texture information.");
				return null;
			}
			
			log.note("Width: " + width.get(0));
		}
		
		return null;
	}
	
}
