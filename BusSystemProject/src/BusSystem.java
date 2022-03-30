import java.util.ArrayList;
import java.util.Scanner;

public class BusSystem {
	public static SystemData data;
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);			
		data = new SystemData("stops.txt","transfers.txt", "stop_times.txt");
		
	}
	
	public static void findShortestPath(SystemData data, int stopFrom, int stopTo)
	{		
		DijkstraShortestPath shortestPath = new DijkstraShortestPath(data, stopFrom, stopTo);
		ArrayList<Stop> route = shortestPath.shortestRoute;				
	}
}
