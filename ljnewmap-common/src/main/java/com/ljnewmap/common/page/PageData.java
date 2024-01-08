package com.ljnewmap.common.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljnewmap.common.utils.ConvertUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 *
 */
@Data
@Schema(description = "分页数据")
public class PageData<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "总记录数")
    private int total;

    @Schema(description = "列表数据")
    private List<T> list;

    /**
     * 分页
     * @param list   列表数据
     * @param total  总记录数
    */
    public PageData(List<T> list, long total) {
        this.list = list;
        this.total = (int) total;
    }

    public PageData(List<?> list, long total, Class<T> target) {
        List<T> targetList = ConvertUtils.sourceToTarget(list, target);
        this.list = targetList;
        this.total = (int) total;
    }

    public PageData(IPage page, Class<T> target) {
        List<T> targetList = ConvertUtils.sourceToTarget(page.getRecords(), target);
        this.list = targetList;
        this.total = (int) page.getTotal();
    }
}