package scalablecapital.forex.forex.source;

import org.apache.camel.BeanInject;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import scalablecapital.forex.forex.dto.GetForexRequest;
import scalablecapital.forex.forex.dto.GetForexResponse;
import scalablecapital.forex.forex.processor.ForexProcessor;
/**
 * 
 * @author sharmak
 *
 */
@Component
public class ApplicationRestProcess extends RouteBuilder {

	@BeanInject
	private ForexProcessor processor;

	@Override
	public void configure() throws Exception {
		rest().post("/getforexDetails").consumes(MediaType.APPLICATION_JSON_VALUE).type(GetForexRequest.class).outType(GetForexResponse.class)
				.route().process(processor).endRest();

	}

}
