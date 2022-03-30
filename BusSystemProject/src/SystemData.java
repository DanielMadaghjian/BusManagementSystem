import java.io.BufferedReader;
import java.io.File;
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
	public ArrayList<ArrayList<DirectedEdge>> edges;
	
	public SystemData(String filename1, String filename2, String filename3)
	{
		this.stopsFile = filename1;
		this.transfersFile = filename2;
		this.stopTimesFile = filename3;
		stops = new ArrayList<Stop>();
		//initialiseStops();
		V = stops.size();
		edges = new ArrayList<>(stops.size());
		for (int i = 0; i < stops.size(); i++) {
            edges.add(new ArrayList<>());
        }
		//addEdgesFromTransfers();	
		//addEdgesFromStopTimes();
	}
	
	public void initialiseStops()
	{
		
	}
	
	public void addEdgesFromTransfers()
	{
		
	}
	
	public void addEdgesFromStopTimes()
	{
		
	}

	
}
