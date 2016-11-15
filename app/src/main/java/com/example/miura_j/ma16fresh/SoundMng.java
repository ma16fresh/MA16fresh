package com.example.miura_j.ma16fresh;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

public class SoundMng {
    Context context;
    SoundPool soundPool;
    int otsukaresama;
    int wakeUp;
    int streamId;
    boolean loadStatus = false;

    public void load(Context activity, final int level) {
        AudioAttributes attr = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();

        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attr)
                .setMaxStreams(2)
                .build();

            wakeUp = soundPool.load(activity, R.raw.okite, 1);
            otsukaresama = soundPool.load(activity, R.raw.otsukare, 1);


        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                if (0 == status) {
                    loadStatus = true;
                    SoundStart(level);
                }
            }
        });
    }
    public void SoundStart(int level) {
        if(loadStatus  == true) {
            if(level == 0) {
                streamId = soundPool.play(otsukaresama, 1F, 1F, 0, 0, 1F);
            }
            switch(level){
                case 1:
                    streamId = soundPool.play(wakeUp, 0.2F, 0.2F, 0, -1, 1F);
                    break;
                case 2:
                    streamId = soundPool.play(wakeUp, 0.4F, 0.4F, 0, -1, 1F);
                    break;
                case 3:
                    streamId = soundPool.play(wakeUp, 0.6F, 0.6F, 0, -1, 1F);
                    break;
                case 4:
                    streamId = soundPool.play(wakeUp, 0.8F, 0.8F, 0, -1, 1F);
                    break;
                case 5:
                    streamId = soundPool.play(wakeUp, 1F, 1F, 0, -1, 1F);
                    break;
            }
        }else{
        }
    }

    public void SoundStop() {
        soundPool.stop(streamId);
        soundPool.release();
    }
}

