package app

import org.jadira.usertype.dateandtime.joda.PersistentLocalDate
import org.joda.time.LocalDate

class PublishedRoom {
  Room baseRoom
  LocalDate checkIn
  LocalDate checkOut
  BigDecimal rate

  Booking booking

  static mapping = {
    checkIn type: PersistentLocalDate
    checkOut type: PersistentLocalDate
  }

  static constraints = {
    booking nullable: true
  }
}
