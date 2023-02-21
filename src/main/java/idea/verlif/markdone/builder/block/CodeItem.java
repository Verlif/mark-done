package idea.verlif.markdone.builder.block;

public class CodeItem extends BaseBlockItem {

    /**
     * 代码类型，例如<strong>java</strong>、<strong>text</strong>、<strong>shell</strong>等。
     */
    private final String type;

    public CodeItem(String type) {
        this(0, type);
    }

    /**
     * 基础块元素构造器
     *
     * @param level 位置层级，一般表示列表内的不同层级
     * @param type  {@link CodeItem#type}
     */
    public CodeItem(int level, String type) {
        super(level);
        this.type = type;
    }

    @Override
    public Builder start() {
        return new CodeBuilder().line("```" + type);
    }

    public final class CodeBuilder extends Builder {

        @Override
        public BaseBlockItem build() {
            line("```");
            return super.build();
        }
    }
}
