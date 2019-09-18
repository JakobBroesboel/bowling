package bowling.service

import bowling.SumService
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class SumServiceSpec extends Specification implements ServiceUnitTest<SumService> {


    def "test calculateSum (Short game bowling) - [points: #points, result: #result]"() {
        when:
            List<Integer> sum = service.calculateSum(points)

        then:
            sum == result

        where:
            points                     | result
            [[2, 5]]                   | [7]
            [[2, 5], [1, 3]]           | [7, 11]
            [[10, 0], [5, 3]]          | [18, 26]
            [[7, 3], [5, 3]]           | [15, 23]
            [[7, 3], [10, 0], [4, 5]]  | [20, 39, 48]
            [[7, 3], [3, 7], [4, 5]]   | [13, 27, 36]
            [[7, 3], [5, 3], [10, 0]]  | [15, 23, 33]
            [[7, 3], [5, 3], [5, 5]]   | [15, 23, 33]
            [[7, 3], [5, 3], [0, 10]]  | [15, 23, 33]
            [[2, 3], [0, 10], [0, 10]] | [5, 15, 25]

    }


    def "test calculateSum (10 round bowling) - [points: #points, result: #result]"() {
        when:
            List<Integer> sum = service.calculateSum(points)

        then:
            sum == result

        where:
            points                                                                                               | result
            [[10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 10]] | [30, 60, 90, 120, 150, 180, 210, 240, 270, 300]
            [[10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 5]]  | [30, 60, 90, 120, 150, 180, 210, 240, 270, 295]
            [[10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 5]]  | [30, 60, 90, 120, 150, 180, 210, 240, 270, 295]
            [[10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [0, 0]]   | [30, 60, 90, 120, 150, 180, 210, 240, 260, 270]
            [[10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [4, 6]]   | [30, 60, 90, 120, 150, 180, 210, 240, 264, 284]
            [[10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [5, 5], [10]]      | [30, 60, 90, 120, 150, 180, 210, 235, 255, 275]
            [[10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [5, 5], [6]]       | [30, 60, 90, 120, 150, 180, 210, 235, 255, 271]
            [[10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [5, 5], [0]]       | [30, 60, 90, 120, 150, 180, 210, 235, 255, 265]
            [[10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [4, 5]]            | [30, 60, 90, 120, 150, 180, 210, 234, 253, 262]
            [[5, 3], [1, 0], [0, 10], [10, 0], [2, 2], [0, 0], [10, 0], [4, 5], [1, 0], [4, 5]]                  | [8, 9, 29, 43, 47, 47, 66, 75, 76, 85]
    }

    def "test calculateSum (given values) - [points: #points, result: #result]"() {
        when:
            List<Integer> sum = service.calculateSum(points)

        then:
            sum == result

        where:
            points                                                                             | result
            [[3, 7], [10, 0], [8, 2], [8, 1], [10, 0], [3, 4], [7, 0], [5, 5], [3, 2], [2, 5]] | [20, 40, 58, 67, 84, 91, 98, 111, 116, 123]
            [[2, 0], [8, 2]]                                                                   | [2, 12]
    }

    def "test isStrike - [round: #round, expected: #expected]"() {
        when:
            Boolean calculated = service.isStrike(round)

        then:
            calculated == expected

        where:
            round  | expected
            [10]   | true
            [9, 1] | false
            [4, 3] | false
            [0, 0] | false
    }

    def "test isSpare - [round: #round, expected: #expected]"() {
        when:
            Boolean calculated = service.isSpare(round)

        then:
            calculated == expected

        where:
            round  | expected
            [10]   | false
            [9, 1] | true
            [6, 4] | true
            [2, 3] | false
            [3, 7] | true
            [4, 4] | false
            [0, 0] | false
    }
}
