package cn.lwd.algorithm.ac;

/**
 * 关键词匹配记录(记录关键词在原文中的位置)
 *
 * @param beginIndex 起始索引(包含开始位置)
 * @param endIndex   结束索引(不包含结束位置)
 * @param keyword    关键词
 */
public record ACSensitiveWordFilterRecord(int beginIndex, int endIndex, String keyword) {
}
