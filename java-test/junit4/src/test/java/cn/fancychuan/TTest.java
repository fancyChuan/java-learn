package cn.fancychuan;

import org.junit.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class TTest {

    @Test
    public void add() {
        assertEquals(8, T.add(5, 3));
        assertThat(T.add(5, 3), is(8));
    }

    @Ignore
    @Test
    public void add2() {
        assertThat(T.add(5, 3), is(8));
    }

    @Before
    public void before() {
        System.out.println("before");
    }

    @After
    public void after() {
        System.out.println("after");
    }


    @BeforeClass
    public static void beforeClass() {
        System.out.println("before class");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("after class");
    }
}