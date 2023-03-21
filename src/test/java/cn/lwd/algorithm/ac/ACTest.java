package cn.lwd.algorithm.ac;

import org.junit.jupiter.api.*;

import java.util.List;

@DisplayName("ac-test")
class ACTest {

    private String str;

    private List<ACSensitiveWordFilterRecord> expected;

    @BeforeEach
    void init() {
        str = "这是敏感词1 这是敏感词2 这是敏感词3";
        expected = List.of(new ACSensitiveWordFilterRecord(2, 6, "敏感词1"),
                new ACSensitiveWordFilterRecord(9, 13, "敏感词2"),
                new ACSensitiveWordFilterRecord(16, 20, "敏感词3"));
    }

    @Test
    void acTest() {
        Assertions.assertArrayEquals(expected.toArray(), ACSimpleSensitiveWordFilterUtil.searchKeyword(str).toArray(),
                "ac test failed");
    }

    @AfterEach
    void destroy() {
        str = null;
        expected = null;
    }
}
