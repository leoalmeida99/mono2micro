package org.leoalmeida.travelorder;

import java.util.List;
import java.util.stream.Collectors;

import org.leoalmeida.flight.Flight;
import org.leoalmeida.flight.FlightResource;
import org.leoalmeida.hotel.Hotel;
import org.leoalmeida.hotel.HotelResource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("travelorder")
public class TravelOrderResource {

    @Inject
    FlightResource flightResource;

    @Inject
    HotelResource hotelResource;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TravelOrderDTO> travelOrders() {
        return TravelOrder.<TravelOrder>listAll().stream()
                .map(
                        order -> TravelOrderDTO.of(order,
                                                    flightResource.findByTravelOrderId(order.id),
                                                    hotelResource.findByTravelOrderId(order.id)
                                )
                ).collect(Collectors.toList());
    }

    @GET
    @Path("findById")
    @Produces(MediaType.APPLICATION_JSON)
    public TravelOrder findById(@QueryParam("id") long id) {
        return TravelOrder.findById(id);
    }


    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TravelOrder newTravelOrder(TravelOrderDTO travelOrderDTO) {

        TravelOrder travelOrder = new TravelOrder();

        travelOrder.id = null;
        travelOrder.persist();

        Flight flight = new Flight();
        flight.fromAirport = travelOrderDTO.getFromAirport();
        flight.toAirport = travelOrderDTO.getToAirport();
        flight.travelOrderId = travelOrder.id;
        flightResource.newFlight(flight);

        Hotel hotel = new Hotel();
        hotel.nights = travelOrderDTO.getNights();
        hotel.travelOrderId = travelOrder.id;
        hotelResource.newHotel(hotel);

        return travelOrder;
    }
}