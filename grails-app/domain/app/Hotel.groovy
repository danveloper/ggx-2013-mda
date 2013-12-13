package app

class Hotel {
  String name
  String location

  static hasMany = [rooms: Room]

  static constraints = {
    rooms nullable: true
  }

}
