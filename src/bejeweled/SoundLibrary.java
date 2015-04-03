/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bejeweled;

/**
 *
 * @author Wilmer Carranza
 */
import java.util.HashMap;
import java.applet.*;
import java.net.*;

public class SoundLibrary {

    HashMap<String, AudioClip> soundLibrary;

    public SoundLibrary() {
        AudioClip select = null;
        AudioClip match = null;
        AudioClip fall = null;
        try {
            select = Applet.newAudioClip(new URL("file:resources/select.wav"));
            fall = Applet.newAudioClip(new URL("file:resources/fall.wav"));
            match = Applet.newAudioClip(new URL("file:resources/match.wav"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        soundLibrary = new HashMap(3);
        soundLibrary.put("select", select);
        soundLibrary.put("fall", fall);
        soundLibrary.put("match", match);

    }

    public void playAudio(String name) {
        AudioClip sample = soundLibrary.get(name);
        sample.play();
    }
}
