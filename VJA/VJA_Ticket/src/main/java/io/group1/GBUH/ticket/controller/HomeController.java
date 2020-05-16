package io.group1.GBUH.ticket.controller;

import io.group1.GBUH.ticket.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {
	@Autowired
	private Environment env;

	@RequestMapping("/")
	public String home() {
		// This is useful for debugging
		// When having multiple instance of image service running at different ports.
		// We load balance among them, and display which instance received the request.
		return "Hello from Image Service running at port: " + env.getProperty("local.server.port");
	}
		
	@RequestMapping("/tickets")
	public List<Ticket> getTickets() {
		List<Ticket> ticket = Arrays.asList(
			new Ticket(1,50000000,"HCM","HAN","adult","1586531487637",15,"A"),
			new Ticket(2,75000000,"HAN","HCM","adult","1586931887924",15,"A"),
			new Ticket(3,25000000,"DAN","DNA","child","1586531487637",20,"C")
			);
		return ticket;
	}
}