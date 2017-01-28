package georgyhristov.xyz.mrnom.framework.implementation;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import java.io.IOException;

import georgyhristov.xyz.mrnom.R;
import georgyhristov.xyz.mrnom.framework.Audio;
import georgyhristov.xyz.mrnom.framework.Music;
import georgyhristov.xyz.mrnom.framework.Sound;

public class AndroidAudio implements Audio {
    private AssetManager assetManager;
    private SoundPool soundPool;
    private AudioAttributes audioAttributes;

    public AndroidAudio(Activity activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assetManager = activity.getAssets();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(20)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
        }
     }

    @Override
    public Music newMusic(String fileName) {
        try {
            AssetFileDescriptor descriptor = assetManager.openFd(fileName);
            return new AndroidMusic(descriptor);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Sound newSound(String fileName) {
        try {
            AssetFileDescriptor descriptor = assetManager.openFd(fileName);
            int soundID = soundPool.load(descriptor,0);
            return new AndroidSound(soundPool,soundID);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
