package com.android.example.spanish;

public class Word {

    // Default translation for the word
    private String mDefaultTranslation;

    // Spanish translation for the word
    private String mSpanishTranslation;

    // Audio resource ID for the word
    private int mAudioResourceId;

    // Image resource ID for the word
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    // Constant value that represents no image was provided for the word
    private static final int NO_IMAGE_PROVIDED = -1;

    public Word(String defaultTranslation, String spanishTranslation, int audioResourceId){
        mDefaultTranslation = defaultTranslation;
        mSpanishTranslation = spanishTranslation;
        mAudioResourceId = audioResourceId;
    }

    public Word(String defaultTranslation, String spanishTranslation, int imageResourceId, int audioResourceId){
        mDefaultTranslation = defaultTranslation;
        mSpanishTranslation = spanishTranslation;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }

    public int getImageResourceId(){ return mImageResourceId; }


    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }

    public String getspanishTranslation(){
        return mSpanishTranslation;
    }

    public boolean hasImage(){
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getAudioResourceId() { return mAudioResourceId; }
}
