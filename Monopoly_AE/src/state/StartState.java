package state;



public class StartState implements State {

	@Override
	
	public void doAction(Context context) {
		// TODO Auto-generated method stub
	
		context.getMedia2().play();
		context.getMedia2().setCycleCount(10);
		context.setState(this);

	

	}
}
