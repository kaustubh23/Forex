package scalablecapital.forex.forex.service;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import scalablecapital.forex.forex.dto.GetForexRequest;
import scalablecapital.forex.forex.dto.GetForexResponse;

/**
 * 
 * @author sharmak
 *
 */
@Service
public class ForexService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	Map<String, String> rates = new HashMap<String, String>();
	Map<String, Integer> forexRequest = null;

	public GetForexResponse getForect(GetForexRequest request) {

		String url = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
		DecimalFormat df = new DecimalFormat("#.##");
		GetForexResponse response = null;

		rates = parseDocFromURL(url);
		if ("EUR".equalsIgnoreCase(request.getFrom())) {
			if (rates.containsKey(request.getTo())) {
				response = mapResponse("found");
				response.setTotalAmount(Double
						.valueOf(df.format(request.getAmount() * Double.parseDouble(rates.get(request.getTo())))));
				setTrys(request.getTo(), request.getFrom());
			} else {
				response = mapResponse("missing");
			}
		} else if ("EUR".equalsIgnoreCase(request.getTo())) {

			if (rates.containsKey(request.getFrom())) {
				response = mapResponse("found");
				double amount = 100 / Double.parseDouble(rates.get(request.getFrom())) / 100;
				response.setTotalAmount(Double.valueOf(df.format(request.getAmount() * amount)));

			} else {
				response = mapResponse("missing");
			}

			setTrys(request.getTo(), request.getFrom());
		} else {
			if (rates.containsKey(request.getTo()) && rates.containsKey(request.getFrom())) {
				response = mapResponse("found");
				double to = Double.parseDouble(rates.get(request.getTo()));
				double from = Double.parseDouble(rates.get(request.getFrom()));
				double result = 0.0;
				result = to / from;
				response.setTotalAmount(Double.valueOf(df.format(request.getAmount() * result)));
				setTrys(request.getTo(), request.getFrom());

			} else {
				response = mapResponse("missing");
			}
		}

		return response;
	}

	public GetForexResponse mapResponse(String logic) {
		GetForexResponse response = new GetForexResponse();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		String todaysdate = dateFormat.format(date);
		response.setDate(todaysdate);
		if ("found".equalsIgnoreCase(logic)) {
			response.setStatus("200");
			response.setStatus("Success");
		} else {
			response.setStatus("200");
			response.setStatus("Currency code not found");

		}

		return response;

	}

	Map<String, String> parseDocFromURL(String URL) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new URL(URL).openStream()));
			NodeList elementsList = doc.getElementsByTagName("Cube");
			for (int i = 0; i < elementsList.getLength(); i++) {
				Node outterElement = elementsList.item(i);
				if (outterElement.getAttributes().getNamedItem("time") != null) {
					String date = outterElement.getAttributes().getNamedItem("time").getNodeValue();
					NodeList childs = outterElement.getChildNodes();
					for (int k = 0; k < childs.getLength(); k++) {
						Node innerElement = childs.item(k);
						NamedNodeMap attributes = innerElement.getAttributes();
						if (attributes != null) {
							for (int j = 0; j < attributes.getLength(); j++) {
								rates.put(attributes.getNamedItem("currency").getNodeValue(),
										attributes.getNamedItem("rate").getNodeValue());
							}
						}
					}

				}
			}
		} catch (SAXException | IOException | ParserConfigurationException e) {
			log.debug(e.getMessage());
		}
		return rates;
	}

	public void setTrys(String to, String from) {
		if (forexRequest == null) {
			forexRequest = new HashMap<String, Integer>();
			forexRequest.put(to + "-" + from, 1);
		} else if (forexRequest.containsKey(to + "-" + from)) {
			forexRequest.replace(to + "-" + from, forexRequest.get(to + "-" + from) + 1);
		} else {
			forexRequest.put(to + "-" + from, 1);
		}
	}

}
