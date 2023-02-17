package idea.verlif.markdone.builder.block;

import idea.verlif.markdone.builder.BlockBuilder;

public class DisorderedListBlockItem extends BaseBlockItem {

    public DisorderedListBlockItem() {
    }

    /**
     * 基础块元素构造器
     *
     * @param level 位置层级，一般表示列表内的不同层级
     */
    public DisorderedListBlockItem(int level) {
        super(level);
    }

    @Override
    public DisorderedListBuilder start() {
        return new DisorderedListBuilder();
    }

    public final class DisorderedListBuilder extends Builder {

        @Override
        protected DisorderedListBuilder line(String line) {
            super.line("- " + line);
            return this;
        }

        @Override
        public DisorderedListBuilder content(BlockBuilder builder) {
            String build = builder.toString();
            String[] split = build.split("\n");
            for (String s : split) {
                super.line(s);
            }
            return this;
        }
    }
}
