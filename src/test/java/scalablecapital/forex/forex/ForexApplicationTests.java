package scalablecapital.forex.forex;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import scalablecapital.forex.forex.dto.GetForexRequest;
import scalablecapital.forex.forex.dto.GetForexResponse;
import scalablecapital.forex.forex.dto.GetTotalResponse;
import scalablecapital.forex.forex.service.ForexService;

@RunWith(SpringRunner.class)
@SpringBootTest

class ForexApplicationTests {

	@Autowired
	ForexService service;

	@Test
	public void forexTest() {
		// Call the REST API

		GetForexRequest request = new GetForexRequest();
		request.setAmount(1);
		request.setFrom("ZAR");
		request.setTo("EUR");
 
		GetForexResponse response = service.getForect(request) ;
		assertTrue(response.getStatusCode() == 0);

	}
	
	
	@Test
	public void totalTest() {
		// Call the REST API
  
		GetTotalResponse response = service.getTotal( ) ;
		assertTrue(response.getStatusCode() == 200);

	}

}
