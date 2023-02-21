package idea.verlif.markdone.builder.block;

import java.util.ArrayList;

public class TableItem extends BaseBlockItem {

    public TableItem() {
    }

    /**
     * 基础块元素构造器
     *
     * @param level 位置层级，一般表示列表内的不同层级
     */
    public TableItem(int level) {
        super(level);
    }

    @Override
    public TableBuilder start() {
        return new TableBuilder();
    }

    /**
     * 列浮动位置
     */
    public enum FLOW {
        LEFT,
        CENTER,
        RIGHT,
    }

    /**
     * 表头信息
     */
    public static final class TitleInfo {

        private final String name;

        private final FLOW flow;

        public TitleInfo(String name, FLOW flow) {
            this.name = name;
            this.flow = flow;
        }

        public String getName() {
            return name;
        }

        public FLOW getFlow() {
            return flow;
        }
    }

    public final class TableBuilder extends Builder {

        private final ArrayList<TitleInfo> infos;

        private boolean titleEnd;

        public TableBuilder() {
            this.infos = new ArrayList<>();
            this.titleEnd = false;
        }

        /**
         * 批量添加标题。列对其方式默认靠左。
         *
         * @param titles 标题列表
         */
        public TableBuilder titles(String... titles) {
            return titles(FLOW.LEFT, titles);
        }

        /**
         * 批量添加标题
         *
         * @param flow   列对其方式
         * @param titles 标题列表
         */
        public TableBuilder titles(FLOW flow, String... titles) {
            for (String title : titles) {
                infos.add(new TitleInfo(title, flow));
            }
            return this;
        }

        /**
         * 添加标题
         *
         * @param name 标题名称
         * @param flow 列对其方式
         */
        public TableBuilder title(String name, FLOW flow) {
            infos.add(new TitleInfo(name, flow));
            return this;
        }

        /**
         * 表格的一行数据。请勿使用两次{@code values(Object...)}来填充一行数据
         *
         * @param values 数据列表
         */
        public TableBuilder values(Object... values) {
            if (!titleEnd) {
                outputTitle();
                titleEnd = true;
            }
            StringBuilder sb = new StringBuilder("|");
            for (Object value : values) {
                sb.append(" ").append(value).append(" |");
            }
            line(sb.toString());
            return this;
        }

        private void outputTitle() {
            StringBuilder sb = new StringBuilder("|");
            for (TitleInfo info : infos) {
                sb.append(" ").append(info.name).append(" |");
            }
            line(sb.toString());
            sb.setLength(1);
            for (TitleInfo info : infos) {
                switch (info.flow) {
                    case LEFT:
                        sb.append(" :---- |");
                        break;
                    case RIGHT:
                        sb.append(" ----: |");
                        break;
                    default:
                        sb.append(" :---: |");
                }
            }
            line(sb.toString());
        }

        /**
         * 重复标题，用于另开表格使用当前标题展示数据
         */
        public TableBuilder repeatTitle() {
            this.titleEnd = false;
            line();
            return this;
        }

    }
}
