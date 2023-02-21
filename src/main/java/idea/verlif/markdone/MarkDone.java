package idea.verlif.markdone;

import idea.verlif.markdone.builder.BlockBuilder;
import idea.verlif.markdone.builder.Inline.SimpleStyle;
import idea.verlif.markdone.builder.InlineBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class MarkDone {

    protected final StringBuilder content;
    protected final StringBuilder footer;
    protected Editor editor;

    public MarkDone() {
        content = new StringBuilder();
        footer = new StringBuilder();
    }

    /**
     * 获取内置编辑器
     *
     * @return 编辑器对象
     */
    public Editor getEditor() {
        if (editor == null) {
            editor = new Editor();
        }
        return editor;
    }

    public MarkDone append(String text) {
        if (text == null || text.length() == 0) {
            return this;
        }
        content.append(text);
        if (text.charAt(text.length() - 1) != '\n') {
            content.append("\n");
        }
        if (text.charAt(text.length() - 2) != '\n') {
            content.append("\n");
        }
        return this;
    }

    public MarkDone append(BlockBuilder builder) {
        return append(builder.build());
    }

    public MarkDone append(InlineBuilder builder) {
        return append(builder.build());
    }

    /**
     * 对此处进行脚注说明
     *
     * @param title       脚注标签
     * @param description 脚注内容
     * @return 当前的MarkDone
     */
    public MarkDone footnote(String title, String description) {
        insertBeforeNewLine(content, "[^" + title + "]");
        footer.append("[^").append(title).append("]: ").append(description).append("\n");
        return this;
    }

    /**
     * 在换行符前填入数据
     *
     * @param target 目标字符串构建器
     * @param str    需要重复的目标字符串
     */
    protected void insertBeforeNewLine(StringBuilder target, String str) {
        // 独立上一次结尾的换行符
        int length = target.length();
        int count = 0;
        while (target.charAt(length - 1) == '\n') {
            count++;
            target.setLength(--length);
        }
        // 添加目标字符串
        target.append(str);
        // 重新回填换行符
        for (int i = 0; i < count; i++) {
            target.append("\n");
        }
    }

    public String build() {
        while (content.charAt(content.length() - 1) == '\n') {
            content.setLength(content.length() - 1);
        }
        if (footer.length() > 0) {
            content.append("\n\n");
            while (footer.charAt(footer.length() - 1) == '\n') {
                footer.setLength(footer.length() - 1);
            }
        }
        return content.toString() + footer;
    }

    /**
     * 内置编辑器
     */
    public class Editor {

        protected final Map<String, String> referenceMap;
        protected int index;

        private Editor() {
            referenceMap = new HashMap<>();
            index = 0;
        }

        /**
         * 添加内容与脚注，并自动生成脚注标签
         *
         * @param content     添加的文本内容
         * @param description 脚注内容
         * @return 带脚注的内容文本
         */
        public synchronized String footnote(String content, String description) {
            return footnote(content, String.valueOf(++index), description);
        }

        /**
         * 添加内容与脚注
         *
         * @param content     添加的文本内容
         * @param title       脚注标签
         * @param description 脚注内容
         * @return 带脚注的内容文本
         */
        public String footnote(String content, String title, String description) {
            footer.append("[^").append(title).append("]: ").append(description).append("\n");
            return content + "[^" + title + "]";
        }

        /**
         * 添加参考链接，并自动生成链接标题
         *
         * @param content 内容
         * @param url     链接地址
         * @return 带链接的内容
         */
        public synchronized String reference(String content, String url) {
            String title = referenceMap.get(content);
            if (title == null) {
                title = String.valueOf(++index);
                referenceMap.put(content, title);
                footer.append("[").append(title).append("]: ").append(url).append(" \"").append(content).append("\"\n");
            }
            return "[" + content + "][" + title + "]";
        }

        /**
         * 添加参考链接
         *
         * @param content 内容
         * @param title   链接标题
         * @param url     链接地址
         * @return 带链接的内容
         */
        public synchronized String reference(String content, String title, String url) {
            footer.append("[").append(title).append("]: ").append(url).append(" \"").append(content).append("\"\n");
            return "[" + content + "][" + title + "]";
        }

        /**
         * 输出装饰文本
         *
         * @param content     内容文本
         * @param simpleStyle 装饰规则
         * @return 装饰后文本内容
         */
        public String style(String content, SimpleStyle simpleStyle) {
            return simpleStyle.getLeft() + content + simpleStyle.getRight();
        }

        /**
         * 输出装饰文本
         *
         * @param content 内容文本
         * @param enumMap 装饰规则表
         * @return 装饰后文本内容
         */
        public String style(String content, Map<String, SimpleStyle> enumMap) {
            for (Map.Entry<String, SimpleStyle> enumEntry : enumMap.entrySet()) {
                String key = enumEntry.getKey();
                SimpleStyle styleEnum = enumEntry.getValue();
                content = content.replaceAll(Pattern.quote(key), styleEnum.getLeft() + enumEntry.getKey() + styleEnum.getRight());
            }
            return content;
        }
    }
}
