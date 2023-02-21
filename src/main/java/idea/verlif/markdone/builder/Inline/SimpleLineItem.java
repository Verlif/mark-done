package idea.verlif.markdone.builder.Inline;

import idea.verlif.markdone.builder.InlineBuilder;

import java.util.regex.Pattern;

/**
 * 行元素构建器。<br>
 * 行元素构建器包括两个内容区，一个总内容输出区，一个当前编辑区。
 * 在使用行元素构建器时，一般的方法调用，例如 {@link SimpleLineItem#append(String)}、{@link SimpleLineItem#style(SimpleStyle, String...)}都是将当前编辑区进行编辑。
 * 在编辑完成后需要使用 {@link SimpleLineItem#commit()} 将当前编辑区提交到总内容输出区。<br>
 * 在最终结果时，调用{@link SimpleLineItem#build()}来完成所有编辑工作并重置行元素构建器。
 */
public class SimpleLineItem implements InlineBuilder {

    private final StringBuilder totalStrBuilder;
    private final StringBuilder tempStrBuilder;

    public SimpleLineItem() {
        totalStrBuilder = new StringBuilder();
        tempStrBuilder = new StringBuilder();
    }

    /**
     * 向当前的行元素中填充内容
     *
     * @param content 填充的内容文本
     * @return 当前的行元素
     */
    public SimpleLineItem append(String content) {
        tempStrBuilder.append(content);
        return this;
    }


    /**
     * 向当前的行元素中填充装饰内容
     *
     * @param content     填充的内容文本
     * @param simpleStyle 内容装饰规则
     * @return 当前的行元素
     */
    public SimpleLineItem append(String content, SimpleStyle simpleStyle) {
        return append(simpleStyle.getLeft()).append(content).append(simpleStyle.getRight());
    }

    /**
     * 向当前的行元素中添加新的一行内容
     *
     * @param line 填充的内容文本
     * @return 当前的行元素
     */
    public SimpleLineItem line(String line) {
        line();
        tempStrBuilder.append(line);
        line();
        return this;
    }

    /**
     * 向当前的行元素中添加新的一行内容
     *
     * @return 当前的行元素
     */
    public SimpleLineItem line() {
        int length = tempStrBuilder.length();
        if (length > 0 && tempStrBuilder.charAt(length - 1) != '\n') {
            tempStrBuilder.append("\n");
            length++;
            if (length > 0 && tempStrBuilder.charAt(length - 2) != '\n') {
                tempStrBuilder.append("\n");
            }
        }
        return this;
    }

    /**
     * 批量装饰文本
     *
     * @param simpleStyle 装饰规则
     * @param txt         需要装饰的文本列表
     * @return 当前的行元素
     */
    public SimpleLineItem style(SimpleStyle simpleStyle, String... txt) {
        String content = tempStrBuilder.toString();
        for (String str : txt) {
            String key = Pattern.quote(str);
            content = content.replaceAll(key, simpleStyle.getLeft() + str + simpleStyle.getRight());
        }
        tempStrBuilder.setLength(0);
        tempStrBuilder.append(content);
        return this;
    }

    /**
     * 将上次的修改提交到总输出中
     *
     * @return 当前的行元素
     */
    public SimpleLineItem commit() {
        totalStrBuilder.append(tempStrBuilder);
        tempStrBuilder.setLength(0);
        return this;
    }

    /**
     * 结束当前的编辑流程，并重置行元素。
     *
     * @return 编辑后的文本
     */
    @Override
    public String build() {
        commit();
        String s = totalStrBuilder.toString();
        totalStrBuilder.setLength(0);
        return s;
    }

    @Override
    public String toString() {
        return totalStrBuilder.toString();
    }
}
