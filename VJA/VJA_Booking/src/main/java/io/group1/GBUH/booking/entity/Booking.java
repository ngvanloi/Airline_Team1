package io.group1.GBUH.booking.entity;

import java.util.List;

public class Booking {
    private List<Object> ticket;

    public Booking(List<Object> tickets) {
        this.ticket = tickets;
    }

    public Booking() {
    }

	public List<Object> getTicket() {
		return ticket;
	}

	public void setTicket(List<Object> ticket) {
		this.ticket = ticket;
	}
    
}