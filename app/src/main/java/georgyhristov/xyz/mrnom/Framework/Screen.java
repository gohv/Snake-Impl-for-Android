package georgyhristov.xyz.mrnom.Framework;

/**
 * Created by gohv on 28.12.16.
 */
public abstract class Screen {
    protected final Game game;

    public Screen(Game game){
        this.game = game;
    }
    public abstract void update(float deltaTime);
    public abstract void present(float deltaTime);
    public abstract void pause();
    public abstract void resume();
    public abstract void dispose();
}
