package com.voting.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

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
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "userid", type = IdType.AUTO)
    private Integer userId;

    @TableField("loginName")
    private String loginName;

    private String username;

    private String password;

    /**
     * 1为男
     */
    private Integer sex;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private Date createTime;

    @TableField("isAdmin")
    private Integer isAdmin;


}
