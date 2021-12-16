package com.sk.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sk.blog.model.user.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
