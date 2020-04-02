package simulator;

public class ValuePerSecond {
	private float timeMoment;
	private float value;
	
	public ValuePerSecond() {
		super();
		this.timeMoment = -1;
		this.value = -1;
	}
	
	public synchronized float getTimeMoment() {
		return timeMoment;
	}
	public void setTimeMoment(float timeMoment) {
		this.timeMoment = timeMoment;
	}
	public synchronized float getValue() {
		return value;
	}
	public synchronized void setValue(float value) {
		this.value = value;
	}
	
	@Override
	public synchronized String toString() {
		return "La secunda: " + this.timeMoment + ", valoarea: "+this.value;
	}
}
