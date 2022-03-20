package scalablecapital.forex.forex.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import scalablecapital.forex.forex.ForexBo;
/**
 * 
 * @author sharmak	
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetTotalResponse {
	
	private int statusCode;
	private String status;
	private List<ForexBo> forex;
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<ForexBo> getForex() {
		return forex;
	}
	public void setForex(List<ForexBo> forex) {
		this.forex = forex;
	}
 	
}
