package com.redbee.io

import com.redbee.io.persistence.entities.Event
import com.redbee.io.resources.EventResource
import com.redbee.io.service.EventService
import spock.lang.Specification

class EventResourceTestSpec extends Specification{

    def eventResource = new EventResource()
    def mockedService =  Mock(EventService)

    public "getAll"() {

        setup:
        eventResource.eventService = mockedService
        def event1 = new Event()
        def event2 = new Event()

        when:
        def result =  eventResource.list()

        then:
        1 * mockedService.getAll() >> {return [event1,event2]}

        result.size() == 2
        result.contains(event1)
        result.contains(event2)
    }

}
