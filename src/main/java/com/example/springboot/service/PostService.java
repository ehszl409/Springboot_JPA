package com.example.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springboot.domain.post.Post;
import com.example.springboot.domain.post.PostRepository;

@Service
public class PostService {
	
	private PostRepository postRepository;
	
//	public PostService() {
//		System.out.println("@Service");
//	}
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	public List<Post> 글목록(){
		return postRepository.findAll();
	}
	
	public Post 글상세보기(int id){
		// 없을 수 도 있으니까 없을 경우일때 리턴 해주는 값을 정해줘야 한다.
		return postRepository.findById(id).orElse(new Post());
	}
	
	public Post 글저장(Post post) {
		Post postEntity = postRepository.save(post);
		return postEntity;
	}
}
