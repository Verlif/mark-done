package idea.verlif.markdone.builder.block;

import idea.verlif.markdone.builder.BlockBuilder;

/**
 * 基础块级构建。
 * 包括以下类型：
 * <li> 代码块（CodeBlockBuilder） </li>
 * <li> 引用块（QuoteBlockBuilder） </li>
 * <li> 列表块（ListBlockBuilder） </li>
 * <li> 表格块（TableBlockBuilder） </li>
 */
public abstract class BaseBlockItem implements BlockBuilder {

    protected int level;
    protected final StringBuilder sb;

    public BaseBlockItem() {
        this(1);
    }

    /**
     * 基础块元素构造器
     *
     * @param level 位置层级，一般表示列表内的不同层级
     */
    public BaseBlockItem(int level) {
        this.level = Math.max(level, 1);
        this.sb = new StringBuilder();
    }

    /**
     * 更改层级
     *
     * @param level 目标层级
     */
    @Override
    public void levelFrom(int level) {
        if (level < 1) {
            this.level = 1;
        } else {
            this.level = level;
        }
    }

    /**
     * 构建开始
     *
     * @return 新构建器
     */
    public abstract Builder start();

    @Override
    public String build() {
        int length = sb.length();
        if (length < 2) {
            return sb.toString();
        }
        if (sb.charAt(length - 1) != '\n') {
            sb.append("\n");
        }
        if (sb.charAt(sb.length() - 2) != '\n') {
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return sb.toString();
    }

    /**
     * 构建器
     */
    public class Builder {

        protected Builder() {}

        /**
         * 使用块级构建器填充内容
         *
         * @param builder 块级构建器
         * @return 当前的构造器
         */
        public Builder content(BlockBuilder builder) {
            String build = builder.toString();
            String[] split = build.split("\n");
            for (String s : split) {
                line(s);
            }
            return this;
        }

        /**
         * 填充内容
         *
         * @param content 文本内容
         * @return 当前的构造器
         */
        public Builder content(String content) {
            String[] strings = content.split("\n");
            for (String string : strings) {
                line(string);
            }
            return this;
        }

        /**
         * 新的一行
         *
         * @param line 行内容
         */
        protected Builder line(String line) {
            if (line.length() > 0) {
                repeat(" ", (level - 1) * 3);
            }
            sb.append(line).append("\n");
            return this;
        }

        /**
         * 空白行
         */
        protected Builder line() {
            return line("");
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

        /**
         * 获取字符串中字符在首位出现的次数
         *
         * @param target 目标字符串
         * @param c      目标字符
         * @return 连续出现次数
         */
        protected int countFirst(String target, char c) {
            int count = 0;
            while (target.charAt(count++) == c) ;
            return count;
        }

        /**
         * 完成构建
         *
         * @return 当前的基础块级构建器
         */
        public BaseBlockItem build() {
            return BaseBlockItem.this;
        }
    }

}
