import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DijkstraShortestPath {
	private int stopFrom;
	private int stopTo;
	public SystemData data;
	public ArrayList<Stop> shortestRoute;
	double[] dist;
	boolean[] visited;
	int[] prev;
	
	DijkstraShortestPath(SystemData data, int stopFrom, int stopTo)
	{
		this.stopFrom = stopFrom;
		this.stopTo = stopTo;
		this.data = data;
		shortestRoute = new ArrayList<Stop>();		
		findShortestPaths(stopFrom, stopTo);		
	}
	
    public void findShortestPaths(int src, int dest)
    {
    	
    }
   
}
