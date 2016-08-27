package persistence.entities;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by manuel.camisas.gays on 8/26/16.
 */
public class Event {

    @Id
    private String id;

    private Date date;


}
