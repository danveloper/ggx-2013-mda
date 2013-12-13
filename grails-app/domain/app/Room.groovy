package app

class Room {
  enum RoomType {
    TWIN, DOUBLE, QUEEN, KING
  }

  String number
  RoomType type
  BigDecimal baseRate

  static belongsTo = [hotel: Hotel]
  static hasMany = [bookings: Booking]

  static constraints = {
    bookings nullable: true
  }
}
