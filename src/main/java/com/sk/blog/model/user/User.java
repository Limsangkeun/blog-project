package com.sk.blog.model.user;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //DB의 넘버링 전략을 따라감
	Long id; //auto_increment 사용
	
	@Column(nullable = false, length = 30)
	String username;
	
	@Column(nullable = false, length = 100)
	String password;
	
	@Column(nullable = false, length = 50)
	String email;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	ERole role;
	
	@CreationTimestamp //시간이 자동으로 입력
	Timestamp createDate;
	
	@Column
	boolean enabled;
}
