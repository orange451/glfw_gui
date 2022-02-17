package org.lwjgl.nanovg;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import org.mini.nanovg.Nanovg;

public class NanoVG extends Nanovg {

	public static void nvgBeginPath(long nvg) {
		Nanovg.nvgBeginPath(nvg);
	}

	public static void nvgRect(long nvg, float x, float y, float width, float height) {
		Nanovg.nvgRect(nvg, x, y, width, height);
	}

	public static void nvgFill(long nvg) {
		Nanovg.nvgFill(nvg);
	}

	public static void nvgImageSize(long vg, int nvgImage, int[] w, int[] h) {
		Nanovg.nvgImageSize(vg, nvgImage, w, h);
	}

	public static void nvgRoundedRectVarying(long nvg, float x, float y, float width, float height, float c1, float c2, float c3, float c4) {
		Nanovg.nvgRoundedRectVarying(nvg, x, y, width, height, c1, c2, c3, c4);
	}

	public static void nvgClosePath(long nvg) {
		Nanovg.nvgClosePath(nvg);
	}

	public static void nvgMoveTo(long vg, float sx, float sy) {
		Nanovg.nvgMoveTo(vg, sx, sy);
	}

	public static void nvgBezierTo(long vg, float c1x, float c1y, float c2x, float c2y, float ex, float ey) {
		Nanovg.nvgBezierTo(vg, c1x, c1y, c2x, c2y, ex, ey);
	}

	public static void nvgStroke(long vg) {
		Nanovg.nvgStroke(vg);
	}

	public static void nvgStrokeWidth(long vg, float strokeThickness) {
		Nanovg.nvgStrokeWidth(vg, strokeThickness);
	}

	public static void nvgStrokeColor(long vg, NVGColor color) {
		Nanovg.nvgStrokeColor(vg, color.get());
	}

	public static void nvgFillPaint(long nvg, NVGPaint paint) {
		Nanovg.nvgFillPaint(nvg, paint.get());
	}

	public static NVGPaint nvgLinearGradient(long nvg, float startX, float startY, float endX, float endY, NVGColor color1, NVGColor color2, NVGPaint paint) {
		byte[] data = Nanovg.nvgLinearGradient(nvg, startX, startY, endX, endY, color1.get(), color2.get());
		paint.set(data);
		return paint;
	}

	public static NVGPaint nvgImagePattern(long vg, float x, float y, float width, float height, float angle, int b, float alpha, NVGPaint paint) {
		byte[] data = Nanovg.nvgImagePattern(vg, x, y, width, height, angle, b, alpha);
		paint.set(data);
		return paint;
	}

	public static void nvgFillColor(long nvg, NVGColor color) {
		Nanovg.nvgFillColor(nvg, color.get());
	}

	public static void nvgLineTo(long nvg, float px, float py) {
		Nanovg.nvgLineTo(nvg, px, py);
	}

	public static void nvgRoundedRect(long vg, float x, float y, float w, float h, float cornerRadius) {
		Nanovg.nvgRoundedRect(vg, x, y, w, h, cornerRadius);
	}

	public static void nvgCircle(long nvg, float x, float y, float radius) {
		Nanovg.nvgCircle(nvg, x, y, radius);
	}

	public static void nvgDeleteImage(long nvg, int nanoImage) {
		Nanovg.nvgDeleteImage(nvg, nanoImage);
	}

	public static void nvgSave(long nvg) {
		Nanovg.nvgSave(nvg);
	}

	public static void nvgEndFrame(long nvg) {
		Nanovg.nvgEndFrame(nvg);
	}

	public static void nvgRestore(long nvg) {
		Nanovg.nvgRestore(nvg);
	}

	public static int nvgCreateImageMem(long nvg, int i, ByteBuffer data) {
		return Nanovg.nvgCreateImageMem(nvg, i, data.array(), data.array().length);
	}

	public static int nvgCreateFontMem(long nvg, CharSequence fontName, ByteBuffer fontData, int i) {
		return Nanovg.nvgCreateFontMem(nvg, fontName.toString().getBytes(), fontData.array(), i, i);
	}

	public static void nvgScissor(long nvg, float x, float y, float w, float h) {
		Nanovg.nvgScissor(nvg, x, y, w, h);
	}

	public static void nvgBeginFrame(long nvg, float windowWidth, float windowHeight, float devicePixelRatio) {
		Nanovg.nvgBeginFrame(nvg, (int)windowWidth, (int)windowHeight, devicePixelRatio);
	}

