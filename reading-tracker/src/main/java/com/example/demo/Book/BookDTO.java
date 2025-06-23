package com.example.demo.Book;

import java.time.LocalDate;

public class BookDTO {
	
	private Long id;
	private String name;
	private String author;
	private String image;
	private String genre;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	private Integer rating;
	private BookStatus status;
	public BookDTO() {
		super();
	}
	public BookDTO(Long id, String name, String author, String image, String genre, String description,
			LocalDate startDate, LocalDate endDate, Integer rating, BookStatus status) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.image = image;
		this.genre = genre;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.rating = rating;
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public BookStatus getStatus() {
		return status;
	}
	public void setStatus(BookStatus status) {
		this.status = status;
	}
	
	
}
