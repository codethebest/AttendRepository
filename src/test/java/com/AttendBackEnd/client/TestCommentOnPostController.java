package com.AttendBackEnd.client;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.user.CommentOnPost;
import com.AttendBackEnd.factories.user.CommentOnPostFactory;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;
import org.w3c.dom.Comment;

import java.util.Set;

/**
 * Created by Leo on 8/30/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration (classes = App.class)
@WebAppConfiguration
public class TestCommentOnPostController extends AbstractJUnit4SpringContextTests{

    @Test
    public void testCreate() throws Exception
    {
        String URL = "http://localhost:8080/api/commentonpost";
        RestTemplate restTemplate = new RestTemplate();
        CommentOnPost event = CommentOnPostFactory.getCommentPost("Kumnadi");
        restTemplate.postForObject(URL,event, CommentOnPost.class);
    }
    @Test
    public void testFindById() throws Exception
    {
        String URL = "http://localhost:8080/api/commentonpost/{id}";
        RestTemplate restTemplate = new RestTemplate();
        CommentOnPost event = restTemplate.getForObject(URL,CommentOnPost.class,"1");
        Assert.assertNotNull(event);
        Assert.assertEquals("Having Mint Time",event.getPost());

    }

    @Test
    public void testUpdate(){
        String URI =  "http://localhost:8080/api/commentonpost/{id}";
        RestTemplate restTemplate = new RestTemplate();
        CommentOnPost event = restTemplate.getForObject(URI, CommentOnPost.class, "5");
        if(event != null) {
            String UPDATE_URI = "http://localhost:8080/api/commentonpost";
            CommentOnPost updateEvent = new CommentOnPost.Builder()
                    .copy(event)
                    .post("Wish i was the")
                    .build();
            restTemplate.put(UPDATE_URI,updateEvent);
            CommentOnPost updatedEvent = restTemplate.getForObject(URI, CommentOnPost.class, "5");

            Assert.assertEquals(updatedEvent.getPost(),"Wish i was the");
        }
    }
    @Test
    public void testFindAll(){
        String URI =  "http://localhost:8080/api/commentonpost";
        RestTemplate restTemplate = new RestTemplate();
        Set busSet = restTemplate.getForObject(URI,Set.class);
        Assert.assertTrue(busSet.size()>0);
    }
    @Test
    public void testDelete(){
        String URI =  "http://localhost:8080/api/commentonpost/{id}";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI,"8");
        CommentOnPost event= restTemplate.getForObject(URI, CommentOnPost.class, "8");
        Assert.assertNull(event);
    }
}
