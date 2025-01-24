package com.example.board.post.dtos;


import com.example.board.author.domain.Author;
import com.example.board.common.service.LoginSuccessHandler;
import com.example.board.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostSaveReq {
    @Autowired
    public LoginSuccessHandler loginSuccessHandler;
    @NotEmpty
    private String title;
    private String contents;
    private String appointment;
    private String appointmentTime;

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


//    public Post toEntity(Author author){
//        return Post.builder().title(this.title)
//                .contents(this.contents).author(author)
//                .appointment(this.appointment)
//                .appointmentTime(LocalDateTime.parse(this.appointmentTime)).build();
//    }
public Post toEntity(Author author, LocalDateTime appointmentTime){
    return Post.builder().title(this.title)
            .contents(this.contents).author(author)
            .appointment(this.appointment)
            .appointmentTime(appointmentTime).build();
}
}
