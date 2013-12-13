package app

import app.exceptions.BookingException
import app.ro.SearchRequest

/**
 * User: danielwoods
 * Date: 12/12/13
 */
class PublishedRoomService {

  def ratingService

  PublishedRoom getPublishedRoom(Long id) {
    def publishedRoom = PublishedRoom.findWhere(id: id, booking: null)
    if (!publishedRoom) {
      throw new BookingException("Room not found")
    }

    def reratedRoom = ratingService.rate(publishedRoom.baseRoom, publishedRoom.checkIn, publishedRoom.checkOut)
    if ( (reratedRoom.rate < publishedRoom.rate) ||  ( (1-(publishedRoom.rate / publishedRoom.rate)) > 0.05 )) {
      reratedRoom.save flush: true
      reratedRoom.lock()
    } else {
      publishedRoom.lock()
    }
  }

  PublishedRoom save(PublishedRoom room) {
    room.save flush: true
  }

  List<PublishedRoom> doSave(SearchRequest request) {
    request.publishedRooms.each { save(it) }
  }
}
