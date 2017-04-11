package io.github.memfis19.kotlinjackson;

import org.junit.Test;

import io.github.memfis19.kotlinjackson.data.TestJackson;
import io.github.memfis19.kotlinjackson.models.Check;

import static junit.framework.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class JacksonUnitTest {
    @Test
    public void serialize_deserialize_check_isCorrect() throws Exception {
        Check origin = new Check("Fake", true, false);
        String result = new TestJackson().serializeCheck(origin);
        Check check = new TestJackson().deserializeCheck(result);
        assertEquals(origin, check);
    }

    @Test
    public void serialize_deserialize_test_isCorrect() throws Exception {
        io.github.memfis19.kotlinjackson.models.Fake origin = new io.github.memfis19.kotlinjackson.models.Fake("Fake", true, false);
        String result = new TestJackson().serializeTest(origin);
        io.github.memfis19.kotlinjackson.models.Fake fake = new TestJackson().deserializeTest(result);
        assertEquals(origin, fake);
    }
}