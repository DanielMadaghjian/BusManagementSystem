
class Stop {
	public int stopId;	
	public String stopName;
	public int stopIndex;
	public String stopDesc;
	
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
	public Stop(int stopId, String stopName, String stopDesc)
	{
		this.stopId = stopId;
		this.stopDesc = stopDesc;		
	
		if(stopName.charAt(0)=='W' && stopName.charAt(1)=='B')
		{
			this.stopName = stopName.substring(3, stopName.length()-1) + " WB";
		}
		else if(stopName.charAt(0)=='N' && stopName.charAt(1)=='B')
		{
			this.stopName = stopName.substring(3, stopName.length()-1) + " NB";
		}
		else if(stopName.charAt(0)=='S' && stopName.charAt(1)=='B')
		{
			this.stopName = stopName.substring(3, stopName.length()-1) + " SB";
		}
		else if(stopName.charAt(0)=='E' && stopName.charAt(1)=='B')
		{
			this.stopName = stopName.substring(3, stopName.length()-1) + " EB";
		}	
		else
		{
			this.stopName = stopName;
		}
		
	}
}