	public static void nvgTranslate(long vg, float x, float y) {
		Nanovg.nvgTranslate(vg, x, y);
	}

	public static void nvgFontSize(long vg, float fontSize) {
		Nanovg.nvgFontSize(vg, fontSize);
	}

	public static void nvgFontFace(long vg, CharSequence font) {
		Nanovg.nvgFontFace(vg, font.toString().getBytes());
	}

	public static float nvgText(long vg, float xx, float yy, CharSequence text) {
		byte[] dat = toUtf8(text.toString());
		return Nanovg.nvgTextJni(vg, xx, yy, dat, 0, dat.length);
	}

	public static float nvgText(long vg, float xx, float yy, ByteBuffer text) {
		byte[] dat = text.array();
		return Nanovg.nvgTextJni(vg, xx, yy, dat, 0, dat.length);
	}
	
    private static byte[] toUtf8(String s) {
        if (s == null) {
            return null;
        }
        int pos = s.lastIndexOf('\000');
        if (pos >= 0 && pos == s.length() - 1) {

        } else {
            s += '\000';
        }
        byte[] barr = null;
        try {
            barr = s.getBytes("utf-8");
        } catch (UnsupportedEncodingException ex) {
        }
        return barr;
    }

	public static void nnvgBeginPath(long vg) {
		Nanovg.nvgBeginPath(vg);
	}

	public static void nvgShapeAntiAlias(long nvg, boolean b) {
		Nanovg.nvgShapeAntiAlias(nvg, b?1:0);
	}

	public static void nnvgFill(long nvg) {
		Nanovg.nvgFill(nvg);
	}

	public static NVGPaint nvgBoxGradient(long nvg, float xx, float yy, float ww, float hh, float r, float f, NVGColor nvg2, NVGColor nvg3, NVGPaint paint) {
		byte[] data = Nanovg.nvgBoxGradient(nvg, xx, yy, ww, hh, r, f, nvg2.get(), nvg3.get());
		paint.set(data);
		return paint;
	}

	public static void nnvgRect(long nvg, float f, float g, float h, float i) {
		Nanovg.nvgRect(nvg, f, g, h, i);
	}

	public static void nnvgClosePath(long nvg) {
		Nanovg.nvgClosePath(nvg);
	}

	public static float nvgTextBounds(long nvg, float i, float j, CharSequence string, float[] bounds) {
        byte[] dat = toUtf8(string.toString());
        return Nanovg.nvgTextBoundsJni(nvg, i, j, dat, 0, dat.length, bounds);
	}

	public static float nvgTextBounds(long nvg, float i, float j, ByteBuffer buffer, float[] bounds) {
		byte[] dat = buffer.array();
        return Nanovg.nvgTextBoundsJni(nvg, i, j, dat, 0, dat.length, bounds);
	}

	public static void nvgTextBoxBounds(long nvg, float i, float j, float breakRowWidth, CharSequence string, float[] bounds) {
		byte[] dat = toUtf8(string.toString());
        Nanovg.nvgTextBoxBoundsJni(nvg, i, j, breakRowWidth, dat, 0, dat.length, bounds);
	}

	public static void nvgTextBoxBounds(long nvg, float i, float j, float breakRowWidth, ByteBuffer buffer, float[] bounds) {
		byte[] dat = buffer.array();
        Nanovg.nvgTextBoxBoundsJni(nvg, i, j, breakRowWidth, dat, 0, dat.length, bounds);
	}

	public static int nvgTextGlyphPositions(long nvg, float i, float j, CharSequence string, NVGGlyphPosition.Buffer positions) {
		long glyphsHandle = nvgCreateNVGglyphPosition(positions.size());
		byte[] text_arr = toUtf8(string.toString());
		int char_count = Nanovg.nvgTextGlyphPositionsJni(nvg, i, j, text_arr, 0, text_arr.length, glyphsHandle, positions.size());
		
		float maxX = 0;
		for (int k = 0; k < char_count; k++) {
			float x0 = nvgNVGglyphPosition_x(glyphsHandle, k);
			float x1 = x0 + 16; // TODO get implementation for glyph width
			maxX = Math.max(maxX, x1);
			
			positions.put(new NVGGlyphPosition(x0, x1));
		}
		
		positions.flip();
		return (int) maxX;
	}
}
