package com.voting.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ll
 * @since 2019-10-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "vid", type = IdType.AUTO)
    private Integer vid;

    private Integer uid;

    private Integer sid;

    private Integer oid;


}
