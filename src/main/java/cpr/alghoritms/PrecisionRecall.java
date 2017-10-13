package cpr.alghoritms;

public interface PrecisionRecall {

    /**
     * Returns the recall.
     *
     * @return
     */
    Double getRecall();

    /**
     * Returns the precision.
     *
     * @return
     */
    Double getPrecision();

    /**
     * Returns the FScore.
     *
     * @return
     */
    Double getFScore();

    /**
     * Returns an approximated double.
     *
     * @param N
     * @return
     */
    default Double approximate(Double N) {
        return Math.round(N*1000D)/1000D;
    }

}
