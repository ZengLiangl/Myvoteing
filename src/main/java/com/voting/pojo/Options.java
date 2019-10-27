package com.voting.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ll
 * @since 2019-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Options implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "oid", type = IdType.AUTO)
    private Integer oid;

    private Integer osid;

    private String ocontent;


}
