package ex;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

public class GraphicAdapter extends Graphics{
	@Override
	public void clearRect(int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setClip(int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		
	}public GraphicAdapter() {
		// TODO Auto-generated constructor stub
	}@Override
	public void clipRect(int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		
	}@Override
	public void copyArea(int x, int y, int width, int height, int dx, int dy) {
		// TODO Auto-generated method stub
		
	}@Override
	public Graphics create() {
		// TODO Auto-generated method stub
		return null;
	}@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}@Override
	public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		// TODO Auto-generated method stub
		
	}@Override
	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
			Color bgcolor, ImageObserver observer) {
		// TODO Auto-generated method stub
		return false;
	}@Override
	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
			ImageObserver observer) {
		// TODO Auto-generated method stub
		return false;
	}@Override
	public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
		// TODO Auto-generated method stub
		return false;
	}@Override
	public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
		// TODO Auto-generated method stub
		return false;
	}@Override
	public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
		// TODO Auto-generated method stub
		return false;
	}@Override
	public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
		// TODO Auto-generated method stub
		return false;
	}@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		
	}@Override
	public void drawOval(int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		
	}@Override
	public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		// TODO Auto-generated method stub
		
	}@Override
	public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
		// TODO Auto-generated method stub
		
	}@Override
	public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		// TODO Auto-generated method stub
		
	}@Override
	public void drawString(AttributedCharacterIterator iterator, int x, int y) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public FontMetrics getFontMetrics(Font f) {
		// TODO Auto-generated method stub
		return null;
	}@Override
	public void drawString(String str, int x, int y) {
		// TODO Auto-generated method stub
		
	}@Override
	public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		// TODO Auto-generated method stub
		
	}@Override
	public void fillOval(int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		
	}@Override
	public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		// TODO Auto-generated method stub
		
	}@Override
	public void fillRect(int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		
	}@Override
	public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		// TODO Auto-generated method stub
		
	}@Override
	public Shape getClip() {
		// TODO Auto-generated method stub
		return null;
	}@Override
	public Rectangle getClipBounds() {
		// TODO Auto-generated method stub
		return null;
	}@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}@Override
	public Font getFont() {
		// TODO Auto-generated method stub
		return null;
	}@Override
	public void setClip(Shape clip) {
		// TODO Auto-generated method stub
		
	}@Override
	public void setColor(Color c) {
		// TODO Auto-generated method stub
		
	}@Override
	public void setFont(Font font) {
		// TODO Auto-generated method stub
		
	}@Override
	public void setPaintMode() {
		// TODO Auto-generated method stub
		
	}@Override
	public void setXORMode(Color c1) {
		// TODO Auto-generated method stub
		
	}@Override
	public void translate(int x, int y) {
		// TODO Auto-generated method stub
		
	}@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}@Override
	public Graphics create(int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return super.create(x, y, width, height);
	}@Override
	public void draw3DRect(int x, int y, int width, int height, boolean raised) {
		// TODO Auto-generated method stub
		super.draw3DRect(x, y, width, height, raised);
	}@Override
	public void drawBytes(byte[] data, int offset, int length, int x, int y) {
		// TODO Auto-generated method stub
		super.drawBytes(data, offset, length, x, y);
	}@Override
	public void drawChars(char[] data, int offset, int length, int x, int y) {
		// TODO Auto-generated method stub
		super.drawChars(data, offset, length, x, y);
	}@Override
	public void drawPolygon(Polygon p) {
		// TODO Auto-generated method stub
		super.drawPolygon(p);
	}
}
