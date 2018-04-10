import java.util.ArrayList;
import java.util.Hashtable;

/* Generated by Together */

/**
 * Vehicle list manages the collection of vehicles currently associated with
 * permits. It handles checks of whether vehicles are allowed through the
 * barriers, records warnings, and various other functions. Note that each
 * Vehicle_info object must have a unique registration number, to allow sensible
 * checking and recording of entries onto the campus (so a HashTable is probably
 * a good implementation of the collection, with registration number as key).
 * There will only be one instance of this class.
 * 
 * @url element://model:project::PACSUS/design:node:::hb9ongkua8d7b-6jddv3
 * @url element://model:project::PACSUS/design:node:::eizwpgkua8d716ey0zc
 */
public class Vehicle_list {
	/**
	 * The Vehicle list maintains a collection of the known Vehicle_infos
	 * associated with all permits. This association must be implemented by an
	 * attribute holding a collection data structure (for example: array, hash
	 * table - the latter is recommended).
	 * 
	 * Note that no two Vehicle_infos may have the same registration number
	 * (this information is not represented diagrammatically) - this is to
	 * guarantee consistency with the constraint that no vehicle is associated
	 * with more than one permit.
	 * 
	 * Note that, given a registration number, the Vehicle_list can look up the
	 * appropriate Vehicle_info instance, and via that it can obtain the
	 * vehicle's permit information.
	 * 
	 * @associates Vehicle_info
	 * @label Contains
	 * @clientCardinality 1
	 * @supplierCardinality 0..*
	 * @directed
	 */

	private Vehicle_info Vehicle_info;
	private java.util.Hashtable<String, Vehicle_info> lnkVehicle;	
	

	public Vehicle_list() {

		lnkVehicle = new Hashtable<String, Vehicle_info>();
	}
	

	public void addNewVehicle(Permit p, String regNo, String make, String model, String colour) {

		Vehicle_info = new Vehicle_info(p, regNo, make, model, colour);
		lnkVehicle.put(regNo, Vehicle_info);
		p.addVehicleInfo(Vehicle_info);
	}
	

	public boolean checkPermitted(String regNo) {

		if (lnkVehicle.containsKey(regNo)) {
			return true;
		} else {
			return false;
		}
		
		
	}
	

	public boolean checkPermitted(String regNo, int d) {		
				
		boolean r = false;
		
		if (lnkVehicle.containsKey(regNo)) 
		{
			if (lnkVehicle.get(regNo).getPermitType().equals("Permanent Visitor") || lnkVehicle.get(regNo).getPermitType().equals("University Member"))
			{
				r = true;
			}
			
			else if (lnkVehicle.get(regNo).getPermitType().equals("Day Visitor")) 
			{
				if (lnkVehicle.get(regNo).getPermit().getEndDate().getDayNumber() == d)
				{					
					r = true;
				} 
				else
				{
					r = false;
				}				
			} 
			else if (lnkVehicle.get(regNo).getPermitType().equals("Regular Visitor"))
			{
				if (lnkVehicle.get(regNo).getPermit().getDate().getDayNumber() <= d && lnkVehicle.get(regNo).getPermit().getEndDate().getDayNumber() >= d)
				{
					r = true;
				} 
			} 
								
		} else
		{
			r = false;
		}
		
		return r;
	}
	

	public boolean addWarning(String regNo) {

		if (checkPermitted(regNo)) {
			lnkVehicle.get(regNo).issueWarning(regNo);
			return true;
		} else {
			return false;
		}

	}
	

	public void deleteWarning(String regNo) {

		lnkVehicle.get(regNo).deleteWarning();
	}
	

	public void deleteAllWarnings(String regNo) {

		lnkVehicle.get(regNo).deleteAllWarnings();
	}
	
	
	public void setEnteredToday(String regNo){
		boolean entered = true;
		lnkVehicle.get(regNo).getPermit().setEnteredToday(entered);
	}
	
	public void plusOneEntries(String regNo){
		lnkVehicle.get(regNo).getPermit().setEntries();
	}
	
	
	
	
	public void removeVehicleFromPermit(ArrayList<Vehicle_info> vi, String regNo) {

		for (int i = 0; i < vi.size(); i++) {
			if(vi.get(i).getRegNo().equals(regNo)){
			lnkVehicle.remove(vi.get(i).getRegNo());
			}
		}
	}


	public void removeAllVehiclesFromPermit(ArrayList<Vehicle_info> vi) {

		for (int i = 0; i < vi.size(); i++) {
			lnkVehicle.remove(vi.get(i).getRegNo());
		}
	}
	

	public int getWarnings(String regNo) {

		return lnkVehicle.get(regNo).getWarnings();
	}
	

	public boolean vehicleAllowedAccess(String regNo) {		

		if (checkPermitted(regNo)) {

			if (lnkVehicle.get(regNo).getPermit().getSuspended()) {
				
				return false;
				
			} else
			{
				return true;
			}
			
		} else
		{
			return false;
		}

	}
	
	

}
