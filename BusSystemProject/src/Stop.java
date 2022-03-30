
class Stop {
	public int stopId;	
	public String stopName;
	public int stopIndex;
	
	public Stop(){};
	
	public Stop(int stopId)
	{
		this.stopId = stopId;
	}
	public Stop(int stopId, String stopName, int stopIndex)
	{
		this.stopId = stopId;
		this.stopName = stopName;
		this.stopIndex = stopIndex;
	}	
}
