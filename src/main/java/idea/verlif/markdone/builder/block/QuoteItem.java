package idea.verlif.markdone.builder.block;

import idea.verlif.markdone.builder.BlockBuilder;

/**
 * 引用块
 */
public class QuoteItem extends BaseBlockItem {

    public QuoteItem() {}

    /**
     * 基础块元素构造器。
     *
     * @param level 位置层级，一般表示列表内的不同层级。请注意，在引用快中使用引用块时，请使用无参构造。
     */
    public QuoteItem(int level) {
        super(level);
    }

    @Override
    public QuoteBuilder start() {
        return new QuoteBuilder();
    }

    @Override
    public String build() {
        String s = super.build();
        if (s.endsWith("\n\n")) {
            s = s.substring(0, s.length() - 2);
        }
        while (s.endsWith("> ")) {
            s = s.substring(0, s.length() - 2);
            if (s.charAt(s.length() - 1) == '\n') {
                s = s.substring(0, s.length() - 1);
            }
        }
        return s + "\n\n";
    }

    public final class QuoteBuilder extends Builder {

        @Override
        protected QuoteBuilder line(String line) {
            super.line("> " + line);
            return this;
        }

        @Override
        public QuoteBuilder content(BlockBuilder builder) {
            super.content(builder).line();
            return this;
        }

    }
}
