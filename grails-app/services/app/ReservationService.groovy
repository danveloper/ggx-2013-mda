package app

import app.ro.SearchRequest
import org.joda.time.LocalDate

class ReservationService {

  List<Room> search(String location, LocalDate checkIn, LocalDate checkOut) {
    Room.where {
      hotel in Hotel.findAllByLocation(location)
    }.list().findAll { Room r ->
      if (!r.bookings) true
      else !(r.bookings.find { Booking b ->
        (checkIn in (b.publishedRoom.checkIn..b.publishedRoom.checkOut)) ||
          (checkOut in (b.publishedRoom.checkIn..b.publishedRoom.checkOut))
      })
    }
  }

  SearchRequest doSearch(SearchRequest searchRequest) {
    searchRequest.rooms = search(searchRequest.location, searchRequest.checkIn, searchRequest.checkOut)
    searchRequest
  }
}
