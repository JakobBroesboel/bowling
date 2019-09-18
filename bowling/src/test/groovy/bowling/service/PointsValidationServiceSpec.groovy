package bowling.service

import bowling.PointsValidationService
import bowling.SumService
import bowling.httpClient.BowlingHttpClient
import bowling.httpClient.result.PointsResult
import spock.lang.Specification

class PointsValidationServiceSpec extends Specification {

    PointsValidationService pointsValidationService

    void setup() {
        pointsValidationService = new PointsValidationService(sumService: Mock(SumService), bowlingHttpClient: Mock(BowlingHttpClient))
    }

    void "test run"() {
        when:
            pointsValidationService.run()

        then:
            1 * pointsValidationService.bowlingHttpClient.getScore() >> new PointsResult(points: _, token: _)
            1 * pointsValidationService.sumService.calculateSum(_)
            1 * pointsValidationService.bowlingHttpClient.validateSum(_)
            0 * _
    }
}
