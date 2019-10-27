package com.voting.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ll
 * @description:
 * @create: 2019-10-08 00:33
 **/
@Data

@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {
    private String name;
    private Double value;


}
