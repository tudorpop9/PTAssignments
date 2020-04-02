package HW5.T5;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import core.DateComparator;
import core.MonitoredData;

/**
 * Hello world!
 *
 */
public class App 
{
	public static long getDuration(String row) {//https://www.mkyong.com/java/how-to-calculate-date-time-difference-in-java/
		String comp[] = row.split("		");
		String start = comp[0];
		String stop = comp[1];
		
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

    public static void main( String[] args )
    {
        try {
            //<<1>>
			Stream<String> inF = Files.lines(Paths.get("Activities.txt")); //https://kousenit.org/2017/03/26/java-8-constructor-refs-in-all-their-glory/
			List<MonitoredData> objList = inF.map(row -> new MonitoredData(row))
											 .collect(Collectors.toList()); 
			
			//<<2>>
			long monitorPeriod = objList.stream().filter(obj -> obj.getStartDate().equals(obj.getStopDate()) == false)
										.count();
			System.out.println("[Ex2]Days of monitored data: "+ (monitorPeriod+1L));
			System.out.println("");
			
			//<<3>>
			Map<String, Long> activityMap = objList.stream() //https://vitalflux.com/java-one-liner-lambda-expression-print-maplist-objects/
												   .map(data -> data.getActivity())
												   .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
			
			activityMap.forEach((key,value)->System.out.println( "[Ex3]Activity: " + key.replaceAll("\\s", "") + "\t appears " + value+ " times."));//remove all white spaces
			System.out.println("");
			
			//<<4>>
			Map<String, Long> activityMapPerDay = objList.stream() //https://vitalflux.com/java-one-liner-lambda-expression-print-maplist-objects/
					   							         .map(data ->data.getStartDate() +" "+ data.getActivity())
					   							         .sorted(new DateComparator())
					                                     .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
			activityMapPerDay.forEach((key,value)->System.out.println( "[Ex4]Date/Activity: " + key + " appears " + value+ " times."));//remove all white spaces
			System.out.println("");
			inF.close();
			
			//<<5>>
			Stream<String> inF2 = Files.lines(Paths.get("Activities.txt"));
			List<String> rowDuration = inF2.map(row -> "Duration[hr]:"+(App.getDuration(row)/3600) +", [minutes]:"+(App.getDuration(row)/60)%60+ ", [seconds]:"+App.getDuration(row)%60 +" for: "+ row)
										   .collect(Collectors.toList());
			rowDuration.stream().forEach(row -> System.out.println("[Ex5] "+row));
			System.out.println("");
			inF2.close();
			
			//<<6>>
				Map<Object,Long> activityTimeMap = objList.stream() //https://www.baeldung.com/java-groupingby-collector
														   .collect(Collectors.groupingBy(o-> o.getActivity(),Collectors.summingLong( a-> a.getDuration())));
			activityTimeMap.forEach((key,value)->System.out.println( "[Ex6]Activity: " + ((String)key).replaceAll("\\s", "") + " \ttime: [hr]"+ value/3600 +", [minutes]:" + (value/60)%60 + ", [seconds]:"+(value%60) ));
			System.out.println("");
			
			//<<7>>
			//genereaza aparitiile activitatiilor cu o durata <5 minute
			Map<String, Long> activityDurationLessThan = objList.stream()
					   											.filter(o->o.getDuration() < 300)
																.map(data -> data.getActivity())
																.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
			activityDurationLessThan.forEach((key,value)->System.out.println( "[Ex7]Activity: " + key.replaceAll("\\s", "") + "\t with a duration <5 minutes appears " + value+ " times."));
			
			for (Entry<String, Long> entry1 : activityMapPerDay.entrySet()) {
				for (Entry<String, Long> entry2 : activityDurationLessThan.entrySet()) {
					if(entry1.getKey().equals(entry2.getKey()) && (double)(entry2.getValue()/entry1.getValue()) < 0.9) {
						System.err.println(entry1.getKey());
					}
				}
			}
			
			//activityMap.forEach((key,value) ->  App.lastTaskMethod(key, value)); 																					);
        
        }catch(Exception e) {
			
			System.out.println(e.getClass().getSimpleName());
			e.printStackTrace();
		}
    }
}
