package com.ketd.search;

import lombok.Data;

/**
 * @Description:
 * @BelongsProject: gulimall
 * @BelongsPackage: com.ketd.search
 * @Author: ketd
 * @CreateTime: 2024-04-09  21:20
 */
@Data
public class User {
    private Integer id;
    private String name;
    private Integer age;

    public User(int i, String s, int i1) {
        this.id = i;
        this.name = s;
        this.age = i1;
    }

    public User() {

    }
}
