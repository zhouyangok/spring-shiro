package com.wangzhixuan.service;

import com.wangzhixuan.commons.utils.PageInfo;
import com.wangzhixuan.model.SysLog;
import com.baomidou.framework.service.ISuperService;

/**
 *
 * SysLog 表数据服务层接口
 *
 */
public interface ISysLogService extends ISuperService<SysLog> {

    void selectDataGrid(PageInfo pageInfo);


}