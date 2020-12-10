package com.example.demo.resource;

import com.example.demo.model.Comment;
import com.example.demo.service.CommentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class CommentControllerTest {
    private static final String OK = "OK";
    private static final List<Comment> COMMENTS_LIST = new ArrayList<>();
    private static final Comment COMMENT = new Comment();
    @Mock
    CommentService commentService;
    @InjectMocks
    CommentController commentController;

    @Before
    public void init() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllTest() throws Exception{
        final ResponseEntity response = commentController.findAll();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody(), COMMENTS_LIST);

    }
}
