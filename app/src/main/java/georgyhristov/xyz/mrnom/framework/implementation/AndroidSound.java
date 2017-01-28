package georgyhristov.xyz.mrnom.framework.implementation;

import android.media.SoundPool;

import georgyhristov.xyz.mrnom.framework.Sound;

/**
 * Created by gohv on 12.01.17.
 */
public class AndroidSound implements Sound {
    private int soundID;
    private SoundPool soundPool;

    public AndroidSound(SoundPool soundPool, int soundID) {
        this.soundID = soundID;
        this.soundPool = soundPool;
    }

    @Override
    public void play(float volume) {
        soundPool.play(soundID,volume,volume,0,0,1);
    }

    @Override
    public void dispose() {
        soundPool.unload(soundID);
    }
}
