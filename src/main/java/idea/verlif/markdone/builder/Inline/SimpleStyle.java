package idea.verlif.markdone.builder.Inline;

/**
 * 简单样式枚举
 */
public enum SimpleStyle {

    /**
     * 粗体
     */
    BOLD("**", "**"),

    /**
     * 内嵌代码
     */
    CODE("`", "`"),

    /**
     * 删除线
     */
    DELETE("~~", "~~"),

    /**
     * 斜体
     */
    ITALIC("*", "*"),

    /**
     * emoji表情
     */
    EMOJI(":", ":"),

    /**
     * 网址
     */
    URL("<", ">"),
    ;

    private final String left;
    private final String right;

    SimpleStyle(String left, String right) {
        this.left = left;
        this.right = right;
    }

    public String getLeft() {
        return left;
    }

    public String getRight() {
        return right;
    }
}
