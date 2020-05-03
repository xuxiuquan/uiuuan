package com.xxq.mongo;

import com.xxq.mongo.sys.entity.User;
import com.xxq.mongo.sys.mapper.UserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MongoAdminApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testSelect() {
		System.out.println(("----- selectAll method test ------"));
		List<User> userList = userMapper.getAll();
		userList.forEach(System.out::println);
	}

}
