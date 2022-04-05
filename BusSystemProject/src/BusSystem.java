import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class BusSystem {
	public static SystemData data;
	public static DijkstraShortestPath shortestPath;
	public static TST tree;
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);	
		System.out.println("Please wait while we gather bus stop information.....\n");		
		data = new SystemData("stops.txt","transfers.txt", "stop_times.txt");
		boolean finished = false;
		System.out.println("WELCOME TO THE VANCOUVER BUS PLANNER \n");
		do
		{
			System.out.println("Please choose an option to proceed or type 'exit' to leave: ");
			System.out.println("1. Plan your Journey\n2. Search for bus stop\n3. Search for trip(s) by arrival time");
			//Check choice
			if(input.hasNextInt())
			{
				int choice = input.nextInt();
				if(choice >0 && choice <4)
				{
					switch(choice)
					{
						case 1:
							System.out.println("Please enter stop id: ");
							boolean correctFromId = false;
							//check first stop id
							while(!correctFromId)
							{
								System.out.println("From: ");
								if(input.hasNextInt())
								{
									
									int stopFrom = input.nextInt();
									if(data.getStopIndex(stopFrom) != -1)
									{
										correctFromId = true;										
										boolean correctToId = false;
										//check second stop id
										while(!correctToId)
										{
											System.out.println("To: ");
											if(input.hasNextInt())
											{
												int stopTo = input.nextInt();
												if(data.getStopIndex(stopTo) != -1)
												{
													correctToId = true;													
													findShortestPath(data, stopFrom, stopTo);																							
												}
												else
												{
													System.out.println("Invalid stop Id\n");
												}
											}
											else
											{
												System.out.println("Invalid stop Id\n");
												input.next();
											}
										}																											
									}
									else
									{
										System.out.println("Invalid stop Id\n");
									}									
								} 
								else
								{
									System.out.println("Invalid stop Id\n");
									input.next();
								}
							}
							break;
						case 2:
							
							boolean correctStopName = false;
							do
							{
								System.out.println("Please enter the bus stop name, or by the first few characters: ");

									String stopName = input.next();	
									tree = new TST(data);
									Iterable<String> listOfStops = tree.keysWithPrefix(stopName.toUpperCase());
									findStopsUsingTST(data, tree, listOfStops);
									
									if(tree.checkIfValidStop(listOfStops))
									{
										correctStopName = true;
									}
									else
									{
										System.out.println("No stops found.\n");
									}
						    } while(!correctStopName);
							break;
						case 3:
							boolean validArrivalTime = false;
							while(!validArrivalTime)
							{
								System.out.println("Please enter the trip arrival time in the format: HH:MM:SS");
								String arrivalTime = input.next();
								if(data.checkValidArrivalTime(arrivalTime))
								{								
									ArrayList<Trip> matchingTrips = findMatchingTrips(arrivalTime);
									if(matchingTrips != null)
									{
										validArrivalTime = true;
										printTrips(matchingTrips);
									}
									else
									{
										System.out.println("No trips found. \n");
									}
								}
								else if(!data.checkValidArrivalTime(arrivalTime))
								{
									System.out.println("Invalid arrival time.\n");
									
     							}
							} 
							break;
						default:								
					}					
				}
			}
			else if(input.hasNext("exit"))
			{
				finished = true;
			}
			else
			{
				System.out.println("Invalid option\n");
			}			
		} while(!finished);		
		System.out.println("Thank you for using this service!");		
	}
	
	public static void findShortestPath(SystemData data, int stopFrom, int stopTo)
	{		
		shortestPath = new DijkstraShortestPath(data, stopFrom, stopTo);
		ArrayList<Stop> route = shortestPath.shortestRoute;
		
		Stop firstStop;
		Stop secondStop;
		DirectedEdge stopsEdge;
		double cost = 0;
		double totalCost =0;
		int stopSequence= 1;
		int count = 1;
		System.out.println("The Route from stop " + stopFrom + " to stop " + stopTo + " is:\n");
		firstStop = route.get(0);
		System.out.println(stopSequence + ") " + firstStop.stopId + " ~ " + firstStop.stopName + " ~ Cost -> 0\n");
		stopSequence++;
        for (int i = 1; i < route.size(); i++) {
            secondStop = route.get(i);
            for(DirectedEdge edge: data.edges.get(data.getStopIndex(firstStop.stopId)))
            {
            	if(edge.dest == secondStop.stopId)
            	{
            		stopsEdge = edge;
            		cost = stopsEdge.cost;
            	}
            }
            System.out.println(stopSequence + ") " + secondStop.stopId + " ~ " + secondStop.stopName + " ~ Cost -> " + cost + "\n");
            firstStop = secondStop;
            totalCost += cost;     
            stopSequence++;
            count++;
        }
        System.out.println("The total cost of this trip is: " + totalCost);	
        System.out.println("The number of stops during the journey is: " + count + "\n");
	}
	
	public static void findStopsUsingTST(SystemData data, TST tree, Iterable<String> listOfStops)
	{
		if(tree.checkIfValidStop(listOfStops))
		{
			int count = 0;
			for(String key: listOfStops)
			{
				Stop stop = tree.get(key);
				System.out.println(" " + stop.stopId + " ~ " + stop.stopDesc + "\n");
				count++;
			}	
			System.out.println("Total results found: " + count + "\n");
		}						
	}
	
	public static ArrayList<Trip> findMatchingTrips(String arrivalTime)
	{
		ArrayList<Trip> matchingTrips = new ArrayList<Trip>();
		for(Trip trip: data.trips)
		{
			String tripArrivalTime = trip.arrivalTime;
		
			if(tripArrivalTime.charAt(0) == ' ')
			{
				tripArrivalTime = tripArrivalTime.substring(1);
			}
			if(arrivalTime.charAt(0) == ' ')
			{
				arrivalTime = arrivalTime.substring(1);
			}
			
			if(tripArrivalTime.equals(arrivalTime))
			{
				matchingTrips.add(new Trip(tripArrivalTime, trip.tripId, trip.stopId, trip.stopName));
			}
		}
		if(matchingTrips.isEmpty())
		{
			return null;
		}
		else
		{
			return matchingTrips;
		}
	}
	
	public static void printTrips(ArrayList<Trip> matchingTrips)
	{
		if(!matchingTrips.isEmpty())
		{
				matchingTrips = sortTripsById(matchingTrips);
				int tripSequence = 1;
				int count = 0;
				for(Trip trip: matchingTrips)
				{
					System.out.println(tripSequence + ") " + trip.arrivalTime + " ~ " + trip.tripId + " ~ " + trip.stopName + "\n" );
					tripSequence++;
					count++;
				}
				System.out.println("The total trips found is: " + count + "\n");			
		}
		
	}
	
	public static ArrayList<Trip> sortTripsById(ArrayList<Trip> matchingTrips)
	{
		ArrayList<Trip> newMatchingTrips = new ArrayList<Trip>();
		int[] tripIds = new int[matchingTrips.size()];
		int index = 0;
		for(Trip trip : matchingTrips)
		{
			tripIds[index]=trip.tripId;
			index++;
		}
		Arrays.sort(tripIds);
		for(int i = 0; i < tripIds.length; i++)
		{
			newMatchingTrips.add(data.getTripWithTripId(tripIds[i]));
		}
		return newMatchingTrips;
	}
}
