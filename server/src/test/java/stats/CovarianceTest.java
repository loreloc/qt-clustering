
package stats;

import org.junit.Test;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import stats.Covariance;
import stats.StatisticException;

import org.la4j.Vector;
import org.la4j.Matrix;
import org.la4j.vector.dense.BasicVector;
import org.la4j.matrix.dense.Basic2DMatrix;

/**
 * Covariance class tester.
 */
public class CovarianceTest {

	/**
	 * The accuracy of the test.
	 */
	static private final double EPS = 1e-8;

	/**
	 * Covariance.covariance() tester.
	 */
	@Test
	public void testCovariance() {
		Matrix samples = new Basic2DMatrix(3, 2);
		samples.set(0, 0,  1.0); samples.set(0, 1, -0.5);
		samples.set(1, 0,  2.0); samples.set(1, 1,  2.5);
		samples.set(2, 0,  0.0); samples.set(2, 1,  3.0);

		try {
			Matrix covariance = Covariance.covariance(samples);
			assertEquals(covariance.get(0, 0),  0.666666667, EPS);
			assertEquals(covariance.get(0, 1), -0.166666667, EPS);
			assertEquals(covariance.get(1, 0), -0.166666667, EPS);
			assertEquals(covariance.get(1, 1),  2.388888889, EPS);
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	/**
	 * Covariance.covariance() test (samples length mismatch).
	 * @throws StatisticException Thrown when a statistic exception occurs
	 */
	@Test(expected = StatisticException.class)
	public void testCovarianceLengthMismatch() throws StatisticException {
		Vector firstSamples = new BasicVector(2);
		Vector secondSamplse = new BasicVector(3);

		Covariance.covariance(firstSamples, secondSamplse);
	}
}
