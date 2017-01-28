package georgyhristov.xyz.mrnom.framework;

/**
 * Created by gohv on 28.12.16.
 */
public interface Pixmap {
    public int getWidth();
    public int getHeight();

    public Graphics.PixmapFormat getFormat();

    public void dispose();
}
