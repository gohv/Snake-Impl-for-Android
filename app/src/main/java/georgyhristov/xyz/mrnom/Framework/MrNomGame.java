package georgyhristov.xyz.mrnom.Framework;

/**
 * Created by gohv on 28.12.16.
 */

public class MrNomGame extends AndroidGame {
    public Screen getStartScreen(){
        return new StartScreen(this);
    }
}
