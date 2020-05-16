package io.group1.GBUH.booking.controller;

import io.group1.GBUH.booking.entity.Booking;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Environment env;
	
	@RequestMapping("/")
	public String home() {
		// This is useful for debugging
		// When having multiple instance of gallery service running at different ports.
		// We load balance among them, and display which instance received the request.
		return "Hello from Booking Service running at port: " + env.getProperty("local.server.port");
	}
  
	@RequestMapping("/tickets")
	public Booking getBooking() {
		// create gallery object
		Booking booking = new Booking();
		
		// get list of available images 
		List<Object> ticket = restTemplate.getForObject("http://ticket-service/tickets/", List.class);
		booking.setTicket(ticket);
	
		return booking;
	}
	@RequestMapping("/{ngaydi}/{ngayden}/{noidi}/{noiden}/{nguoilon}/{treem}/{embe}")
	public Booking getTicket(@PathVariable String ngaydi,@PathVariable String ngayden,@PathVariable String noidi,@PathVariable String noiden,@PathVariable String nguoilon,@PathVariable String treem,@PathVariable String embe) throws ParseException {
		Booking booking = new Booking();
		JSONObject timve = new JSONObject(); 
		List<Object> veDi = new ArrayList<>();
		List<Object> veVe = new ArrayList<>();
		String result = restTemplate.getForObject("http://ticket-service/tickets/", String.class);
			Object obj = new JSONParser().parse(result);
			JSONArray  ja = (JSONArray) obj;
			int tongnguoi = Integer.parseInt(nguoilon) + Integer.parseInt(treem);
			for (int i = 0; i < ja.size(); i++) {
				JSONObject tmp = (JSONObject) ja.get(i);
				long veconlai = (long) tmp.get("available");
				if(tmp.get("flightFrom").equals(noidi) && tmp.get("flightTo").equals(noiden) && tmp.get("date").equals(ngaydi) && veconlai > tongnguoi) {
					veDi.add(tmp);
				}
				if(tmp.get("flightTo").equals(noidi) && tmp.get("flightFrom").equals(noiden) && tmp.get("date").equals(ngayden) && veconlai > tongnguoi) {
					veVe.add(tmp);
				}
			}
			timve.put("Ve Di", veDi);
			timve.put("Ve Ve", veVe);
			JSONArray array = new JSONArray();
			array.add(timve);
			List<Object> ve = (List<Object>) array;
			booking.setTicket(ve);
			return booking;

		
	
		
	}
	// -------- Admin Area --------
	// This method should only be accessed by users with role of 'admin'
	// We'll add the logic of role based auth later
	@RequestMapping("/admin")
	public String homeAdmin() {
		return "This is the admin area of Gallery service running at port: " + env.getProperty("local.server.port");
	}
}