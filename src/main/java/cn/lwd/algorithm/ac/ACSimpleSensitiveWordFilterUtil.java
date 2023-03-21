package cn.lwd.algorithm.ac;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 简单的关键词匹配工具类
 */
@Slf4j
public class ACSimpleSensitiveWordFilterUtil {

    private ACSimpleSensitiveWordFilterUtil() {
    }

    private static class Singleton {
        private static final ACSensitiveWordFilter AC_SENSITIVE_WORD_FILTER;

        static {
            List<String> list;
            try {
                list = Files.readAllLines(Path.of(Objects.requireNonNull(Singleton.class.getResource(
                        "/ac/sensitive-word.txt")).toURI()));
            } catch (IOException | URISyntaxException e) {
                log.warn("sensitive-word.txt 加载失败", e);
                list = Collections.emptyList();
            }
            AC_SENSITIVE_WORD_FILTER = new ACSensitiveWordFilter(list);
        }
    }

    /**
     * 匹配敏感词
     *
     * @param source 待匹配字符串
     */
    public static List<ACSensitiveWordFilterRecord> searchKeyword(String source) {
        return Singleton.AC_SENSITIVE_WORD_FILTER.searchKeyword(source);
    }
}
