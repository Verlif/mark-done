package idea.verlif.markdone.builder.block;

/**
 * 目录元素
 */
public class CatalogItem extends BaseBlockItem {
    @Override
    public Builder start() {
        Builder builder = new Builder();
        builder.line("[TOC]");
        return builder;
    }

}
