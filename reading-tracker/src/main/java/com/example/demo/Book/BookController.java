package com.example.demo.Book;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("api/home")
public class BookController {

	@Autowired
	private BookService bookService;
	
	String username;
	
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks(){
		username = SecurityContextHolder.getContext().getAuthentication().getName();
		List<Book> books = bookService.getBooks(username);
		return ResponseEntity.ok(books);
	}
	
	@GetMapping("/view/book")
	public ResponseEntity<?> getBook(@RequestParam Long id){
		return ResponseEntity.ok(bookService.getBook(id));
	}
	
	@GetMapping("genres/list")
	public ResponseEntity<?> getAllGenres(){
		username = SecurityContextHolder.getContext().getAuthentication().getName();
		List<String> genres = bookService.getAllGenres(username);
		return ResponseEntity.ok(genres);
	}
	
	@GetMapping("/genres/books")
	public ResponseEntity<?> getBooksByGenre(@RequestParam String genre){
		username = SecurityContextHolder.getContext().getAuthentication().getName();
		List<Book> books = bookService.getBooksByGenre(username, genre);
		return ResponseEntity.ok(books);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Book> addBook(
			@RequestParam("name") String name,
	        @RequestParam("author") String author,
	        @RequestParam(value = "image", required = false) MultipartFile image,
	        @RequestParam("description") String description,
	        @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
	        @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
	        @RequestParam(value = "rating", required = false) Integer rating,
	        @RequestParam("genre") String genre,
	        @RequestParam("status") BookStatus status
//	        Principal principal
			){
		username = SecurityContextHolder.getContext().getAuthentication().getName();
		return ResponseEntity.ok(bookService.addBook(username, name, author, image, description, startDate, endDate, rating, genre, status)); 
	}
	
	@PutMapping("/update")
	public ResponseEntity<Book> updateBook(@RequestBody Book book, @RequestParam Long id){
		username = SecurityContextHolder.getContext().getAuthentication().getName();
		return ResponseEntity.ok(bookService.updateBook(username, book, id));
	}
	
	@DeleteMapping()
	public ResponseEntity<?> deleteBook(@RequestParam Long id){
		username = SecurityContextHolder.getContext().getAuthentication().getName();
		bookService.deleteBook(username, id);
		return ResponseEntity.ok("Deleted Successfully");
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> search(@RequestParam String query, @RequestParam(required = false) Integer rating, @RequestParam(required = false) BookStatus status){
		username = SecurityContextHolder.getContext().getAuthentication().getName();
		return ResponseEntity.ok(bookService.search(username, query, rating, status));
	}
}
