package app

import app.exceptions.BookingException

class BookingService {

  def ratingService

  Booking bookRoom(PublishedRoom room) {
    def booking = new Booking(publishedRoom: room)
    if (!booking.save(flush: true)) {
      throw new BookingException("Errors while saving booking, errors: ${booking.errors}")
    }
    booking
  }

  Booking notifyHotel(Booking booking) {
    // do some external call to the hotel
    booking
  }

}
