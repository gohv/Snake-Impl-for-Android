package georgyhristov.xyz.mrnom.Framework;

/**
 * Created by gohv on 28.12.16.
 */
public interface Music {
    public void play();
    public void stop();
    public void pause();
    public void setLooping(boolean looping);
    public void setVolume(float volume);
    public boolean isPlaying();
    public boolean isStopped();
    public boolean isLooping();
    public void dispose();
}
