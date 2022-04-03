import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SystemData {
	public int V;
	private String stopsFile;
	private String transfersFile;
	private String stopTimesFile;
	
	public ArrayList<Stop> stops;
	public ArrayList<Stop> stopsTST;
	public ArrayList<ArrayList<DirectedEdge>> edges;
	
	public SystemData(String filename1, String filename2, String filename3)
	{
		this.stopsFile = filename1;
		this.transfersFile = filename2;
		this.stopTimesFile = filename3;
		stops = new ArrayList<Stop>();
		stopsTST = new ArrayList<Stop>();
		initialiseStops();
		initialiseStopsTST();
		V = stops.size();
		edges = new ArrayList<>(stops.size());
		for (int i = 0; i < stops.size(); i++) {
            edges.add(new ArrayList<>());
        }
		addEdgesFromTransfers();	
		addEdgesFromStopTimes();
	}
	
	public void initialiseStops()
	{
		BufferedReader br;
		
		try {
			br = new BufferedReader(new FileReader(stopsFile));
			try {
				String line = br.readLine();
				line = br.readLine();	
				int stopId;				
				String stopName;
				int i = 0;
				while(line!= null)
				{
					stopId = Integer.parseInt(line.split(",")[0]);					
					stopName = line.split(",")[2];
					stops.add(new Stop(stopId, stopName, i));
					i++;
					line = br.readLine();
				}
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void initialiseStopsTST()
	{
		BufferedReader br;
		
		try {
			br = new BufferedReader(new FileReader(stopsFile));
			try {
				String line = br.readLine();
				line = br.readLine();	
				int stopId;				
				String stopName;	
				String stopDesc;
				while(line!= null)
				{
					stopId = Integer.parseInt(line.split(",")[0]);					
					stopName = line.split(",")[2];
					stopDesc = line.split(",")[3];
					stopsTST.add(new Stop(stopId, stopName, stopDesc));					
					line = br.readLine();
				}	
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void addEdgesFromTransfers()
	{
		BufferedReader br;
		int src;
		int dest;
		double minTransferTime;
		int transferType;
		double cost;
		try {
			br = new BufferedReader(new FileReader(transfersFile));
			try {
				String line = br.readLine();
				line = br.readLine();		
				while(line!= null)
				{					
					src = Integer.parseInt(line.split(",")[0]);
					dest = Integer.parseInt(line.split(",")[1]);
					transferType = Integer.parseInt(line.split(",")[2]);
									
					if(transferType == 0)
					{
						cost = 2;
					}
					else
					{
						minTransferTime = Double.parseDouble(line.split(",")[3]);
						cost = minTransferTime/100.0;
					}
					edges.get(getStopIndex(src)).add(new DirectedEdge(src, dest, cost));
					line = br.readLine();			
				}
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void addEdgesFromStopTimes()
	{
		BufferedReader br;
		int currentTripId;
		int lastTripId;
		int currentStopId;
		int lastStopId;
		
		double cost = 1;
		try {
			br = new BufferedReader(new FileReader(stopTimesFile));
			try {
				String line = br.readLine();
				line = br.readLine();	
				
				lastTripId = Integer.parseInt(line.split(",")[0]);
				lastStopId = Integer.parseInt(line.split(",")[3]);
				line = br.readLine();
				while(line!=null)
				{
					currentTripId = Integer.parseInt(line.split(",")[0]);
					if(currentTripId == lastTripId)
					{
						currentStopId = Integer.parseInt(line.split(",")[3]);
						edges.get(getStopIndex(lastStopId)).add(new DirectedEdge(lastStopId, currentStopId, cost));
						lastStopId = currentStopId;
						lastTripId = currentTripId;
					}
					else
					{
						lastStopId = Integer.parseInt(line.split(",")[3]);
						lastTripId = currentTripId;
					}
					line = br.readLine();
				}
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public int getStopIndex(int stopId)
	{		
		for(Stop stop: stops)
		{
			if(stop.stopId == stopId)
			{
				return stop.stopIndex;
			}
		}
		return -1;
	}

	
}
