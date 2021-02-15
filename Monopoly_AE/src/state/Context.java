package state;

import java.io.File;

import javafx.scene.media.AudioClip;

public class Context {
	private State state;
	private AudioClip media2 = new AudioClip(new File ("c:\\sound.mp3").toURI().toString());

	public AudioClip getMedia2() {
		return media2;
	}
	public void setMedia2(AudioClip media2) {
		this.media2 = media2;
	}
	public Context() {
		state=null;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public boolean etat() {
		
		return (media2.isPlaying());
	}
	
}
