package com.ketd.common.enume;

import lombok.Getter;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.common.enume
 * @Author: ketd
 * @CreateTime: 2024-06-02  18:36
 */
@Getter
public enum LockStatusEnum {
    /*1-锁定 2-解锁 3-扣减库存
     */

    LOCK(1, "锁定"),
    UNLOCK(2, "解锁"),
    LOCK_STOCK(3, "扣减库存");


    private final Integer code;
    private final String msg;


    LockStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }



}
