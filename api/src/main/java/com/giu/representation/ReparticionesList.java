package com.giu.representation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by julian on 25/08/16.
 */
public class ReparticionesList {

        private List<String> reparticiones;

        public List<String> getReparticiones() {
            return reparticiones;
        }

        public void setReparticiones(List<String> reparticiones) {
            this.reparticiones = reparticiones;
        }

        public ReparticionesList() {
            this.reparticiones = new ArrayList<>();
        }

}
