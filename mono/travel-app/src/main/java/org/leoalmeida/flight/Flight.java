package org.leoalmeida.flight;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Flight extends PanacheEntity {

    public Long TravelOrderId;
    public String fromAirport;
    public String toAirport;
    
}
