package com.android.example.spanish;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange){
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> numberList = new ArrayList<Word>();

        numberList.add(new Word("where are you going ?","A dónde vas ?", R.raw.number_one));
        numberList.add(new Word("what is your name ?", "cuál es tu nombre ?", R.raw.number_two));
        numberList.add(new Word("my name is roni", "mi nombre es roni", R.raw.number_three));
        numberList.add(new Word("how are you feeling ?", "cómo te estás sintiendo ?", R.raw.number_four));
        numberList.add(new Word("I'm feeling very good.", "Me siento muy bien.", R.raw.number_five));
        numberList.add(new Word("are you coming ?", "vienes ?", R.raw.number_six));
        numberList.add(new Word("yes, I am coming.", "sí, ya voy.", R.raw.number_seven));
        numberList.add(new Word("let's go there.", "vamos para allá.", R.raw.number_eight));
        numberList.add(new Word("come on.", "vamos.", R.raw.number_nine));
        numberList.add(new Word("I love you", "Te quiero", R.raw.number_ten));

        WordAdapter adapter = new WordAdapter(this, numberList, R.color.category_phrases);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = numberList.get(position);
                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getAudioResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){
        if (mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
