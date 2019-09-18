package bowling


class SumService {

    /**
     * Calculates the point sum og each round as per the rules of 10 pin bowling
     * @param points - a list containing the amount of knocked pins from each consecutive round.
     * @return a list of summed points
     */
    List<Integer> calculateSum(List<List<Integer>> points) {
        points = points.collect { it == [10, 0] ? [10] : it } //Converts strike rounds from [10, 0] to [10]
        List<Integer> sum = []

        points.collate(3, 1).take(10).each { //Collates rounds into list of the current round plus the two consecutive rounds for easier bonuspoint calculation.
            List<Integer> round = it[0] //Current round
            List<Integer> bonusRolls = it.drop(1).flatten() //two consecutive rounds in a flattened list

            int roundPoints = round.sum() //points from current round
            int bonusPoints = 0

            if (isStrike(round)) {
                bonusPoints = bonusRolls.take(2).sum(0) //If strike, add points from the two following rolls
            } else if (isSpare(round)) {
                bonusPoints = bonusRolls.take(1).sum(0) //If Spare, add points from the next roll
            }

            sum << roundPoints + bonusPoints + (sum.empty ? 0 : sum.last()) //Add up points, bonuspoints and points from the previous round.
        }

        return sum
    }

    /**
     * Deduces if the given round is a strike. i.e if all 10 pins are knocked down in the first roll.
     * Assuming Strike rounds has been converted from [10, 0] to [10]
     * @param points
     * @return
     */
    private Boolean isStrike(List<Integer> points) {
        return points == [10]
    }

    /**
     * Deduces if the given round is a spare. i.e if all 10 pins are knocked down in two rolls in a round.
     * Assuming Strike rounds has been converted from [10, 0] to [10]
     * @param points
     * @return
     */
    private Boolean isSpare(List<Integer> points) {
        return !isStrike(points) && points.sum() == 10
    }

}


