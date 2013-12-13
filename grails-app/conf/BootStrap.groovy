import app.*
import org.joda.time.*

class BootStrap {
  def ratingService

  def init = { servletContext ->
    /* London Hilton */
    def hilton   = new Hotel(name: "London Hilton", location: "London").save(flush: true, failOnError: true)
    def room201  = new Room(type: Room.RoomType.TWIN, number:  "201", hotel: hilton, baseRate:  79.99).save(flush: true)
    def room202  = new Room(type: Room.RoomType.TWIN, number:  "202", hotel: hilton, baseRate:  79.99).save(flush: true)
    def roomP101 = new Room(type: Room.RoomType.KING, number: "P101", hotel: hilton, baseRate: 199.99).save(flush: true)
    book roomP101, "2013-12-21", "2013-12-31"

    /* New York Marriott */
    def marriott = new Hotel(name: "New York City Marriott", location: "New York City").save(flush: true, failOnError: true)
    def room10   = new Room(type: Room.RoomType.DOUBLE, number: "10", hotel: marriott, baseRate: 129.99).save(flush: true)
    def room20   = new Room(type: Room.RoomType.DOUBLE, number: "20", hotel: marriott, baseRate: 129.99).save(flush: true)
    book room10, "2014-01-10", "2014-01-20"
    book room20, "2013-12-31", "2014-01-11"
  }

  private void book(Room room, String checkIn, String checkOut) {
    def publishedRoom = ratingService.rate(room, LocalDate.parse(checkIn), LocalDate.parse(checkOut))
    publishedRoom.save(flush: true)
    new Booking(publishedRoom: publishedRoom).save flush: true
  }

  def destroy = {
  }

}
