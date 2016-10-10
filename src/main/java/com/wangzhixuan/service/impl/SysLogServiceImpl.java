package com.wangzhixuan.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.wangzhixuan.commons.utils.PageInfo;
import com.wangzhixuan.mapper.SysLogMapper;
import com.wangzhixuan.model.SysLog;
import com.wangzhixuan.service.ISysLogService;

/**
 *
 * SysLog 表数据服务层接口实现类
 *
 */
@Service
public class SysLogServiceImpl extends SuperServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

    @Override
    public void selectDataGrid(PageInfo pageInfo) {
        
    }

}