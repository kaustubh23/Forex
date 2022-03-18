package scalablecapital.forex.forex.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author sharmak
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetForexRequest {

   private String from;
   private String to;
   private double amount;
public String getFrom() {
	return from;
}
public void setFrom(String from) {
	this.from = from;
}
public String getTo() {
	return to;
}
public void setTo(String to) {
	this.to = to;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
}
