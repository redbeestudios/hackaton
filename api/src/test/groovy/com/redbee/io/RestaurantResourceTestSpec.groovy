package com.redbee.io

import com.redbee.io.representation.RestaurantRepresentation
import com.redbee.io.resources.RestaurantResource
import com.redbee.io.service.RestaurantService
import persistence.entities.Restaurant
import persistence.repositories.RestaurantRepository
import spock.lang.Specification

/**
 * Created by martin on 26/08/16.
 */
class RestaurantResourceTestSpec extends Specification{

    def restaurantResource = new RestaurantResource()
    def mockedService =  Mock(RestaurantService)

    public "getAll"() {

        setup:
        restaurantResource.restaurantService = mockedService

        when:
        def result =  restaurantResource.list()

        then:
        result.size() == 1
        1 * mockedService.getAll() >> {return [new Restaurant("El palacio de la pizza")]}
    }

    public "create"() {
        setup:
        restaurantResource.restaurantService = mockedService
        Restaurant rest = new Restaurant("El palacio de la pizza")

        when:
        def result = restaurantResource.create(new RestaurantRepresentation([name: "El palacio de la pizza"]))

        then:
        1 * mockedService.create(_) >> {return new RestaurantRepresentation([name: "El palacio de la pizza"])}
        result.name == "El palacio de la pizza"

    }
}