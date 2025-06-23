package com.example.demo.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Users.Users;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	List<Book> findByUser(Users user);
	
	@Query("SELECT DISTINCT b.genre FROM Book b WHERE b.user.username = :username")
	List<String> findDistinctGenresByuser(@Param("username") String username);
	
	List<Book> findByUser_UsernameAndGenreIgnoreCase(String username, String genre);
	
	@Query("SELECT b FROM Book b WHERE b.user.username = :username " +
		   "AND (LOWER(b.name) LIKE :query OR LOWER(b.author) LIKE :query OR LOWER(b.genre) LIKE :query) " +
		   "AND (:rating IS NULL OR b.rating = :rating) " +
		   "AND (:status IS NULL OR b.status = :status)")
	List<Book> searchBooks(@Param("username") String username,@Param("query") String query, @Param("rating") Integer rating, @Param("status") BookStatus status);
}
