package state;



public class StopState implements State {
	
	

	@Override
	public void doAction(Context context) {
		// TODO Auto-generated method stub
		
		context.getMedia2().stop();
		context.setState(this);
	}
}
