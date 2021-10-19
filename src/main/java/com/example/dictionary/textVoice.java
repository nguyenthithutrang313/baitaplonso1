package com.example.dictionary;

import com.sun.speech.freetts.*;
public class textVoice{
    //Create a voice to read word. Use FreeTTS
    public static void TTS(String word) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        final VoiceManager voiceManager = VoiceManager.getInstance();
        final Voice voice = voiceManager.getVoice("kevin16");
        voice.allocate();
        voice.speak(word);
    }
}
