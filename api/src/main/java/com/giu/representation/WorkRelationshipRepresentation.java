package com.giu.representation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by julian on 05/08/16.
 */
public class WorkRelationshipRepresentation {
    private List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public WorkRelationshipRepresentation() {
        this.list = new ArrayList<String>();
    }

    public void generateList(){
        this.list.add("Planta Permanente");
        this.list.add("Planta Transitoria");
        this.list.add("Planta Gabinete");
        this.list.add("Planta Jerárquica");
        this.list.add("Contratados");
        this.list.add("Externos");
    }

}
