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

public class FamilyActivity extends AppCompatActivity {
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

        numberList.add(new Word("father","padre", R.drawable.family_father, R.raw.father));
        numberList.add(new Word("mother", "mamá", R.drawable.family_mother, R.raw.mother));
        numberList.add(new Word("son", "hijo", R.drawable.family_son, R.raw.son));
        numberList.add(new Word("daughter", "hija", R.drawable.family_daughter, R.raw.daughter));
        numberList.add(new Word("older brother", "hermano mayor", R.drawable.family_older_brother, R.raw.olther_brother));
        numberList.add(new Word("younger brother", "hermano menor", R.drawable.family_younger_brother, R.raw.younger_brother));
        numberList.add(new Word("older sister", "hermana mayor", R.drawable.family_older_sister, R.raw.older_sister));
        numberList.add(new Word("younger sister", "hermana menor", R.drawable.family_younger_sister, R.raw.younger_sister));
        numberList.add(new Word("grandmother", "abuela", R.drawable.family_grandmother, R.raw.grandmother));
        numberList.add(new Word("grandfather", "abuelo", R.drawable.family_grandfather, R.raw.grandfather));

        WordAdapter adapter = new WordAdapter(this, numberList, R.color.category_family);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = numberList.get(position);
                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getAudioResourceId());
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
