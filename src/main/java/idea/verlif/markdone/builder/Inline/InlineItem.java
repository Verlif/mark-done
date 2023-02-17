package idea.verlif.markdone.builder.Inline;

import idea.verlif.markdone.builder.InlineBuilder;

public class InlineItem implements InlineBuilder {

    private final StringBuilder sb;

    public InlineItem() {
        sb = new StringBuilder();
    }

    /**
     * 新的一行
     *
     * @return 当前的标准行内构建器
     */
    public InlineItem newLine() {
        repeat("\n", 2);
        return this;
    }

    /**
     * 粗体文本
     *
     * @param content 文本内容
     * @return 当前的标准行内构建器
     */
    public InlineItem bold(String content) {
        return output("**").output(content).output("**");
    }

    /**
     * 标题文本
     *
     * @param title 标题内容
     * @return 当前的标准行内构建器
     */
    public InlineItem heading(int level, String title) {
        if (level < 1) {
            level = 1;
        }
        repeat("#", level);
        line(" " + title);
        return this;
    }

    /**
     * 链接资源
     *
     * @param name 显示名称
     * @param path 资源路径
     * @return 当前的标准行内构建器
     */
    public InlineItem src(String name, String path) {
        return src(name, path, null);
    }

    /**
     * 链接资源
     *
     * @param name  显示名称
     * @param path  资源路径
     * @param title 资源标题
     * @return 当前的标准行内构建器
     */
    public InlineItem src(String name, String path, String title) {
        output("[").output(name).output("](").output(path);
        if (title != null) {
            output(" \"").output(title).output("\"");
        }
        return output(")");
    }

    /**
     * 网址
     *
     * @param url 网址地址
     * @return 当前的标准行内构建器
     */
    public InlineItem url(String url) {
        return output("<").output(url).output(">");
    }

    /**
     * 行内代码
     *
     * @param code 代码内容
     * @return 当前的标准行内构建器
     */
    public InlineItem code(String code) {
        return output("`").output(code).output("`");
    }

    /**
     * 图片
     *
     * @param name   图片名称
     * @param imgUrl 图片路径
     * @return 当前的标准行内构建器
     */
    public InlineItem img(String name, String imgUrl) {
        return output("!").src(name, imgUrl);
    }

    /**
     * 图片
     *
     * @param name   图片名称
     * @param imgUrl 图片路径
     * @param desc   图片介绍
     * @return 当前的标准行内构建器
     */
    public InlineItem img(String name, String imgUrl, String desc) {
        return output("!").src(name, imgUrl, desc);
    }

    /**
     * 删除线
     *
     * @param content 被删除线标记的内容
     * @return 当前的标准行内构建器
     */
    public InlineItem delete(String content) {
        return output("~~").output(content).output("~~");
    }

    /**
     * 斜体
     *
     * @param content 使用斜体的内容
     * @return 当前的标准行内构建器
     */
    public InlineItem italic(String content) {
        return output("*").output(content).output("*");
    }

    /**
     * 包裹标签
     *
     * @param tag     标签名称
     * @param content 内容
     * @return 标签包裹的内容文本
     */
    public InlineItem warpedTag(String tag, String content) {
        return output("<").output(tag).output(">")
                .output(content)
                .output("</").output(tag).output(">");
    }

    /**
     * 使用emoji表情
     *
     * @param tag emoji标签
     * @return emoji表情文本
     */
    public InlineItem emoji(String tag) {
        return output(":").output(tag).output(":");
    }

    /**
     * 基本文本
     *
     * @param content 文本内容
     * @return 当前的标准行内构建器
     */
    public InlineItem output(String content) {
        sb.append(content);
        return this;
    }

    protected void line(String line) {
        sb.append(line).append("\n");
    }

    /**
     * 重复字符串
     *
     * @param str       需要重复的目标字符串
     * @param frequency 重复的次数
     */
    protected void repeat(String str, int frequency) {
        for (int i = 0; i < frequency; i++) {
            sb.append(str);
        }
    }

    @Override
    public String build() {
        return sb.toString();
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
