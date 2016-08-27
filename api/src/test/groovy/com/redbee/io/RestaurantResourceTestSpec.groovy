package com.redbee.io

import com.redbee.io.domain.Restaurant
import com.redbee.io.resources.RestaurantResource
import com.redbee.io.service.RestaurantService
import spock.lang.Specification

/**
 * Created by martin on 26/08/16.
 */
class RestaurantResourceTestSpec extends Specification{

    def restaurantResource = new RestaurantResource()
    def mockedService =  Mock(RestaurantService)
    def mockedRepo = Mock(RestaurantRepository)

    public "getAll"() {

        setup:
        restaurantResource.restaurantService = mockedService

        when:
        def result =  restaurantResource.list()

        then:
        result.size() == 1
        1 * mockedService.getAll() >> {return [new Restaurant()]}
    }

    public "create"() {
        setup:
        restaurantResource.restaurantService = mockedService
        restaurantResource.restaurantService.restaurantRepository = mockedRepo
        Restaurant rest = new Restaurant([])

        when:
        def result = restaurantResource.create()

        then:
        result.
    }
}