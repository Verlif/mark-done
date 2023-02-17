package idea.verlif.markdone.builder.block;

public class LineBlockItem extends BaseBlockItem {

    public LineBlockItem() {}

    /**
     * 基础块元素构造器
     *
     * @param level 位置层级，一般表示列表内的不同层级
     */
    public LineBlockItem(int level) {
        super(level);
    }

    @Override
    public Builder start() {
        return new Builder();
    }

}
