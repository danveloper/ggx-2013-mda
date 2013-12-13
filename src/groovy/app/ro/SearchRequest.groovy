package app.ro

import app.PublishedRoom
import app.Room
import org.joda.time.LocalDate

class SearchRequest {
  String location
  LocalDate checkIn
  LocalDate checkOut

  /* "Working" properties */
  List<Room> rooms
  List<PublishedRoom> publishedRooms
}
