package georgyhristov.xyz.mrnom.MrNom;

import georgyhristov.xyz.mrnom.framework.Game;
import georgyhristov.xyz.mrnom.framework.Graphics;
import georgyhristov.xyz.mrnom.framework.Screen;

/**
 * Created by gohv on 23.01.17.
 */
public class LoadingScreen extends Screen {

    public LoadingScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();

        Assets.background = g.newPixmap("background.png", Graphics.PixmapFormat.ARGB8888);
        Assets.logo = g.newPixmap("logo.png", Graphics.PixmapFormat.ARGB8888);
        Assets.mainMenu = g.newPixmap("mainmenu.png", Graphics.PixmapFormat.ARGB8888);
        Assets.buttons = g.newPixmap("buttons.png", Graphics.PixmapFormat.ARGB8888);
        Assets.help1 = g.newPixmap("help1.png", Graphics.PixmapFormat.ARGB8888);
        Assets.help2 = g.newPixmap("help2.png", Graphics.PixmapFormat.ARGB8888);
        Assets.help3 = g.newPixmap("help3.png", Graphics.PixmapFormat.ARGB8888);
        Assets.numbers = g.newPixmap("numbers.png", Graphics.PixmapFormat.ARGB8888);
        Assets.ready = g.newPixmap("ready.png", Graphics.PixmapFormat.ARGB8888);
        Assets.pause = g.newPixmap("pausemenu.png", Graphics.PixmapFormat.ARGB8888);
        Assets.gameOver = g.newPixmap("gameover.png", Graphics.PixmapFormat.ARGB8888);
        Assets.headUp = g.newPixmap("headup.png", Graphics.PixmapFormat.ARGB8888);
        Assets.headLeft = g.newPixmap("headleft.png", Graphics.PixmapFormat.ARGB8888);
        Assets.headDown = g.newPixmap("headdown.png", Graphics.PixmapFormat.ARGB8888);
        Assets.headRight = g.newPixmap("headright.png", Graphics.PixmapFormat.ARGB8888);
        Assets.tail = g.newPixmap("tail.png", Graphics.PixmapFormat.ARGB8888);
        Assets.stain1 = g.newPixmap("stain1.png", Graphics.PixmapFormat.ARGB8888);
        Assets.stain2 = g.newPixmap("stain2.png", Graphics.PixmapFormat.ARGB8888);
        Assets.stain3 = g.newPixmap("stain3.png", Graphics.PixmapFormat.ARGB8888);

        Assets.click = game.getAudio().newSound("click.ogg");
        Assets.eat = game.getAudio().newSound("eat.ogg");
        Assets.bitten = game.getAudio().newSound("bitten.ogg");

        Settings.load(game.getFileIO());
        game.setScreen(new MainMenuScreen(game));


    }

    @Override
    public void present(float deltaTime) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
