package georgyhristov.xyz.mrnom.Framework;

/**
 * Created by gohv on 28.12.16.
 */
public class StartScreen extends Screen {
    Pixmap pic;
    int x;
    public StartScreen(Game game) {
        super(game);
        pic = game.getGraphics().newPixmap("data/pic.png",
                Graphics.PixmapFormat.RGB565);

    }

    @Override
    public void update(float deltaTime) {
        x+= 1;
        if(x > 100)x = 0;
    }

    @Override
    public void present(float deltaTime) {
         game.getGraphics().clear(0);
         game.getGraphics().drawPixmap(pic,x,0,0,0,pic.getWidth(),pic.getHeight());
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
    pic.dispose();
    }
}
