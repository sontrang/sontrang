package poiuyt.alarm;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.util.Log;

import java.io.IOException;
import java.util.logging.Logger;

import poiuyt.alarm.utils.LogUtils;

public class Klaxon {

    private static final long[] sVibratePattern = new long[]{500, 500};
    private static final float IN_CALL_VOLUME = 0.125f;
    //    private static final AudioAttributes.Builder VIBRATION_ATTRIBUTES = new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
//       .setUsage(AudioAttributes.USAGE_ALARM).build();
    // -->min 21
    private static boolean sStarted = false;
    private static MediaPlayer sMediaPlayer = null;

    public static void stop(Context context) {

        if (sStarted) {
            sStarted = false;
            if (sMediaPlayer != null) {
                sMediaPlayer.stop();
                AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                audioManager.abandonAudioFocus(null);
                sMediaPlayer.release();
                sMediaPlayer = null;
            }
            ((Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE)).cancel();
        }
    }

    public static void start(final Context context, AlarmInstance instance, boolean inTelephoneCall) {
        LogUtils.v("Klaxon.start()");
        stop(context);
        if (!AlarmInstance.NO_RINGTONE_URI.equals(instance.mRingtone)) {
            Uri alarmNoise = instance.mRingtone;
            if (alarmNoise == null) {
                alarmNoise = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                LogUtils.v("Using default alarm: " + alarmNoise.toString());
            }
            sMediaPlayer = new MediaPlayer();
            sMediaPlayer.setOnErrorListener(new OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    LogUtils.e("Error occurred while playing audio. Stopping Klaxon.");
                    Klaxon.stop(context);
                    return true;
                }
            });

            try {
                if (inTelephoneCall) {
                    LogUtils.v("Using the in-call alarm");
                    sMediaPlayer.setVolume(IN_CALL_VOLUME, IN_CALL_VOLUME);
                    setDataSourceFromResource(context, sMediaPlayer, R.raw.one);
                } else {
                    sMediaPlayer.setDataSource(context, alarmNoise);
                }
                startAlarm(context, sMediaPlayer);
            } catch (Exception ex) {
                LogUtils.v("Using the fallback ringtone");
                try {
                    sMediaPlayer.reset();
                    setDataSourceFromResource(context, sMediaPlayer, R.raw.two);
                    startAlarm(context, sMediaPlayer);
                } catch (Exception ex2) {
                    LogUtils.e("Failed to play fallback ringtone");
                }
            }
        }

//        if (instance.mVibrate) {
//            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
//            vibrator.vibrate(sVibratePattern, 0, VIBRATION_ATTRIBUTES);
//        }==> min 21

        sStarted = true;
    }

    private static void startAlarm(Context context, MediaPlayer player) throws IOException {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
            player.setAudioStreamType(AudioManager.STREAM_ALARM);
            player.setLooping(true);
            player.prepare();
            audioManager.requestAudioFocus(null,AudioManager.STREAM_ALARM, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
            player.start();
        }
    }

    private static void setDataSourceFromResource(Context context, MediaPlayer player, int res)
            throws IOException {
        AssetFileDescriptor afd = context.getResources().openRawResourceFd(res);
        if (afd != null) {
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
        }
    }
}
