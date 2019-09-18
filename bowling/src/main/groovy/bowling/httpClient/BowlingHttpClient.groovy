package bowling.httpClient

import bowling.httpClient.result.PointsResult
import bowling.httpClient.request.SumRequest
import bowling.httpClient.result.ValidationResult
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client


@Client("\${integrations.skat.url}")
interface BowlingHttpClient {


    @Get
    PointsResult getScore()

    @Post
    ValidationResult validateSum(@Body SumRequest sumRequest)

}