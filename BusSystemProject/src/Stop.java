import java.util.Comparator;

class Stop implements Comparator<Stop> { 
    public int to_stop_id; //intersection
    public int cost;
    public int stop_code;
    
    public Stop() { } //empty constructor 
   
    public Stop(int to_stop_id, int cost) { 
        
        this.to_stop_id = to_stop_id;
        this.cost = cost; 
    } 
    @Override
    public int compare(Stop stop1, Stop stop2) 
    { 
        if (stop1.cost < stop2.cost) 
            return -1; 
        if (stop1.cost > stop2.cost) 
            return 1; 
        return 0; 
    } 
}
