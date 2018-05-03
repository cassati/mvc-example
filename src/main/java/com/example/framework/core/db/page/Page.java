package com.example.framework.core.db.page;

import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 分页信息 第一页从1开始
 */
public class Page extends RowBounds implements Serializable {
    private static final long serialVersionUID = 1L;
    protected List<?> result;
    protected int pageSize = 20;	//每页数据大小
    protected int pageNo = 1;		//当前页
    protected int totalRows = 0;	//总条数

    public Page() {
    }

    public Page(int pageSize, int pageNo) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
    }

    /**
     * @param result
     *          返回数据list
     * @param pageSize
     *          每页条数
     * @param pageNo
     *          页码（单前页码）
     * @param totalRows
     *          总条数
     */
    public Page(List<?> result, int pageSize, int pageNo, int totalRows) {
        this.result = result;
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.totalRows = totalRows;
    }

    public List<?> getResult() {
        return result;
    }

    public void setResult(List<?> result) {
        this.result = result;
    }

    public int getPageSize() {
        return pageSize;
    }

    /**
     * <p>
     * 每一页的条数，默认10条
     * <p/>
     * 
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
    	this.checkPage();
        return pageNo;
    }

    /**
     * <p>
     * 当前第几页，默认为1，从第一页开始
     * <p/>
     * 
     * @param pageNo
     */
    public void setPageNo(int pageNo) {
        this.pageNo = (pageNo>0)?pageNo:1;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    private void checkPage() { //2018-2-2 wuyj 解决bug15568：最后一次分页查询会重复
        if (this.pageNo < 1) {
        	this.setPageNo(1);
        }
    }

    /**
     * <p>
     * 获取总页数
     * <p/>
     * 
     * @return
     */
    public int getTotalPage() {
        int countPage = 0;
        if (this.getTotalRows() > 0) {
            if (this.getTotalRows() % this.getPageSize() == 0) {
                countPage = this.getTotalRows() / this.getPageSize();
            } else {
                countPage = this.getTotalRows() / this.getPageSize() + 1;
            }
        }
        return countPage;
    }
    
}
