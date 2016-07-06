package com.truiton.placeapiautocomplete;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

/**
 * Created by Jony on 20-04-2016.
 */
public class TTSLibrary {
    private Locale locSpanish;
    private TextToSpeech tts;
    public static final String PREFS_NAME = "config";
    String utteranceId;

    public TTSLibrary(Context context){
        locSpanish = new Locale("spa", "CL");
        try {
            tts=new TextToSpeech(context.getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    tts.setLanguage(locSpanish);
                    utteranceId=this.hashCode() + "";
                }
            }
            );
        } catch (Exception ex){
            tts=null;
        }
    }

    public void speakMe(String textToSpeach){
        final String text=textToSpeach;
        if(tts!=null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                tts.speak(text, TextToSpeech.QUEUE_ADD, null, utteranceId);
            }
            else
                tts.speak(text, TextToSpeech.QUEUE_ADD, null);
        }
    }

    public void stopSpeakMe(){
        if(tts!=null)
            tts.stop();
    }

    public void setVelocity(float velocity){
        if(tts!=null)
            tts.setSpeechRate(velocity);
    }

    public void shutdown() {
        if(tts!=null)
            tts.shutdown();
    }
}
