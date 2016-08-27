
package com.redbee.io;

import com.redbee.io.domain.Restaurant;
import com.redbee.io.resources.RestaurantResource;
import com.redbee.io.service.RestaurantService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martin on 26/08/16.
 */
public class RestaurantResourceTests {

    private RestaurantResource resource;
    private RestaurantService mockedService;

    @Before
    public void setUp(){
        this.mockedService = Mockito.mock(RestaurantService.class);
        this.resource = new RestaurantResource(this.mockedService);
    }

    @Test
    public void testGetAll() {
        List<Restaurant> mockedResult = new ArrayList<>();
        Restaurant mockedItem = Mockito.mock(Restaurant.class);
        mockedResult.add(mockedItem);
        Mockito.when(this.mockedService.getAll()).thenReturn(mockedResult);
        List<Restaurant> result = this.resource.list();
        Assert.assertNotNull(result);
        Assert.assertTrue(!result.isEmpty());
        Assert.assertEquals(result, mockedResult);
    }

}
