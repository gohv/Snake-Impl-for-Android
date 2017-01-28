package georgyhristov.xyz.mrnom.MrNom;

import java.util.List;

import georgyhristov.xyz.mrnom.framework.Game;
import georgyhristov.xyz.mrnom.framework.Graphics;
import georgyhristov.xyz.mrnom.framework.Input;
import georgyhristov.xyz.mrnom.framework.Screen;

/**
 * Created by gohv on 24.01.17.
 */
public class HelpScreen extends Screen {

    public HelpScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvent();

        int len = touchEvents.size();
        for (Input.TouchEvent t : touchEvents) {
            Input.TouchEvent event = t;
            if(event.type == Input.TouchEvent.TOUCH_UP){
                if(event.x > 256 && event.y > 416){
                    game.setScreen(new HelpScreen2(game));
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
            }if(event.type == Input.TouchEvent.TOUCH_UP){
                    if(event.x > 0 && event.y > 416){
                    game.setScreen(new MainMenuScreen(game));
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.background,0,0);
        g.drawPixmap(Assets.help1,64,100);
        g.drawPixmap(Assets.buttons,256,416,0,64,64,64);
        g.drawPixmap(Assets.buttons, 0, 416, 64, 64, 64, 64);
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
