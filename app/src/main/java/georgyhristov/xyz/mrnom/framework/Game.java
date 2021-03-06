package georgyhristov.xyz.mrnom.framework;

/**
 * Created by gohv on 28.12.16.
 */

public interface Game {
    public Input getInput();
    public FileIO getFileIO();
    public Graphics getGraphics();
    public Audio getAudio();
    public void setScreen(Screen screen);
    public Screen getCurrentScreen();
    public Screen getStartScreen();

}
