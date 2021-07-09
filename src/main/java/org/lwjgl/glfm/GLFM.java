package org.lwjgl.glfm;

import org.lwjgl.glfw.GLFWCharCallbackI;
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
    
	public static GLFWCharCallbackI glfmSetCharCallback(long window, GLFWCharCallbackI callback) {
		return GLCallbacks.charCallbacks.put(window, callback);
	}
	
	public static GLFWKeyCallbackI glfmSetKeyCallback(long window, GLFWKeyCallbackI callback) {
		return GLCallbacks.keyCallbacks.put(window, callback);
	}
	
	public static GLFMRenderFuncCallbackI glfmSetRenderFuncCallback(long window, GLFMRenderFuncCallbackI callback) {
		return GLCallbacks.renderFuncCallbacks.put(window, callback);
	}
}
