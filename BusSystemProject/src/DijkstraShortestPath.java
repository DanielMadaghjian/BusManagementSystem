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
	    dist = new double[data.V];
	    visited = new boolean[data.V];
	    prev = new int[data.V];
	    prev[data.getStopIndex(src)] = -1;
	    for (int i = 0; i < dist.length; i++) {
	        dist[i] = Double.POSITIVE_INFINITY;
	        visited[i] = false;
	    }
	    dist[data.getStopIndex(src)] = 0;
	    for (int i = 0; i < data.V - 1; i++) 
	    {
		    int vertex = minimumDistance(dist, visited);
		    if (vertex >= 0)
		    {
			    visited[vertex] = true;
			    for (DirectedEdge edge : data.edges.get(vertex))
			    {
			           int srcVert = data.getStopIndex(edge.src);
			           int destVert = data.getStopIndex(edge.dest);
			           double relaxDist = dist[srcVert] + edge.cost;
			           if (dist[destVert] > relaxDist) 
			           {
			                dist[destVert] = relaxDist;
			                prev[destVert] = srcVert;
			           }
			    }
		    }
	    }          
	    shortestRoute = findShortestRoute(prev, data.getStopIndex(dest));
	}

	    
	 // Referenced from www.geeksforgeeks.org
	public int minimumDistance(double[] distTo, boolean[] visited) {
	    double min = Double.MAX_VALUE;
	    int index = -1;
	    for (int i = 0; i < visited.length; i++) {
	        if (!visited[i] && distTo[i] <= min) {
	            min = distTo[i];
	            index = i;
	        }
	    }
	    return index;
	}
	      
	    //referenced from techiedelight.com
	public ArrayList<Stop> findShortestRoute(int[] prev, int lastStop)
	{   	
	    if(prev[lastStop] >= 0)
	    {
	    	findShortestRoute(prev, prev[lastStop]);
	        shortestRoute.add(data.stops.get(lastStop));         
	    }
	    else
	    {
	    	shortestRoute.add(data.stops.get(lastStop));
	    }
	    return shortestRoute;
	}	
}
