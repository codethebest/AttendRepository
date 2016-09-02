package com.AttendBackEnd.testFactories;

import com.AttendBackEnd.domain.user.PostAnEvent;
import com.AttendBackEnd.factories.user.PostAnEventFactory;
import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Leo on 4/24/2016.
 */
public class PostAnEventFactoryTest {

    @Test
    public void testPerson()throws Exception
    {
        PostAnEvent postAnEvent = PostAnEventFactory.getPostAnEvent ("Pool party happening lads","poolparty");
        Assert.assertNotNull(postAnEvent.getPost());
    }


}
