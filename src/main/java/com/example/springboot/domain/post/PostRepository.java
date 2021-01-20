package com.example.springboot.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// DAO를 대신하는 인터페이스(꼭 인터페이스로 만들어야 한다.)  
// Post타입에서 int(Integer)타입을 primaryKey로 하겠다.
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
	
}
