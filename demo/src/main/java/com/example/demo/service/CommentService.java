package com.example.demo.service;

import com.example.demo.model.Comment;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CommentService {

    public List<Comment> restructure(String comment){
        List<Comment> comments = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(comment);
            for(int i=0; i < array.length(); i++)
            {
                Comment currentComment = new Comment();
                JSONObject object = array.getJSONObject(i);
                currentComment.setId(object.getString("id"));
                currentComment.setEmail(object.getString("email"));
                currentComment.setPostId(object.getString("name"));
                comments.add(currentComment);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        return comments;
    }
}
