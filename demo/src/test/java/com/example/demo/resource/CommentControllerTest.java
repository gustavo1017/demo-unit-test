package com.example.demo.resource;

import com.example.demo.model.Comment;
import com.example.demo.service.CommentService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommentControllerTest {

    private  final StringBuilder strComment = new StringBuilder("[\n" +
            "  {\n" +
            "    \"postId\": 1,\n" +
            "    \"id\": 1,\n" +
            "    \"name\": \"id labore ex et quam laborum\",\n" +
            "    \"email\": \"Eliseo@gardner.biz\",\n" +
            "    \"body\": \"laudantium enim quasi est quidem magnam voluptate ipsam eos\\ntempora quo necessitatibus\\ndolor quam autem quasi\\nreiciendis et nam sapiente accusantium\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"postId\": 1,\n" +
            "    \"id\": 2,\n" +
            "    \"name\": \"quo vero reiciendis velit similique earum\",\n" +
            "    \"email\": \"Jayne_Kuhic@sydney.com\",\n" +
            "    \"body\": \"est natus enim nihil est dolore omnis voluptatem numquam\\net omnis occaecati quod ullam at\\nvoluptatem error expedita pariatur\\nnihil sint nostrum voluptatem reiciendis et\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"postId\": 1,\n" +
            "    \"id\": 3,\n" +
            "    \"name\": \"odio adipisci rerum aut animi\",\n" +
            "    \"email\": \"Nikita@garfield.biz\",\n" +
            "    \"body\": \"quia molestiae reprehenderit quasi aspernatur\\naut expedita occaecati aliquam eveniet laudantium\\nomnis quibusdam delectus saepe quia accusamus maiores nam est\\ncum et ducimus et vero voluptates excepturi deleniti ratione\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"postId\": 1,\n" +
            "    \"id\": 4,\n" +
            "    \"name\": \"alias odio sit\",\n" +
            "    \"email\": \"Lew@alysha.tv\",\n" +
            "    \"body\": \"non et atque\\noccaecati deserunt quas accusantium unde odit nobis qui voluptatem\\nquia voluptas consequuntur itaque dolor\\net qui rerum deleniti ut occaecati\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"postId\": 1,\n" +
            "    \"id\": 5,\n" +
            "    \"name\": \"vero eaque aliquid doloribus et culpa\",\n" +
            "    \"email\": \"Hayden@althea.biz\",\n" +
            "    \"body\": \"harum non quasi et ratione\\ntempore iure ex voluptates in ratione\\nharum architecto fugit inventore cupiditate\\nvoluptates magni quo et\"\n" +
            "  }]");

    @Mock
    CommentService commentService;
    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    CommentController commentController;



    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        final ResponseEntity<String> result = new ResponseEntity<String>(strComment.toString(),HttpStatus.OK);

        List<Comment> comments = new ArrayList<>();

        try {
            JSONArray array = new JSONArray(strComment.toString());
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


        Mockito.when(restTemplate.getForEntity("https://jsonplaceholder.typicode.com/comments", String.class)).thenReturn(result);
        Mockito.when(commentService.restructure(strComment.toString())).thenReturn(comments);
    }

    @Test
    public void findAllTest(){
        final ResponseEntity response = commentController.findAll();
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());

    }
}
