package com.example.demo.Book;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Users.UserRepository;
import com.example.demo.Users.Users;

@Service
public class BookService {

    private final AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BookRepository bookRepository;

    BookService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
	
	public List<Book> getBooks(String username){
		List<Book> books = bookRepository.findByUser(findUser(username));
//		System.out.print(books.get(0).getImage());
		return books;
	}
	
	public Optional<Book> getBook(Long id) {
		return bookRepository.findById(id);
	}
	
	public List<String> getAllGenres(String username){
		return bookRepository.findDistinctGenresByuser(username);
	}
	
	public List<Book> getBooksByGenre(String username, String genre){
		return bookRepository.findByUser_UsernameAndGenreIgnoreCase(username, genre);
	}
	
	public Book addBook(
			String username, 
			String name,
	        String author,
	        MultipartFile image,
	        String description,
	        LocalDate startDate,
	        LocalDate endDate,
	        Integer rating,
	        String genre,
	        BookStatus status) {
		String imageUrl = null;

	    if (image != null && !image.isEmpty()) {
	        try {
	            String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
	            Path uploadPath = Paths.get("uploads"); // Make sure this folder exists
	            Files.createDirectories(uploadPath);
	            Path filePath = uploadPath.resolve(fileName);
	            Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
	            imageUrl = "/uploads/" + fileName; // This will be served as a static file
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
		Book book = new Book();
		book.setName(name);
		book.setAuthor(author);
		book.setDescription(description);
		book.setImage(imageUrl);
		book.setStartDate(startDate);
		book.setEndDate(endDate);
		book.setRating(rating);
		book.setGenre(genre);
		book.setStatus(status);
		book.setUser(findUser(username)); 
		return bookRepository.save(book);
	}
	
	public Book updateBook(String username, Book bookData, Long id) {
//		if(bookRepository.findById(id).isPresent()) {
//			book.setId(id);
//		}
		
		Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));

	    if (bookData.getName() != null) book.setName(bookData.getName());
	    if (bookData.getAuthor() != null) book.setAuthor(bookData.getAuthor());
	    if (bookData.getStatus() != null) book.setStatus(bookData.getStatus());
	    if (bookData.getDescription() != null) book.setDescription(bookData.getDescription());
	    if (bookData.getImage() != null) book.setImage(bookData.getImage());
	    if (bookData.getStartDate() != null) book.setStartDate(bookData.getStartDate());
	    if (bookData.getEndDate() != null) book.setEndDate(bookData.getEndDate());
	    if (bookData.getGenre() != null) book.setGenre(bookData.getGenre());
	    if (bookData.getRating() != null) book.setRating(bookData.getRating());

		book.setUser(findUser(username));
		return bookRepository.save(book);
	}
	
	public void deleteBook(String username, Long id) {
		if(!bookRepository.existsById(id)) {
			new RuntimeException("Book not found");
		}
		bookRepository.deleteById(id);
	}
	
	public List<Book> search(String username,String query, Integer rating, BookStatus status){
		return bookRepository.searchBooks(username, "%" + query.toLowerCase() + "%", rating, status);
	}
	
	
	
	private Users findUser(String username) {
		return userRepository.findByUsername(username);
	}
}
