package com.redbee.io

import com.redbee.io.persistence.entities.Restaurant
import com.redbee.io.representation.RestaurantRepresentation
import com.redbee.io.resources.RestaurantResource
import com.redbee.io.service.RestaurantService
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
        def rest = new Restaurant()
        rest.setName("El palacio de la pizza")

        when:
        def result =  restaurantResource.list()

        then:
        result.size() == 1
        1 * mockedService.getAll() >> {return [rest]}
    }

    public "create"() {
        setup:
        restaurantResource.restaurantService = mockedService
        def rest = new Restaurant()
        rest.setName("El palacio de la pizza")
        def rep = new RestaurantRepresentation()
        rep.setName("El palacio de la pizza")

        when:
        def result = restaurantResource.create(rep)

        then:
        1 * mockedService.create(_) >> {return rep}
        result.name == "El palacio de la pizza"

    }
}