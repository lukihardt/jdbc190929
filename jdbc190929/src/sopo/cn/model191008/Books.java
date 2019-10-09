package sopo.cn.model191008;

import java.sql.Date;

public class Books {
	public Books() {
		super();
	}

	public Books(int id, String name, double price, int authorId, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.authorId = authorId;
		this.date = date;
	}

	private int id;
	private String name;
	private double price;
	private int authorId;
	private Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Books [id=" + id + ", name=" + name + ", price=" + price + ", authorId=" + authorId + ", date=" + date
				+ "]";
	}

}
