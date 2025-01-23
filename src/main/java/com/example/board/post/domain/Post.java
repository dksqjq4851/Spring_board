package com.example.board.post.domain;


import com.example.board.author.domain.Author;
import com.example.board.common.domain.BaseTimeEntity;
import com.example.board.post.dtos.PostDetailRes;
import com.example.board.post.dtos.PostListRes;
import com.example.board.post.dtos.PostUpdateReq;
import com.example.board.post.service.PostService;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
@Entity
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 3000)
    private String contents;
    private String appointment;
    private LocalDateTime appointmentTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    public PostListRes toListDto(){
        return PostListRes.builder().id(this.id).title(this.title).authorEmail(this.author.getEmail()).build();
    }

    public PostDetailRes detailFromEntity(){
        return PostDetailRes.builder().id(this.id).title(this.title).contents(this.contents).authorEmail(this.author.getEmail()).createdTime(getCreatedTime()).updatedTime(getUpdateTime()).build();
//        deletail/{id} => id, title, contents, authorEmail, createdTime, updatedTime
    }

    public void postUpdate(PostUpdateReq dto){
        this.title = dto.getTitle();
        this.contents = dto.getContents();
    }
    public void updateAppointment(String appointment){
        this.appointment = appointment;
    }
}
//Post 객체
// -id, title(length=50, notnull), contents(length=3000), String appointment, LocalDateTime appointmentTime
// -author와의 관계 n:1 (컬럼명 author_id)
