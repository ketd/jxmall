package com.ketd.utils.domain;

import lombok.Data;

/**
 * @Description:
 * @BelongsProject: loveDiary
 * @BelongsPackage: com.ketd.dao
 * @Author: ketd
 * @CreateTime: 2024-01-12  16:03
 */
@Data
public class CredentialsBdy {

     private String tmpSecretId;
     private String tmpSecretKey;
     private String sessionToken;

}
