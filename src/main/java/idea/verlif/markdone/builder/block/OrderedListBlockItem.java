package idea.verlif.markdone.builder.block;

import idea.verlif.markdone.builder.BlockBuilder;

public class OrderedListBlockItem extends BaseBlockItem {

    public OrderedListBlockItem() {}

    /**
     * 基础块元素构造器
     *
     * @param level 位置层级，一般表示列表内的不同层级
     */
    public OrderedListBlockItem(int level) {
        super(level);
    }

    @Override
    public OrderedListBuilder start() {
        return new OrderedListBuilder();
    }


    public final class OrderedListBuilder extends Builder {

        /**
         * 当前序号
         */
        private int index = 1;

        public OrderedListBuilder indexFrom(int index) {
            this.index = index;
            return this;
        }

        @Override
        public OrderedListBuilder content(BlockBuilder builder) {
            String build = builder.toString();
            String[] split = build.split("\n");
            for (String s : split) {
                super.line(s);
            }
            return this;
        }

        @Override
        protected OrderedListBuilder line(String line) {
            super.line(index++ + ". " + line);
            return this;
        }
    }
}
