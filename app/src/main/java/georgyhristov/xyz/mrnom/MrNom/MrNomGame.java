package georgyhristov.xyz.mrnom.MrNom;

import georgyhristov.xyz.mrnom.framework.AndroidGame;
import georgyhristov.xyz.mrnom.framework.Screen;
import georgyhristov.xyz.mrnom.framework.StartScreen;

/**
 * Created by gohv on 28.12.16.
 */

public class MrNomGame extends AndroidGame {

    @Override
    public Screen getStartScreen() {
        return new LoadingScreen(this);
    }
}
