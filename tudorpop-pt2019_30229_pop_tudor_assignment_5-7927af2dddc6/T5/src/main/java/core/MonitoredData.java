package core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class MonitoredData {
	private String startTime;
	private String stopTime;
	private String activity;
	public MonitoredData(String startTime, String stopTime, String activity) {
		super();
		this.startTime = startTime;
		this.stopTime = stopTime;
		this.activity = activity;
	}
	
	public MonitoredData(String data) {
		super();
		try {
			String[] rez = data.split("		");
			this.startTime = rez[0];
			this.stopTime =  rez[1];
			this.activity = rez[2];			
		}catch(Exception e) {
			//System.err.println(rez);
			System.out.println("Invalid input data");
		}
		
	}
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getStopTime() {
		return stopTime;
	}
	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	public String getStartDate() {
		return this.startTime.split(" ")[0];
	}
	
	public String getStopDate() {
		return this.stopTime.split(" ")[0];
	}
	
	public String getStopHr() {
		return this.stopTime.split(" ")[1];
	}
	
	public String getStartHr() {
		return this.startTime.split(" ")[1];
	}

	@Override
	public String toString() {
		return "MonitoredData [startTime=" + startTime + ", stopTime=" + stopTime + ", activity=" + activity + "]";
	}
	
	@Override
	public int hashCode() {
		return (this.activity.length())*31 + (int)(Math.random()*1193.0); // random 
	}
	
	public long getDuration() {//https://www.mkyong.com/java/how-to-calculate-date-time-difference-in-java/
		String start = this.startTime;
		String stop = this.stopTime;
		
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		//LocalDateTime startTime = new LocalDateTime(null, null);
		Date dStart = null;
		Date dStop = null;
		try {
			dStart = form.parse(start);
			dStop = form.parse(stop);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(dStart+ " " +dStop);
		//long duration = (dStop.getTime() - dStart.getTime())/(1000*60); //Duration in minutes
		
		
		return Duration.between(dStart.toInstant(), dStop.toInstant()).toSeconds();
	}
	
	
}
