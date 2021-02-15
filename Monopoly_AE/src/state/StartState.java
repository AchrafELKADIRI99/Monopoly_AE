package state;

import java.io.File;

import javafx.scene.media.AudioClip;

public class StartState implements State {

	private AudioClip media2 ;
	@Override
	
	public void doAction(Context context) {
		// TODO Auto-generated method stub
	
		context.getMedia2().play();
		context.setState(this);

	}


}
