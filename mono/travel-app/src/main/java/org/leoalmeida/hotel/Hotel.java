package org.leoalmeida.hotel;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Hotel extends PanacheEntity {

    public Long travelOrderId;
    public Integer nights;

    public static Hotel findByTravelOrderID(Long travelOrderID) {
        return find("travelOrderId", travelOrderID).firstResult();
    }

}