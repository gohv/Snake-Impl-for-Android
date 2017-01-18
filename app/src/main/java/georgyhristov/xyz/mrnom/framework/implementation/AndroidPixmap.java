package georgyhristov.xyz.mrnom.framework.implementation;

import android.graphics.Bitmap;

import georgyhristov.xyz.mrnom.framework.Graphics;
import georgyhristov.xyz.mrnom.framework.Pixmap;

/**
 * Created by gohv on 18.01.17.
 */

public class AndroidPixmap implements Pixmap {

    public Bitmap bitmap;
    private Graphics.PixmapFormat pixmapFormat;

    public AndroidPixmap(Bitmap bitmap, Graphics.PixmapFormat pixmapFormat) {
        this.bitmap = bitmap;
        this.pixmapFormat = pixmapFormat;
    }

    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }

    @Override
    public Graphics.PixmapFormat getFormat() {
        return pixmapFormat;
    }

    @Override
    public void dispose() {
        bitmap.recycle();
    }
}
