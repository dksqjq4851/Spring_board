package com.example.board.author.domain;


import com.example.board.author.dtos.AuthorDetailRes;
import com.example.board.author.dtos.AuthorListRes;
import com.example.board.author.dtos.AuthorUpdateReq;
import com.example.board.common.domain.BaseTimeEntity;
import com.example.board.post.domain.Post;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
@Builder
public class Author extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20, nullable = false)
    private String name;
    @Column(length = 30,unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String passowrd;
//    enum은 기본적으로 숫자값으로 db에 들어감으로, 별도로 STRING 저장 필요
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;
//    OneToMany의 기본값이 fetch lazy라 별도의 설정은 없음.
//    mappedBy에 ManyToOne 쪽에 변수명을 문자열로 지정.
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
//    빌더패턴에서 변수 초기화(디폴트값)시 Builder.Default 어노테이션 사용
    @Builder.Default
    private List<Post> posts = new ArrayList<>();

    public AuthorListRes listDtoFromEntity(){
        return AuthorListRes.builder().id(this.id).email(this.email).name(this.name).build();
    }

    public Author(String name, String email, String passowrd) {
        this.name = name;
        this.email = email;
        this.passowrd = passowrd;
    }
    public AuthorDetailRes detailFromEntity(){

        return  AuthorDetailRes.builder().createdTime(this.getCreatedTime()).id(this.id).name(this.name)
                .password(this.passowrd).email(this.email).role(this.role).postCount(this.posts.size()).build();
    }
    public void updateProfile(AuthorUpdateReq dto){
        this.name = dto.getName();
        this.passowrd = dto.getPassword();
    }

}
