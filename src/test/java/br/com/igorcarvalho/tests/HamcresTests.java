package br.com.igorcarvalho.tests;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
//import static org.hamcrest.Matchers.*;


@SpringBootTest
class HamcresTests {

    @Test
    void assertWithHamcrestMatchers(){

        assertThat(2+1, notNullValue());
        assertThat(2+1, equalTo(3));
        assertThat("string", containsString("ring"));
    }
    @Test
    public void givenString_whenMeetsAnyOfGivenConditions_thenCorrect() {
        String str = "calligraphy";
        String start = "call";
        String end = "foo";
        assertThat(str, anyOf(startsWith(start), containsString(end)));
    }

   // Chain conditions together, test passes only when target meets all conditions, similar to logical AND:

    @Test
    public void givenString_whenMeetsAllOfGivenConditions_thenCorrect() {
        String str = "calligraphy";
        String start = "call";
        String end = "phy";
        assertThat(str, allOf(startsWith(start), endsWith(end)));
    }

}
