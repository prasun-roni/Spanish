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

public class NumberActivity extends AppCompatActivity {
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

        numberList.add(new Word("one","uno", R.drawable.number_one, R.raw.one));
        numberList.add(new Word("two", "dos", R.drawable.number_two, R.raw.two));
        numberList.add(new Word("three", "tres", R.drawable.number_three, R.raw.three));
        numberList.add(new Word("four", "cuatro", R.drawable.number_four, R.raw.four));
        numberList.add(new Word("five", "cinco", R.drawable.number_five, R.raw.five));
        numberList.add(new Word("six", "seis", R.drawable.number_six, R.raw.six));
        numberList.add(new Word("seven", "siete", R.drawable.number_seven, R.raw.seven));
        numberList.add(new Word("eight", "ocho", R.drawable.number_eight, R.raw.eight));
        numberList.add(new Word("nine", "nueve", R.drawable.number_nine, R.raw.nine));
        numberList.add(new Word("ten", "diez", R.drawable.number_ten, R.raw.ten));

        WordAdapter adapter = new WordAdapter(this, numberList, R.color.category_numbers);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = numberList.get(position);
                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(NumberActivity.this, word.getAudioResourceId());

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
