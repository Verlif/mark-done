package idea.verlif.markdone.builder.block;

public class HorizontalRuleItem extends BaseBlockItem {

    public HorizontalRuleItem() {}

    /**
     * 基础块元素构造器
     *
     * @param level 位置层级，一般表示列表内的不同层级
     */
    public HorizontalRuleItem(int level) {
        super(level);
    }

    @Override
    public Builder start() {
        return new Builder().line("---");
    }

}
