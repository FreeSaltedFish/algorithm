package cn.lwd.algorithm.ac;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * ac算法自动机节点
 *
 * @author liuweidong
 */
@Getter
@Setter
@RequiredArgsConstructor
public class ACNode {

    /**
     * 指向当前节点的字符
     */
    private final char character;

    /**
     * 当前节点对应的子节点
     */
    private final Map<Character, ACNode> children = new HashMap<>();

    /**
     * 父节点
     */
    private final ACNode parent;

    /**
     * 匹配失败时的下一个节点(跳转节点)
     */
    private ACNode failureNode;

    /**
     * 匹配成功时当前节点对应的关键词
     */
    private String keywords;

    public ACNode getChild(char character) {
        return children.get(character);
    }

    public Collection<ACNode> getAllChild() {
        return children.values();
    }

    public void addChild(ACNode acNode) {
        children.put(acNode.getCharacter(), acNode);
    }

    @Override
    public String toString() {
        return "ACNode{" +
                "character=" + character +
                ", children=" + children +
                ", keywords='" + keywords + '\'' +
                '}';
    }
}
