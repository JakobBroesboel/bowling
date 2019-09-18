package bowling.service

import bowling.PointsValidationService
import bowling.httpClient.result.PointsResult
import bowling.httpClient.result.ValidationResult
import grails.testing.mixin.integration.Integration
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

@Integration
class PointsValidationServiceIntegrationSpec extends Specification {

    @Autowired
    PointsValidationService pointsValidationService


    def "test getPoints"() {
        when:
            PointsResult pointsResult = pointsValidationService.getPoints()

        then:
            pointsResult != null
            pointsResult.token != null
            pointsResult.points != null
    }


    def "test run"() {
        when:
            ValidationResult validationResult = pointsValidationService.run()

        then:
            validationResult != null
            validationResult.success == true
    }
}
