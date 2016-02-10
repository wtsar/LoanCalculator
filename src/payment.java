import java.text.*;

public class payment {
	
	static DecimalFormat df = new DecimalFormat("###,###,###.00");
	public String totalInterest = "0";
	public String totalPaid = "0";
	
	public String loan(double principal, double interest, double years){
		 
		/*
		 * 
		 */
		if(interest == 0){totalInterest = "0"; totalPaid = df.format(principal);return df.format(principal/(years*12));}
		double payments = years * 12;//Year to Month
		interest = interest/(100*12); //APR
		double one = interest*(principal);
		double two = Math.pow((1+interest),-payments);
		double three = 1-two;
		double total = one/three;
		totalInterest = df.format(total*(years*12)-principal);
		totalPaid = df.format(total*(years*12));
		return df.format(total);
	}
}

