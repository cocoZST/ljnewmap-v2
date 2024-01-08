package com.ljnewmap.common.constant;

/**
 * 常量
 *
 */
public interface Constant {
    /**
     * 成功
    */
    int SUCCESS = 1;
    /**
     * 失败
    */
    int FAIL = 0;
    /**
     * 菜单根节点标识
    */
    Long MENU_ROOT = 0L;
    /**
     * 部门根节点标识
    */
    Long DEPT_ROOT = 0L;
    /**
     *  升序
    */
    String ASC = "asc";
    /**
     * 降序
    */
    String DESC = "desc";
    /**
     * 创建时间字段名
    */
    String CREATE_DATE = "create_date";

    /**
     * 数据权限过滤
    */
    String SQL_FILTER = "sqlFilter";
    /**
     * 当前页码
    */
    String PAGE = "page";
    /**
     * 每页显示记录数
    */
    String LIMIT = "limit";
    /**
     * 排序字段
    */
    String ORDER_FIELD = "orderField";
    /**
     * 排序方式
    */
    String ORDER = "order";
    /**
     * token header
    */
    String TOKEN_HEADER = "token";

    /**
     * 定时任务状态
    */
    enum ScheduleStatus {
        /**
         * 暂停
        */
        PAUSE(0),
        /**
         * 正常
        */
        NORMAL(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 公共状态
     */
    enum CommonStatus {
        /**
         * 停用/关闭
         */
        DISABLE(0),
        /**
         * 正常/开启
         */
        ENABLE(1);

        private Integer status;

        CommonStatus(Integer status){
            this.status = status;
        }

        public Integer getStatus() {
            return status;
        }
    }

}