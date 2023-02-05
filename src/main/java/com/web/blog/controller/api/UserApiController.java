package com.web.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.dto.ResponseDto;
import com.web.blog.model.RoleType;
import com.web.blog.model.User;
import com.web.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService; //bean에 등록된 UserService를 사용하기 위해 DI 받음.
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		
		System.out.println("username: "+user.getUsername());
		System.out.println("password: "+user.getPassword());
		System.out.println("email: "+user.getEmail());
		
		user.setRole(RoleType.USER); //role은 직접 셋팅. enum으로.
		int result = userService.save(user); // 1, -1 //save의 action은 userService의 트랜잭션에서 수행한다.
		
		// 응답성공시 200을 직접 입력해서 보내는 것보다 enum으로 200=OK로 보낸다. 앞에는 결과상태 뒤에는 데이터.
		return new ResponseDto<Integer>(HttpStatus.OK, result);
	}
}
