package com.example.springboot.web;

import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.springboot.domain.post.Post;
import com.example.springboot.domain.post.PostRepository;
import com.example.springboot.service.PostService;

// http 요청시에 @Controller, @RestController로 맵핑된 클래스가 IOC Container에 객체 생성된다.
// 서버가 실행 될때에도 @Controller가 만들어 지는 이유: 쓰레드 풀기법 때문이다.
// 쓰레드 풀 기법은 사용자의 요청이 들어오기 전에 미리 쓰레드를 만들어 놓는 기법인데
// 이 과정에서 Controller를 미리 띄어 놓게 되는 것이다.
// 원칙은 사용자의 요청시마다 생성되는 것이 맞다. 
@Controller
public class PostController {
	
	/*
	 * // DI대신 이렇게 만들면 사용자의 요청시마다 계속해서 객체를 생성한다. 
	 * // 서비스는 한 번만 객체를 생성하면 되기에(왜?) IOC 컨테이너 속 생성된 객체를 
	 * // 의존성 주입을 해서 사용하면 요청시 마다 객체를 생성하지 않는다. 
	 * private PostService postService2 = new PostService();
	 */
	private PostService postService;
	private PostRepository postRepository;
	
	public PostController(PostService postService, PostRepository postRepository) {
		System.out.println("@Controller");
		this.postService = postService;
		this.postRepository = postRepository;
	}

	// produces는 응답 할 타입을 정할 수 있다. @RestController만 사용가능하다.
	// name는 주소를 넣을때 사용하는데 주소 하나만 넣을 땐 생략이 가능하다.(디폴트)
	// 컨트롤러 함수의 파라미터는 톰켓이 가지고 있는 객체 + IOC 컨테이너 객체를 적으면 그냥 준다.
	@GetMapping("/post")
	public String findAll(Model model) {
		System.out.println("findAll() 실행됨.");
		//1. DB에 데이터 가져오기
		//2. request값 담기
		List<Post> posts = postService.글목록();
		
		// Model은 Spring이 제공해주는 request대신 사용하는 타입이다.
		// request를 사용하면 되는데 왜 Model을 따로 제공해주는지 의문이다.
		// request.addAttribute("posts", posts); 와 같은 뜻
		model.addAttribute("posts", posts);
		System.out.println(posts);
		
		return "post/list";//3. RequestDispatcher 사용
	}
	
	@GetMapping("/post/{id}")
	public String findById(@PathVariable int id, Model model) {
		System.out.println("findById() 실행됨.");
		model.addAttribute("post",postService.글상세보기(id));
		return "post/detail";
	}
	
	@PutMapping("/post/{id}")
	public void update() {
		System.out.println("update() 실행됨.");
	}
	
	@DeleteMapping("/post/{id}")
	public void delete(int id) {
		System.out.println("delete() 실행됨.");
	}
	
	@PostMapping("/post")
	public String save(Post post) { //x-www-form-urlencoded
		System.out.println("post: " + post);
		
		Post postEntity = postRepository.save(post);
		System.out.println("postEntity: " + postEntity);
		// redirect: 라고 사용하면 뒤에 적힌 URL 요청을 한번 더 하겠다 라는 뜻이다.
		// 밑에 적힌 "redirect:/post"를 보면 "/post"가 URL요청으로 리턴되었다는 뜻입니다.
		// 그래서 "/post"로 맵핑된 함수가 호출 됩니다. 
		// 모두 @GetMapping("/post")로 맵핑된 클래스만 호출됩니다.
		return "redirect:/post"; // response.sendRedirect
	}
	
	
}
