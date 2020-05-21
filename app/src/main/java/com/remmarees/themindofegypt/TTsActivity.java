package com.remmarees.themindofegypt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.remmarees.themindofegypt.sqlHelper.DataContainer;

import java.util.Locale;

public class TTsActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{


        TextToSpeech mTTS = null;
        private final int ACT_CHECK_TTS_DATA = 1000;
        Context context=this;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tts);
            final EditText TTS_TV = (EditText)findViewById(R.id.tts_tv);
            final Button TTS_read = (Button)findViewById(R.id.tts_read);
            TTS_read.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String testtxt =

                            "<?xml version=\"1.0\"?>" +
                                    "<speak version=\"1.0\" xmlns=\"http://www.w3.org/2001/10/synthesis\" " +
                                    "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                                    "xsi:schemaLocation=\"http://www.w3.org/2001/10/synthesis " +
                                    "http://www.w3.org/TR/speech-synthesis/synthesis.xsd\" " +
                                    "xml:lang=\"en-US\">" +

                                    "<phoneme alphabet=\"ipa\" ph=\"" + TTS_TV.getText().toString() +
                                    "\"/>." +
                            "</speak>";
                    //saySomething(TTS_TV.getText().toString().trim(), 1);
                    saySomething(testtxt, 1);
                }
            });
            DataContainer.letter_sound=R.raw.p;
            final Button play_button = findViewById(R.id.p_sound);
            play_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    playSound("p.mp3");
                }
            });

            // Check to see if we have TTS voice data
            Intent ttsIntent = new Intent();
            ttsIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
            startActivityForResult(ttsIntent, ACT_CHECK_TTS_DATA);
        }

        private void saySomething(String text, int qmode) {
            if (qmode == 1)
                mTTS.speak(text, TextToSpeech.QUEUE_ADD, null);
            else
                mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode,Intent data){
            super.onActivityResult(requestCode,resultCode,data);
            if (requestCode == ACT_CHECK_TTS_DATA) {
                if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                    // Data exists, so we instantiate the TTS engine
                    mTTS = new TextToSpeech(this, this);
                } else {
                    // Data is missing, so we start the TTS
                    // installation process
                    Intent installIntent = new Intent();
                    installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                    startActivity(installIntent);
                }
            }
        }

        public void onInit(int status) {
            if (status == TextToSpeech.SUCCESS) {
                if (mTTS != null) {
                    int result = mTTS.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(this, "TTS language is not supported", Toast.LENGTH_LONG).show();
                    } else {
                        saySomething("TTS is ready", 0);
                    }
                }
            } else {
                Toast.makeText(this, "TTS initialization failed",
                        Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onDestroy() {
            if (mTTS != null) {
                mTTS.stop();
                mTTS.shutdown();
            }
            super.onDestroy();
        }

    public void playSound(String file_name) {
        try {
            MediaPlayer m = new MediaPlayer();

            AssetFileDescriptor descriptor = getAssets().openFd(file_name);
            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();

            m.prepare();
            m.setVolume(1f, 1f);
            m.setLooping(false);
            m.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
