package com.sk.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.sk.blog.model.user.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(nullable = false, length = 100)
	String title;
	
	@Lob // 대용량 데이터 사용 시
	String content;
	
	@ColumnDefault("0")
	int count; //조회수
	
	@CreationTimestamp
	Timestamp createDate;
	
	@ManyToOne(fetch = FetchType.EAGER) // 한명의 User가 여러 개의 게시글을 작성이 가능하다.
	@JoinColumn(name = "userId") //Field 명 userId로 생성
	User userId;
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // mappedBy : 연관 관계 주인이 아니고 board라는 필드를 참조해서 데이터를 넣어달라
	List<Reply> reply;
	
}
