package com.example.demo.resource;

import com.example.demo.model.Comment;
import com.example.demo.service.CommentService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private RestTemplate restTemplate;
    @PostMapping
    public ResponseEntity findAll(){
        List<Comment> currentComments = new ArrayList<>();
        try{

        ResponseEntity<String> comments = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/comments", String.class);

            currentComments = commentService.restructure(comments.getBody());
        }catch (Exception e){
            e.printStackTrace();
        }

        return  new ResponseEntity(currentComments, HttpStatus.OK);
    }
}
