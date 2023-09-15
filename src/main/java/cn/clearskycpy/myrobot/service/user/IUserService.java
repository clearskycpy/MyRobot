package cn.clearskycpy.myrobot.service.user;

import cn.clearskycpy.myrobot.common.Constants;
import cn.clearskycpy.myrobot.common.dto.UserResDto;
import cn.clearskycpy.myrobot.common.po.User;
import cn.clearskycpy.myrobot.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.IService;
//import com.baomidou.mybatisplus.service.IService;

/**
 * @description:
 * @author：clearSky
 * @date: 2023/9/14
 * @Copyright： clearskycpy.cn
 */
public interface IUserService extends IService<User> {

    /**
     * 切换用户状态  删除方法共用
     * @param targetUserState  目标状态
     * @return 是否切换成功
     */
    boolean switchUserState(Constants.UserState targetUserState);


    /**
     * 更新用户信息
     * @param user
     */
    void update(User user);


    /**
     *  获取消息回答次数
     * @param uId
     * @return
     */
    Integer queryMessageCntByUId(Long uId);

    /**
     * 消费消息回答次数
     * @param uId
     */
    void consumptionMessage(Long uId);

    /**
     * 用户创建方法  校验手机号是否唯一 默认创建普通用户  密码使用md5 加密
     * @param user
     */
    UserResDto insertUser(User user);

}
