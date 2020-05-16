package io.group1.GBUH.ticket.entity;

public class Ticket {
    private Integer id;
    private long price;
    private String flightFrom;
    private String flightTo;
    private String type;
    private String date;
    private Integer available;
    private String level;
	public Ticket(Integer id, long price, String flightFrom, String flightTo, String type, String date,
			Integer available, String level) {
		super();
		this.id = id;
		this.price = price;
		this.flightFrom = flightFrom;
		this.flightTo = flightTo;
		this.type = type;
		this.date = date;
		this.available = available;
		this.level = level;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getFlightFrom() {
		return flightFrom;
	}
	public void setFlightFrom(String flightFrom) {
		this.flightFrom = flightFrom;
	}
	public String getFlightTo() {
		return flightTo;
	}
	public void setFlightTo(String flightTo) {
		this.flightTo = flightTo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getAvailable() {
		return available;
	}
	public void setAvailable(Integer available) {
		this.available = available;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
    
}