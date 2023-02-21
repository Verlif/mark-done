package idea.verlif.markdone.builder.block;

/**
 * 定义元素
 */
public class DefinitionItem extends BaseBlockItem {

    @Override
    public DefinitionBuilder start() {
        return new DefinitionBuilder();
    }

    public final class DefinitionBuilder extends Builder {

        /**
         * 添加新定义
         *
         * @param words       词句内容
         * @param description 定义内容
         * @return 定义构建器
         */
        public DefinitionBuilder words(String words, String description) {
            content(words).content(": " + description).line();
            return this;
        }
    }
}
