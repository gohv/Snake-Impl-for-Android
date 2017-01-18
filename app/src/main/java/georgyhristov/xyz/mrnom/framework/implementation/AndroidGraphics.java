package georgyhristov.xyz.mrnom.framework.implementation;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.io.IOException;
import java.io.InputStream;

import georgyhristov.xyz.mrnom.framework.Graphics;
import georgyhristov.xyz.mrnom.framework.Pixmap;

/**
 * Created by gohv on 18.01.17.
 */

public class AndroidGraphics implements Graphics {

    private AssetManager assetManager;
    private Bitmap frameBuffer;
    private Canvas canvas;
    private Paint paint;
    private Rect srcRect = new Rect();
    private Rect dstRect = new Rect();

    public AndroidGraphics(AssetManager assetManager, Bitmap frameBuffer) {
        this.assetManager = assetManager;
        this.frameBuffer = frameBuffer;
    }

    @Override
    public Pixmap newPixmap(String fileName, PixmapFormat format) {
        Bitmap.Config config = null;
        if(format == PixmapFormat.RGB565){
            config = Bitmap.Config.RGB_565;
        }else if(format == PixmapFormat.ARGB4444){
            config = Bitmap.Config.ARGB_4444;
        }else{
            config = Bitmap.Config.ARGB_8888;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = config;

        InputStream inputStream = null;
        Bitmap bitmap = null;

        try{
            inputStream = assetManager.open(fileName);
            bitmap = BitmapFactory.decodeStream(inputStream);
            if(bitmap == null){
                throw new RuntimeException("Cant load bitmap from assets" + fileName);
            }

        }catch (IOException e){
            throw new RuntimeException("Cant load bitmap from assets" + fileName);
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if(bitmap.getConfig() == Bitmap.Config.RGB_565){
            format = PixmapFormat.RGB565;
        }else if(format == PixmapFormat.ARGB4444){
            config = Bitmap.Config.ARGB_4444;
        }else{
            config = Bitmap.Config.ARGB_8888;
        }
        return new AndroidPixmap(bitmap,format);
    }

    @Override
    public void clear(int color) {
        canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >>8,
                (color & 0xff));
    }

    @Override
    public void drawPixel(int x, int y, int color) {
        paint.setColor(color);
        canvas.drawPoint(x,y,paint);
    }

    @Override
    public void drawLine(int x, int y, int x2, int y2, int color) {
        paint.setColor(color);
        canvas.drawLine(x,y,x2,y2,paint);
    }

    @Override
    public void drawRect(int x, int y, int width, int height, int color) {
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(x,y,(x + width - 1),(y + width - 1),paint);
    }

    @Override
    public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight) {
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth -1;
        srcRect.bottom = srcY + srcHeight -1;

        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + srcWidth -1;
        dstRect.bottom = y + srcHeight -1;

        canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap,srcRect,dstRect,null);
    }

    @Override
    public void drawPixmap(Pixmap pixmap, int x, int y) {
        canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap,srcRect,dstRect,null);
    }

    @Override
    public int getWidth() {
        return frameBuffer.getWidth();
    }

    @Override
    public int getHeight() {
        return frameBuffer.getHeight();
    }
}
