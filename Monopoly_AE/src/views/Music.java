package views;

import java.io.File;

import javafx.scene.media.AudioClip;

public class Music {
	static AudioClip media2 = new AudioClip(new File ("c:\\sound.mp3").toURI().toString());
	
	
	
	
	public static void playmusic() {
		 media2.play();
		 
	}
	
	public static void stopmusic() {
		 media2.stop();
	
	}
	
	public static boolean musicstat() {
		if (media2.isPlaying()) {
			return(true);
		}
		else {
			return(false);
		}
		
	}
	


	}
	
	
	

