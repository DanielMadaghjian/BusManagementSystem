class Trip {
	public String arrivalTime;
	public int tripId;
	public int stopId;		
	public String stopName;
	
	public Trip(){};
	
	public Trip(String arrivalTime, int tripId, int stopId, String stopName)
	{
		this.arrivalTime = arrivalTime;
		this.tripId = tripId;
		this.stopId = stopId;
		this.stopName = stopName;	
	}
	
	
}
