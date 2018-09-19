package cn.zhangxd.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.zhangxd.auth.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, String> {

	User findByUsername(String username);

}
