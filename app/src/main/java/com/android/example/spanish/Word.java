package com.android.example.spanish;

/**
 * {@link Word} represents a vocabulary word that the user wants to learn.
 * It contains a default translation, a Miwok translation, and an image for that word.
 */

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

    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param spanishTranslation is the word in the Miwok language
     * @param audioResourceId is the resource ID for the audio file associated with this word
     */
    public Word(String defaultTranslation, String spanishTranslation, int audioResourceId){
        mDefaultTranslation = defaultTranslation;
        mSpanishTranslation = spanishTranslation;
        mAudioResourceId = audioResourceId;
    }

    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param spanishTranslation is the word in the Miwok language
     * @param imageResourceId is the drawable resource ID for the image associated with the word
     * @param audioResourceId is the resource ID for the audio file associated with this word
     */
    public Word(String defaultTranslation, String spanishTranslation, int imageResourceId, int audioResourceId){
        mDefaultTranslation = defaultTranslation;
        mSpanishTranslation = spanishTranslation;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }

    /**
     * Get the default translation of the word.
     */
    public int getImageResourceId(){ return mImageResourceId; }

    /**
     * Get the Miwok translation of the word.
     */
    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }

    /**
     * Return the image resource ID of the word.
     */
    public String getspanishTranslation(){
        return mSpanishTranslation;
    }

    /**
     * Returns whether or not there is an image for this word.
     */
    public boolean hasImage(){
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    /**
     * Return the audio resource ID of the word.
     */
    public int getAudioResourceId() { return mAudioResourceId; }
}
