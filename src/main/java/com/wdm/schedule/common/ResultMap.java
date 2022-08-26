package com.wdm.schedule.common;

import lombok.Data;

/**
 * @BelongsProject: SpringBoot-Scheduled
 * @BelongsPackage: com.wdm.schedule.common
 * @Classname ResultMap
 * @Description TODO
 * @Date 2022/8/26 4:50 下午
 * @Created by wudengming
 * @Version: 1.0
 */
@Data
public class ResultMap {
    private Integer code;
    private String msg;
    private Object data;
}
