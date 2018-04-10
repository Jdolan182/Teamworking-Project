/* Generated by Together */

/**
 * For a description of Day visitors, follow hyperlink to the Administration use case for issuing a new Day visitor permit.
 * @url element://model:project::PACSUS/design:node:::1zkptgkuaa1f5m84x7g
 */
public class Day_visitor_permit extends Permit {


	/**
     * The name of the University member hosting the visit. 
     */
    private String hostName;

    /**
     * The date that the visitor is visiting on - entry will be allowed on that date only.
     * @clientCardinality 1
     * @supplierCardinality 1
     * @label Visiting on
     * @link aggregation
     * @directed
     */
    private Date lnkDate;
    
    
    private String permitType = "Day Visitor";
    
    public Day_visitor_permit(String name, int visit, String host) {
		super(name);
		this.setPermitType(permitType);
		// TODO Auto-generated constructor stub
		hostName = host;
		lnkDate = new Date(visit);
	}
    
    public String getHostName(){
    	return hostName;
    }
    
	public String getPermitType() {
		return permitType;
	}
	
	public Date getDate(){
		return lnkDate;
	}
	
	
	public Date getEndDate(){
		return lnkDate;
	}
	
	
	public void setDate(int day){
		lnkDate = new Date(day);
	}
}
