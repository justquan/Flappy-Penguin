// Sound - This class is used to process the song playing in the background and perform playing operations on it.

import javax.swing.JApplet;
import java.applet.*;
import java.net.URL;

public class Sound
{
    private AudioClip clip;
    private boolean isPlaying;
    // Pre: file is the name of a song file which is of valid sound format
    // Post: Audio file named file is stored as a sound
    public Sound(String file)
    {
        clip = Applet.newAudioClip(getClass().getResource(file));
		isPlaying = false;
    }
    
    // Pre: None
    // Post: clip is played until it ends
    public void play()
    {
        clip.play();
		isPlaying = true;
    }
    
    // Pre: None
    // Post: clip is stopped
    public void stop()
    {
        clip.stop();
		isPlaying = false;
    }
    
    // Pre: None
    // Post: clip replays if it ends
    public void loop()
    {
        clip.loop();
		isPlaying = true;
    }

	//Pre: None
	//Post: returns if clip is playing
	public boolean isPlaying()
	{
		return isPlaying;
	}
}