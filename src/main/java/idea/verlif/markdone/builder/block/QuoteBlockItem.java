package idea.verlif.markdone.builder.block;

import idea.verlif.markdone.builder.BlockBuilder;

public class QuoteBlockItem extends BaseBlockItem {

    public QuoteBlockItem() {}

    /**
     * 基础块元素构造器
     *
     * @param level 位置层级，一般表示列表内的不同层级
     */
    public QuoteBlockItem(int level) {
        super(level);
    }

    @Override
    public QuoteBuilder start() {
        return new QuoteBuilder();
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
