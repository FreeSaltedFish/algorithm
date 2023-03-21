package cn.lwd.algorithm.ac;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * ac算法敏感词过滤
 *
 * @author liuweidong
 */
public class ACSensitiveWordFilter {

    /**
     * 根节点
     */
    private final ACNode root;

    /**
     * @param keywords 所有待匹配的关键词
     */
    public ACSensitiveWordFilter(List<String> keywords) {
        root = new ACNode('\u0000', null);
        root.setFailureNode(root);

        initTrie(keywords);
        initFailNode();
    }

    /**
     * 初始化前缀树
     *
     * @param keywords 待匹配的关键词集合
     */
    private void initTrie(List<String> keywords) {
        for (var key : keywords) {
            var current = root;

            if (!key.isEmpty()) {
                for (var wordChar : key.toCharArray()) {
                    var node = current.getChild(wordChar);
                    if (node != null) {
                        current = node;
                    } else {
                        var childNode = new ACNode(wordChar, current);
                        current.addChild(childNode);

                        current = childNode;
                    }
                }

                current.setKeywords(key);
            }
        }
    }

    /**
     * 通过bfs初始化失败跳转节点
     */
    private void initFailNode() {
        var queue = new ArrayDeque<ACNode>();
        var node = root;
        for (var childNode : node.getAllChild()) {
            childNode.setFailureNode(root);
            childNode.getAllChild().forEach(queue::offer);
        }

        while (!queue.isEmpty()) {
            node = queue.poll();
            var character = node.getCharacter();

            var child = node.getAllChild();
            for (var childNode : child) {
                queue.offer(childNode);
            }

            var parent = node.getParent();
            var b = true;
            while (b) {
                var failureACNode = parent.getFailureNode();
                var childNode = failureACNode.getChild(character);
                if (childNode != null) {
                    node.setFailureNode(childNode);
                    b = false;
                } else if (failureACNode == root) {
                    node.setFailureNode(failureACNode);
                    b = false;
                } else {
                    parent = failureACNode;
                }
            }
        }
    }

    /**
     * 匹配敏感词
     *
     * @param source 待匹配字符串
     */
    public List<ACSensitiveWordFilterRecord> searchKeyword(String source) {
        var acSearchRecords = new ArrayList<ACSensitiveWordFilterRecord>();
        var sourceCharArr = source.toCharArray();
        var node = root;
        for (var i = 0; i < sourceCharArr.length; i++) {
            var childNode = node.getChild(sourceCharArr[i]);
            if (childNode != null) {
                var keywords = childNode.getKeywords();
                if (keywords != null) {
                    acSearchRecords.add(new ACSensitiveWordFilterRecord(i - keywords.length() + 1, i + 1, keywords));
                }
                node = childNode;
            } else {
                node = node.getFailureNode();
            }
        }

        return acSearchRecords;
    }
}
