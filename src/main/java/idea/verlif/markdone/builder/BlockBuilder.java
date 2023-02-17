package idea.verlif.markdone.builder;

/**
 * 块级内容构造
 */
public interface BlockBuilder {

    /**
     * 更改层级
     *
     * @param level 目标层级
     */
    void levelFrom(int level);

    String build();

}
