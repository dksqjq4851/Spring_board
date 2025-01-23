package com.example.board.board;

import com.example.board.author.domain.Author;
import com.example.board.author.domain.Role;
import com.example.board.author.dtos.AuthorSaveReq;
import com.example.board.author.repository.AuthorRepository;
import com.example.board.author.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class AuthorServiceTest {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void authorSaveTest() {
        AuthorSaveReq authorSaveReq = new AuthorSaveReq("hans", "hans@naver.comcm", "1234", Role.ADMIN);

        authorService.save(authorSaveReq);

        Author author = authorRepository.findByEmail("hans@naver.comcm").orElse(null);
        Assertions.assertEquals(authorSaveReq.getEmail(), author.getEmail());
    }
        @Test
        public void findAllTest(){
            int beforesize = authorService.findAll().size();

            authorRepository.save(Author.builder().name("abc").email("aaaaaa1@naver.com").passowrd("1234").build());
            authorRepository.save(Author.builder().name("abc").email("aaaaaa2@naver.com").passowrd("1234").build());
            authorRepository.save(Author.builder().name("abc").email("aaaaaa3@naver.com").passowrd("1234").build());
            int aftersize = authorService.findAll().size();
            Assertions.assertEquals(beforesize+3,aftersize);
        }
    }


