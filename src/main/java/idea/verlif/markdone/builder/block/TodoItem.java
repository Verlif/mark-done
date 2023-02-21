package idea.verlif.markdone.builder.block;

import idea.verlif.markdone.builder.BlockBuilder;

public class TodoItem extends BaseBlockItem {

    public TodoItem() {
    }

    /**
     * 基础块元素构造器
     *
     * @param level 位置层级，一般表示列表内的不同层级
     */
    public TodoItem(int level) {
        super(level);
    }

    @Override
    public TodoBuilder start() {
        return new TodoBuilder();
    }

    public final class TodoBuilder extends Builder {

        /**
         * 已完成
         *
         * @param content 填写内容
         */
        public TodoBuilder done(String content) {
            content("- [X] " + content);
            return this;
        }

        /**
         * 未完成
         *
         * @param content 填写内容
         */
        public TodoBuilder undo(String content) {
            content("- [ ] " + content);
            return this;
        }

        @Override
        public TodoBuilder content(String content) {
            super.content(content);
            return this;
        }

        @Override
        public TodoBuilder content(BlockBuilder builder) {
            super.content(builder);
            return this;
        }
    }
}
