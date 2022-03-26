import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShortestPath {
	int V; //Number of stops
	List<List<Stop> > adj_list; //list of stops 
	private int stopFrom; 
	private int stopTo;
	
	ShortestPath(String filename, int stopFrom, int stopTo)
	{
		this.stopFrom = stopFrom;
		this.stopTo = stopTo;
		adj_list = new ArrayList<List<Stop> >();
		BufferedReader br;
		
		 try {
	            if (filename != null) {
	                br = new BufferedReader(new FileReader(filename));
	                int lineNum = 0;
	                V = getTotalNumberOfStops(filename);
	                try {
	                    String line = br.readLine();
	                    while (line != null) {
	                    	//number of intersections
	                        if (lineNum == 0) {	                            
	                         
	                                      
	                        }	                       	                       
	                        //path
	                        else {
	                            
	                        }
	                        lineNum++;
	                        line = br.readLine();
	                    }
	                } catch (IOException e) {
	                    e.printStackTrace();
	                } finally {
	                    try {
	                        br.close();
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }	           
	        } catch (FileNotFoundException e) {
	            
	        }
	}
	
	private int getTotalNumberOfStops(String filename)
	{
		int max = 0;
		if(filename!= null)
		{
			try {			
				BufferedReader br = new BufferedReader(new FileReader(filename));
				try {
					int lineNum = 1;				
					String line = br.readLine();
					while(line!=null)
					{
						String[] split = line.trim().split("\\s*"); //seperated by commas
						if(Integer.parseInt(split[0]) > max)
						{
							max = Integer.parseInt(split[0]);
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return max;
	}
	
	
	

}
