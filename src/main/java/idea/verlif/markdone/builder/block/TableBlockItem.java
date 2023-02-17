package idea.verlif.markdone.builder.block;

import java.util.ArrayList;

public class TableBlockItem extends BaseBlockItem {

    public TableBlockItem() {}

    /**
     * 基础块元素构造器
     *
     * @param level 位置层级，一般表示列表内的不同层级
     */
    public TableBlockItem(int level) {
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

        public TableBuilder titles(String... titles) {
            for (String title : titles) {
                infos.add(new TitleInfo(title, FLOW.LEFT));
            }
            return this;
        }

        public TableBuilder title(String name, FLOW flow) {
            infos.add(new TitleInfo(name, flow));
            return this;
        }

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

        public TableBuilder repeatTitle() {
            this.titleEnd = false;
            line();
            return this;
        }

    }
}
