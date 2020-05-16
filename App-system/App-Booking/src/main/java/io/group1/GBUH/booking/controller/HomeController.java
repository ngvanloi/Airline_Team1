package io.group1.GBUH.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.group1.GBUH.booking.entity.Booking;


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
  
	@RequestMapping("/ticket/{fim}")
	public Booking getBooking(@PathVariable String fim) {
		
		Booking booking = new Booking();
		org.json.simple.JSONArray array = new org.json.simple.JSONArray();
		String[] hang = fim.split(",");
		for (int i = 0; i < hang.length; i++) {
			String result = restTemplate.getForObject("http://"+hang[i]+"-gateway/"+hang[i]+"/tickets", String.class);	
			try {
				Object obj = new org.json.simple.parser.JSONParser().parse(result);
				org.json.simple.JSONObject ob = (org.json.simple.JSONObject) obj;
				org.json.simple.JSONArray arr = (org.json.simple.JSONArray) ob.get("ticket");
				org.json.simple.JSONObject newObj = new org.json.simple.JSONObject();
				newObj.put(hang[i], arr);
				array.add(newObj);
			}catch (org.json.simple.parser.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		booking.setTicket((List<Object>) array);
		return booking;
	}
	
	@RequestMapping("/{fim}/{ngaydi}/{ngayden}/{noidi}/{noiden}/{nguoilon}/{treem}/{embe}")
	public Booking getBooking(@PathVariable String fim,@PathVariable String ngaydi,@PathVariable String ngayden,@PathVariable String noidi,@PathVariable String noiden,@PathVariable String nguoilon,@PathVariable String treem,@PathVariable String embe) {
		Booking booking = new Booking();
		org.json.simple.JSONArray array = new org.json.simple.JSONArray();
		String[] hang = fim.split(",");
		for (int i = 0; i < hang.length; i++) {
			String result = restTemplate.getForObject("http://"+hang[0]+"-gateway/"+hang[0]+"/"+ngaydi+"/"+ngayden+"/"+noidi+"/"+noiden+"/"+nguoilon+"/"+treem+"/"+embe, String.class);	
			try {
				Object obj = new org.json.simple.parser.JSONParser().parse(result);
				org.json.simple.JSONObject ob = (org.json.simple.JSONObject) obj;
				org.json.simple.JSONArray arr = (org.json.simple.JSONArray) ob.get("ticket");
				org.json.simple.JSONObject newObj = new org.json.simple.JSONObject();
				newObj.put(hang[i], arr);
				array.add(newObj);
			}catch (org.json.simple.parser.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		booking.setTicket((List<Object>) array);
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