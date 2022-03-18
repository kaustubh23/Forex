package scalablecapital.forex.forex.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import scalablecapital.forex.forex.dto.GetForexRequest;
import scalablecapital.forex.forex.service.ForexService;
/**
 * 
 * @author sharmak
 *
 */
@Component
public class ForexProcessor implements Processor {

	@Autowired
	private ForexService service;


	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub

		String data = exchange.getIn().getBody(String.class);
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("yyyy-MM-dd HH:mm:ss");

		Gson gson = builder.create();

		GetForexRequest request = gson.fromJson(data, GetForexRequest.class);

		// GetTaxRequest request = objectMapper.readValue(data, GetTaxRequest.class);
		ObjectMapper mapper = new ObjectMapper();
		// Converting the Object to JSONString
		String jsonString = mapper.writeValueAsString(service.getForect(request));
		exchange.getOut().setBody(jsonString);

	}

}  
