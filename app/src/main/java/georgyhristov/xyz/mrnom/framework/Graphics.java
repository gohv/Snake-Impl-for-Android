package georgyhristov.xyz.mrnom.framework;

/**
 * Created by gohv on 28.12.16.
 */

public interface Graphics {
    public static enum PixmapFormat{
        ARGB8888, ARGB4444, RGB565
    }
    public Pixmap newPixmap(String fileName, PixmapFormat format);
    public void clear(int color);
    public void drawPixel(int x,int y,int color);
    public void drawLine(int x,int y,int x2,int y2,int color);// x = from y = to x2,y2 = fat
    public void drawRect(int x, int y,int width,int height,int color);
    public void drawPixmap(Pixmap pixmap,int x,int y,int srcX,int srcY,int srcWidth, int srcHeight);
    public void drawPixmap(Pixmap pixmap, int x, int y);

    public int getWidth();
    public int getHeight();


}
