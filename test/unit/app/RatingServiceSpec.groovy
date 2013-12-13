package app

import grails.test.mixin.TestFor
import org.joda.time.LocalDate
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(RatingService)
class RatingServiceSpec extends Specification {

    def "test rating engine"() {
      given:
        def r = new Room(baseRate: 129.99, number: "001")
        def checkIn = LocalDate.parse("2013-12-10")
        def checkOut = LocalDate.parse("2013-12-11")
      when:
        def published = service.rate(r, checkIn, checkOut)
      then:
        published.rate == ((129.99 * 1.5) + 129.99) / 2
    }
}
