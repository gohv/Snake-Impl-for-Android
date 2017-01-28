package georgyhristov.xyz.mrnom.framework.implementation;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

import georgyhristov.xyz.mrnom.framework.Music;

/**
 * Created by gohv on 12.01.17.
 */
public class AndroidMusic implements Music {
    MediaPlayer mediaPLayer;
    boolean isPrepared = false;

    public AndroidMusic(AssetFileDescriptor descriptor) {

        mediaPLayer = new MediaPlayer();
        try {
            mediaPLayer.setDataSource(descriptor.getFileDescriptor(),
                    descriptor.getStartOffset(),
                    descriptor.getLength());
            mediaPLayer.prepare();
            isPrepared = true;
            mediaPLayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                   synchronized (this){
                       isPrepared = false;
                   }
                }
            });
        }catch (Exception e){
            throw new RuntimeException();
        }

    }

    @Override
    public void play() {
        if(mediaPLayer.isPlaying()) return;

        try{

            synchronized (this){
                if(!isPrepared){
                    mediaPLayer.prepare();
                    mediaPLayer.start();
                }
            }
        }catch (IllegalStateException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        mediaPLayer.stop();
        synchronized (this){
            isPrepared = false;
        }

    }

    @Override
    public void pause() {
        if(mediaPLayer.isPlaying()){
            mediaPLayer.pause();
        }
    }

    @Override
    public void setLooping(boolean looping) {
            mediaPLayer.setLooping(looping);
    }

    @Override
    public void setVolume(float volume) {
        mediaPLayer.setVolume(volume,volume);
    }

    @Override
    public boolean isPlaying() {
        return mediaPLayer.isPlaying();
    }

    @Override
    public boolean isStopped() {
        return !isPrepared;
    }

    @Override
    public boolean isLooping() {
        return mediaPLayer.isLooping();
    }

    @Override
    public void dispose() {
        if(mediaPLayer.isPlaying()){
            mediaPLayer.stop();
            mediaPLayer.release();
        }
    }
}
