package org.lwjgl.glfm;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCharCallback;
import org.lwjgl.glfw.GLFWCharCallbackI;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.mini.gl.GLCallbacks;
import org.mini.glfm.Glfm;

public class GLFM extends Glfm {
	
    public static void glfmSetDisplayConfig(long display,
            int preferredAPI,
            int colorFormat,
            int depthFormat,
            int stencilFormat,
            int multisample) {
		Glfm.glfmSetDisplayConfig(display, preferredAPI, colorFormat, depthFormat, stencilFormat, multisample);
	}
    
	public static GLFWCharCallback glfmSetCharCallback(long window, GLFWCharCallbackI callback) {
		return GLCallbacks.charCallbacks.put(window, GLFWCharCallback.createSafe(callback));
	}
	
	public static GLFWKeyCallback glfmSetKeyCallback(long window, GLFWKeyCallbackI callback) {
		return GLCallbacks.keyCallbacks.put(window, GLFWKeyCallback.createSafe(callback));
	}
	
	public static GLFMRenderFuncCallback glfmSetRenderFuncCallback(long window, GLFMRenderFuncCallbackI callback) {
		return GLCallbacks.renderFuncCallbacks.put(window, GLFMRenderFuncCallback.createSafe(callback));
	}
	
	public static GLFMTouchCallback glfmSetTouchCallback(long window, GLFMTouchCallbackI callback) {
		return GLCallbacks.touchCallbacks.put(window, GLFMTouchCallback.createSafe(callback));
	}
	
    public static long glfwCreateWindow(int width, int height, CharSequence title, long monitor, long share) {
        return GLFW.glfwCreateWindow(width, height, title.toString(), monitor, share);
    }
}
