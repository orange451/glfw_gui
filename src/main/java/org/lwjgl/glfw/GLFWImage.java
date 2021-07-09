package org.lwjgl.glfw;

import java.nio.ByteBuffer;

import org.lwjgl.system.MemoryStack;

public class GLFWImage {
	static class Buffer {
		public GLFWImage position(int i) {
			return new GLFWImage(null);
		}
	}

	@SuppressWarnings("unused")
	private MemoryStack stack;
	
	private GLFWImage(MemoryStack stack) {
		System.out.println("GLFWImage not yet implemented");
		this.stack = stack;
	}
	
	public static GLFWImage mallocStack(MemoryStack stack) {
		return new GLFWImage(stack);
	}
	
	public static Buffer mallocStack(int i, MemoryStack stack) {
		return new Buffer();
	}

	public GLFWImage set(int width, int height, ByteBuffer image) {
		return this;
	}

	public GLFWImage width(int i) {
		return this;
	}

	public GLFWImage height(int i) {
		return this;
	}

	public void pixels(ByteBuffer image) {
		//return null;
	}
}
