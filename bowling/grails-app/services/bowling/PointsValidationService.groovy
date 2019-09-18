package bowling

import bowling.httpClient.BowlingHttpClient
import bowling.httpClient.result.PointsResult
import bowling.httpClient.result.ValidationResult
import bowling.httpClient.request.SumRequest
import org.springframework.beans.factory.annotation.Autowired



class PointsValidationService {



    SumService sumService

    @Autowired
    BowlingHttpClient bowlingHttpClient


    /**
     * Retrieves a set of points from the Skat end point
     * @return
     */
    PointsResult getPoints() {
        return bowlingHttpClient.getScore()
    }

    /**
     * Validates a point sum using the Skat end point
     * @param sumRequest
     * @return
     */
    ValidationResult validateSum(SumRequest sumRequest) {
        return bowlingHttpClient.validateSum(sumRequest)
    }


    /**
     * Retrieves a set of points from the skat, calculates the point sum and validates it at the skat end point.
     * @return
     */
    ValidationResult run() {
        PointsResult pointsResult = getPoints()

        List<Integer> sum = sumService.calculateSum(pointsResult.points)
        SumRequest sumRequest = new SumRequest(points: sum, token: pointsResult.token)

        ValidationResult validationResult = validateSum(sumRequest)

        return validationResult
    }

}

