package com.xw;


public enum ErrorCode {

    //----SYSTEM_ERROR_CODE------系统错误错误代码
    NULL_PARAMETER_EXCEPTION(-31995, "参数为空"),
    INNER_EXCEPTION(-31996, "内部错误"),
    CHARACTER_NOT_UTF8(-31999, "请求的字符集不是utf-8"),
    ILLEGAL_PARAMETER(-32602, "参数错误"),
    INCLUDE_SENSITIVE_WORDS(-31971, "内容包含敏感信息"),

    // 证书异常代码
    APPLICATION_INVALID(-31978, "申请ID无效."),
    CERTIFICATION_IN_REVIEW(-31499, "用户的证书正在审核中."),
    CERTIFICATION_HAS_IDENTITY(-31450, "用户身份认证失败."),
    NOT_REAL_NAME_CERTIFICATION(-31449, "未实名认证"),
    CERTIFICATION_ERROR_STATUS(-31448, "认证状态错误"),
    CERTIFICATION_NOT_AUDIT(-31447,"还未审核通过"),

    //----USER_ERROR_CODE------用户错误代码
    USER_NOT_EXIST(-31998, "用户不存在"),
    USER_PASSWORD_ERROR(-31997, "密码错误"),
    USER_SESSION_EXPIRED(-31993, "用户没有登录/或者Session过期"),
    USER_PROMO_CODE_NOT_EXISTED_ERROR(-31988, "推荐码不存在"),

    USER_ID_NOT_EXIST(-31982, "该用户ID不存在"),
    USER_MOBILE_HAS_USED(-31990, "该手机号已被其他用户使用"),
    USER_MOBILE_INVALID(-31986, "手机号码不合法"),
    MERCHANT_NOT_REGISTER(-31985, "商家还未注册"),
    USER_BE_DISABLED(-31984, "用户被禁用"),
    WX_NOT_BIND_USER(-31975, "微信openId还未绑定用户"),
    USER_DONT_UPDATE_NICKNAME(-31973, "实名制用户，昵称不可修改"),
    LOGIN_CODE_INVALID(-31972, "登录码无效"),
    USER_LIMITED_ENTRY(-31970, "限制登录"),
    PASSWORD_IS_SAME(-31966, "新旧密码不能一样"),
    USER_IP_NOT_EXISTS_WHITE_LIST(-31965, "本后台只允许公司内部访问"),
    USER_CHANGE_CITY_NOT_POWER(-31959, "没有切换城市权限"),
    USER_EXCEPTION_LOGIN(-31958, "用户异常登录"),
    USER_ALREADY_MODIFY_PROMO(-31957, "你已经修改过一次邀请码"),
    USER_PROMO_ALREADY_EXISTS(-31956, "该邀请码已经有人使用啦"),

    //--------我的部门 ----------------------------------
    CHILD_DEPARTMENT_CAN_NOT_SET_PARENT(-31955, "下级部门及下级的下级不能设为部门的上级部门"),
    DEPARTMENT_CITY_NOT_EXISTS(-31954, "部门城市不存在"),
    DEPARTMENT_TYPE_NOT_EXISTS(-31953, "部门类型不存在"),
    DEPARTMENT_NAME_EXISTS(-31952, "部门名称已存在"),
    DEPARTMENT_NOT_EXISTS(-31951, "部门不存在"),
    DEPARTMENT_HAS_CANCAL(-31949, "部门已解散"),

    //--------权限相关 ----------------------------------
    EMPLOYEE_GROUP_NOT_EXISTS(-31899, "权限组不存在"),
    EMPLOYEE_GROUP_NAME_EXISTS(-31897, "权限组名称已存在"),
    EMPLOYEE_AUTHORIZATION_NOT_EXISTS(-31896, "权限不存在"),
    NOT_HAVE_PERMIT_CHECK_CITY(-31895, "没有权限查看所选城市"),


    //--------数据字典 ----------------------------------
    DATA_DICTIONARY_NOT_EXISTS(-31799, "数据字典不存在"),

    //--------收款凭证 ----------------------------------
    PAY_CERTIFICATE_NOT_EXISTS(-31699, "收款凭证不存在"),
    PAY_CERTIFICATE_HAS_DELETE(-31698, "收款凭证已删除"),
    PAY_CODE_OVERDUE(-31697,"支付码已经过期"),

    //--------在线专家 ----------------------------------
    EXPERT_NOT_EXISTS(-31650, "专家不存在"),

    //----TEAM_ERROR_CODE------团队错误代码
    // -31399 ~ -31300
    TEAM_NOT_EXIST(-31399, "团队不存在"),
    TEAM_CANNOT_JOIN(-31398, "不能加入自己创建的团队"),
    TEAM_ALREADY_JOIN_OTHER_TEAM(-31397, "用户已经加入（或申请加入）了其它团队"),
    TEAM_MEMBER_NOT_APPLYING(-31396, "成员状态不是申请中"),
    TEAM_MEMBER_NOT_APPROVED(-31395, "成员状态不是审核通过"),
    TEAM_CANNOT_REPEAT_APPLICANT(-31394, "该用户已经申请此团队，不能重复申请"),
    TEAM_NOT_MEMBER(-31393, "成员不存在或者不是正式成员"),
    TEAM_NOT_CREATOR(-31392, "不是团队的创建人"),
    TEAM_ARTICLE_NOT_FOUND(-31391, "文章不存在"),


    //----MVCODE_ERROR_CODE------验证码的错误代码
    MVCODE_NOT_CORRECT(-31989, "验证码不正确"),
    MVCODE_ALREADY_EXISTS(-31969, "验证码已经存在"),
    MVCODE_MOBILE_ALREADY_EXISTS(-31990, "手机号码已经注册"),


    //----SHOP_ERROR_CODE------店铺的错误代码
    SHOP_ID_INVALID(-31976, "店铺不存在"),
    SHOP_ALREADY_EXISTS(-31950, "店铺已经存在"),
    SHOP_STATUS_NOT_PENDING_AUDIT(-30898, "店铺不是待审核状态"),
    SHOP_NOT_CAN_UPDATE_INFO(-30888, "已经开通服务，不能更新店铺店名，行业，区域信息"),
    SHOP_REFRESH_ONLY_ONE_PER_WEEK(-31960, "每天只能刷新一次"),
    SHOP_GREATER_UPPER(-31968, "店铺数量超出上限"),
    SHOP_ALREADY_DELETED(-31967, "店铺已经被删除"),
    //Code30899(-30899,"店铺Id无效  已经有了31976冲突

    //------COLLECT_ERROR_CODE------收藏的错误代码
    COLLECT_EXISTS(-31295, "已经收藏过相同的"),
    COLLECT_NOT_EXISTS(-31294, "不存在的收藏"),


    //----OPPORTUNITY_ERROR_CODE------商机的错误代码
    OPPORTUNITY_EXISTS(-31195, " 商机已存在"),
    OPPORTUNITY_TAKEN(-31198, " 商机被领走"),
    OPPORTUNITY_LOCKED(-31194, "商机已被锁定"),
    OPPORTUNITY_SERVICEING(-31196, "商机已在服务中"),
    OPPORTUNITY_NOT_EXIST(-31197, " 商机不存在"),
    UNSUPPORTED_REMARK_OPERATION(-31193, "不支持的备注操作"),
    OPPORTUNTY_NOT_CERTIFATION(-31190, "认证才可以添加备注"),
    OPPORTUNITY_NOT_COLLECT(-31188, "还未被收藏"),
    OPPORTUNITY_ALREADY_COLLECTED(-31187, "已经被收藏"),
    OPPORTUNITY_STATUS_ERROR(-31186, "商机状态错误"),
    OPPORTUNITY_OTHER_LOCK_NOT_TO_UPDATE(-31185, "信息已被他人锁定，不能修改"),
    OPPORTUNITY_OTHER_ALREADY_UPDATED(-31184, "修改失败，信息有新的变化"),
    OPPORTUNITY_NOT_OWNER_TO_NOT_UPDATE(-31183, "不是商机发布者，不能更新操作"),
    OPPORTUNITY_INVITATION_TODAY_EXIST(-31182, "今天已经邀约过"),
    OPPORTUNITY_ALREADY_BUY_OR_FREE(-31181, "信息已经购买或者已经免费"),
    OPPORTUNITY_CAN_NOT_FREE(-31180, "已经超过免费体验的条数限制"),
    OPPORTUNITY_CONTRACT_NOT_EXIST(-31179, "商机交易记录不存在"),
    OPPORTUNITY_USER_DONT_HAVE(-31178, "用户没有拥有商机"),
    OPPORTUNITY_USER_NOT_EXIST(-31177, "商机操作信息不存在"),
    OPPORTUNITY_INVAILD(-31176, "商机无效不能购买"),
    OPPORTUNITY_REFRESH_ONLY_ONE_PER_DAY(-31175, "每天只能刷新一次"),
    OPPORTUNITY_NOT_OWNER_TO_NOT_REFRESH(-31174, "不是商机发布者，不能刷新操作"),
    OPPORTUNITY_REMARK_NOT_EXISTS(-31173, "商机备注不存在"),
    OPPORTUNITY_REMARK_NOT_OWNER_TO_NOT_UPDATE(-31172, "不是商机备注添加人，不能更新操作"),
    OPPORTUNITY_ALREADY_CLOSE(-31171, "商机已关闭"),
    OPPORTUNITY_PLUGIN_ERROR(-31169, "商机类型不正确"),
    OPPORTUNITY_GREATER_UPPER(-31168, "商机数量超出上限"),
    OPPORTUNITY_BLACK_LIST(-31167, "手机黑名单"),
    ACCREDIT_IS_CURENT_MOBILE(-31166, "是当前用户手机，无需给自己授权"),
    ACCREDIT_MOBILE_IS_EXISTS(-31165, "手机号码已授权"),
    ACCREDIT_MOBILE_COUNT_BEYOND(-31164, "商机超出授权数量"),
    ACCREDIT_HAS_CANCEL(-31163, "已取消授权"),
    OPPORTUNITY_NOT_BUY(-31162, "商机还没有收费"),
    OPPORTUNITY_HAVE_BUY_BUT_HISTORY(-31161, "商机收费但为历史状态"),
    OPPORTUNITY_NOT_CURENT_USER(-31160, "商机不属于当前用户的"),
    USER_NOT_CURENT_OR_HAS_ACCREDIT(-31159, "操作者不是该服务（商机）拥有者或授权者"),
    OPPORTUNITY_ALREAD_VALID(-31158,"此商机已经审核过"),

    //----RESOURCE_ERROR_CODE------资源的错误代码
    RESOURCE_NOT_EXIST(-31701, " 资源不存在"),
    RESOURCE_UNSUPPORTED_OPERATION(-31702, " 不支持的资源操作"),
    RESOURCE_DUPLICATED(-31703, " 资源重复异常"),
    RESOURCE_ADD_SELF(-31704, " 添加自己为资源的异常"),
    RESOURCE_STATUS_NOT_INVAILD(-31705, "资源不是无效状态"),
    RESOURCE_APPEAL_AUDIT_REPEAT_OPERATION(-31706, "不允许重复审核"),
    RESOURCE_APPEAL_SELF_COMMIT(-31707, "不允许申诉他人的资源"),
    APPEAL_NOT_EXIST(-31708, " 申诉记录不存在"),
    APPEAL_NOT_AUDIT(-31709, " 申诉记录还未审核"),
    APPEAL_REASON_IS_NULL(-31710, " 请选择申诉原因"),
    APPEAL_REMARK_IS_NULL(-31711, " 请填写详细情况"),
    APPEAL_HAS_EXIST(-31712, " 申诉记录已经存在"),
    APPEAL_HAS_AUDIT(-31713, " 申诉记录已经审核"),


    //----REQUIREMENT_ERROR_CODE------需求的错误代码
    REQUIREMENT_NOT_EXIST(-31800, "需求不存在"),
    REQUIREMENT_UNSUPPORTED_OPERATION(-31801, "需求不支持这种操作"),
    REQUIREMENT_ACCESS_EXCEPTION(-31802, " 无权刷新的时候会弹出这个消息"),


    //----PLUGIN_ERROR_CODE------插件的错误代码
    PLUGIN_NOT_EXIST(-31101, "插件不存在"),


    //----PREFERENCE_ERROR_CODE------偏好的错误代码
    PREFERENCE_NOT_EXIST(-31601, "偏好不存在"),
    NO_PERMISSION_GET_PREFERENCE(-31602, "不能查看别人资源的偏好详情"),


    //----SERVICE_ERROR_CODE------服务的错误代码
    SERVICE_STATUS_HAS_START(-31974, "该服务已经是开始状态"),
    SERVICE_ALREADY_EXISTS(-31599, "该商户已经存在有效服务/已经在试用"),
    SERVICE_ALREADY_CLOSE(-31598, "该服务已经关闭"),
    SERVICE_NOT_EXISTS(-31597, "服务不存在"),
    SERVICE_ACTIVING(-31596, "服务还是激活的没有关闭"),
    SERVICE_PAID_WAIT_AUDIT(-31592, "合同已经支付"),
    SERVICE_EXAMPLE_NOT_CREATE(-31576, "服务还没有创建案例"),
    SERVICE_NOT_TRYING(-31595, "服务不是试用状态"),
    SERVICE_NOT_CLOSE(-31591, "服务还未关闭"),
    SERVICE_NOT_OPEN(-31593, "服务不是开通状态"),
    SERVICE_TIMEOUT_NOT_RATING(-31590, "服务超时未评价"),
    SERVICE_REPEAT_RATING(-31589, "重复评价"),
    SERVICE_NOT_TAKED_STATUS(-31587, "此服务不是可领取状态"),
    SERVICE_REPEAT_ABANDON_CUSTOMER_SERVICE(-31585, "此服务已经申请放弃，不能重复操作"),
    SERVICE_NOT_CUSTOMER_SERVICE(-31584, "客服没有领取此服务"),
    SERVICE_ONLY_CURRENT_CSS_CAN_SEE(-31600, "只有当前客服才能查看"),
    SERVICE_ONLY_CURRENT_CSS_AND_MERCHANT_CAN_ADD(-31610, "只有当前客服或商户才能添加"),
    SERVICE_ONLY_CURRENT_CSS_AND_MERCHANT_CAN_SEE(-31611, "只有当前客服或商户才能查看"),
    SERVICE_ONLY_CURRENT_MERCHANT_CAN_OPINION(-31612, "只有当前商户才能评价"),
    EXAMPLE_HAS_RECOMMEND(-31588, "案例已经推荐"),
    EXAMPLE_NOT_EXISTS(-31575, "案例Id不存在"),
    EXAMPLE_NOT_ALLOWED_OPERATION(-31586, "案例不允许操作"),
    CUSTOMER_SERVICE_NOT_EXISTS(-31583, "客服服Id不存"),
    CUSTOMER_SERVICE_NOT_ABONDON(-31582, "客服服务不是申请放弃"),
    CUSTOMER_SERVICE_TAKE_UPPER(-31581, "领取客服服务已超上限"),
    CUSTOMER_SERVICE_ONLY_CANCEL_ABANDON(-31580, "若继续服务请撤消申请放弃"),
    SERVICE_STATUS_ERROR(-31579, "服务状态错误"),
    SERVICE_EXPIRE(-31578, "服务关闭90天，不能重新上线"),
    SERVICE_NOT_RESERVATION(-31577, "此服务不是预订服务"),
    SERVICE_PLUGINID_ERROR(-31576, "服务插件错误"),
    SERVICE_OPINION_NOT_IN_CYCLE(-31574, "距离上次评价还未超过许可周期时间，不允许评价"),
    SERVICE_HISTORY_NOT_EXISTS(-31573, "服务历史记录不存在"),
    SERVICE_REMARK_NOT_EXISTS(-31572, "服务备注记录不存在"),
    SERVICE_HISTORY_NOT_OWNER_TO_NOT_UPDATE(-31571, "不是服务历史记录添加人，不能更新操作"),
    SERVICE_REMARK_NOT_OWNER_TO_NOT_UPDATE(-31570, "不是服务备注记录添加人，不能更新操作"),
    SERVICE_ENDDATE_CANNOT_GT_TODAY(-31569, "结束日期不能早于今天"),
    SERVICE_SURPLUS_NOT_ENOUGH(-31568,"余额不足"),
    SERVICE_REPEAT_MOBILE_MESSAGE(-31567, "每天只能发送一次评价短信"),
    //----PAY_ERROR_CODE------支付合同的错误代码
    TRADE_NOT_EXISTS(-30799, "交易记录不存在"),
    TRADE_REPEAT(-30798, "不能重复支付"),
    TRADE_AMOUNT_GREATER_ZERO(-30797, "合同总金额为0"),
    TRADE_CONTRACT_ID_NOT_EXISTS(-30699, "合同ID不存在"),
    TRADE_CONTRACT_CREATED(-30698, "合同已经被创建"),
    CONTRACT_STATUS_INVALID(-30697, "合同状态不正确"),
    CONTRACT_NOT_ADVERTISING(-30696, "该签约无选择广告位"),
    TRADE_STATUS_ERROR(-30695, "交易状态错误"),
    MERCHANT_ACCREDIT_NOT_EXISTS(-30694, "服务授权不存在"),
    RECHARGE_PACKAGE_NOT_EXISTS(-30693, "充值套餐不存在"),


    //----BUSINESS_ERROR_CODE------业务的错误代码
    BUSINESS_OPPORTUNITY_NOT_FOUND(-31199, " 业务：商机不存在"),
    BUSINESS_BUSINESS_TAKEN(-31198, " 业务：已经被领取了"),
    BUSINESS_USER_NOT_VERIFIED(-30990, " 业务：用户没有认证，不能领取"),
    BUSINESS_MAX_NUMBER_EXCEEDED(-30996, " 业务：已经超出用户最高保护限额"),
    BUSINESS_POSTPONED(-30993, " 业务：已经延期一次，不能再延期了"),
    BUSINESS_NOT_FOUND(-30999, " 业务：业务ID不存在"),
    BUSINESS_CLOSED(-30998, " 业务：业务已关闭"),
    BUSINESS_INVALID_REQUEST(-30997, " 业务：reason 字符串超过长度"),
    BUSINESS_COMPLETED(-30998, " 业务：业务已成交"),
    BUSINESS_NOT_TRIAL(-31595, " 合同：服务不在试用状态"),
    BUSINESS_CONTRACT_NOT_FOUND(-31594, "合同不存在"),// TODO： 与-30799含义重复
    BUSINESS_PREPAY_NOT_GREATER_PRICE(-30989, "预付款不能大于优惠价"),
    BUSINESS_NOT_IDENTY_CERTIFICATION(-30988, "没有身份证，不能领取业务"),
    BUSINESS_SIGNING_NOT_ABORT(-30987, "商户正在签约中，不能放弃业务"),
    BUSINESS_SERVICE_ALREADY_WAITING_FOR_AUDIT(-30986, "服务已经是待审核状态"),
    BUSINESS_BARGIN_NEED_GREATER_LOWER(-30985, "报价不能低于最低限额"),


    BUSINESS_USER_NOT_SALES(-31500, "当前用户不是此业务的所有者"),
    BUSINESS_STATUS_NOT_BUSINESS(-31501, "业务：业务状态不是业务中"),
    BUSINESS_ASSIST_ALREADY_TAKEN(-31502, "业务协助已经被领取"),
    BUSINESS_ASSIST_NOT_EXISTS(-31503, "业务协助Id不存在"),
    BUSINESS_ASSIST_ALREADY_ABORT(-31504, "业务协助已经放弃"),
    BUSINESS_ASSIST_NOT_REPEAT_APPLY(-31505, "业务协助不能重复申请"),
    BUSINESS_ASSIST_NOT_APPLY(-31506, "还未申请业务协助"),
    BUSINESS_ASSIST_GREATER_UPPER(-31507, "领取数量已经超过上限"),
    BUSINESS_MODIFY_OTHER_DATA(-31508, "不能修改他人数据"),
    BUSINESS_ASSIST_NOT_TAKE_SELF(-31509, "不能领取自己的业务"),
    BUSINESS_ASSIST_HAS_PAYING(-31510, "有业务签约，不能取消"),
    BUSINESS_TAKE_BY_OTHER_PROTECTED(-31511, "领取失败，已被其他业务员保护"),


    //----RECOMMENDATION_ERROR_CODE------推荐的错误代码
    RECOMMENDATION_RECOMMENDATION_NOT_EXISTED(-30599, "推荐不存在"),
    RECOMMENDATION_UNSUPPORTED_RECOMMENDATION_OPERATION(-30598, "商户不能处理此推荐"),
    RECOMMENDATION_MIDDLEMAN_RESOURCE_NOT_EXISTED(-30597, "推荐人没有此资源"),
    RECOMMENDATION_RECOMMENDATION_EXISTED(-30596, "推荐已经存在"),
    RECOMMENDATION_HANDLE_RESULT_ERROR(-30595, "handleResult不是合法的值"),
    RECOMMENDATION_REQUIREMENT_NOT_ONLINE(-30594, "需求没有上线"),
    RECOMMENDATION_BUSINESS_NOT_ONLINE(-30593, "业务不存在"),
    /* RECOMMENDATION_SERVICE_STATUS_IS_TRY(-30592, "商户服务状态为试用，不能处理推荐"),
     RECOMMENDATION_SERVICE_STATUS_IS_CLOSE(-30591, "商户服务状态为已关闭，不能处理推荐"),
     RECOMMENDATION_SERVICE_STATUS_IS_EXPIRED(-30590, "商户服务状态为已已过期，不能处理推荐"),*/
    RECOMMENDATION_SERVICE_STATUS_IS_ONT_OPEN(-30592, "商户服务状态不是Open状态，不能处理推荐"),
    RECOMMENDATION_ADD_REMARK_IS_NOT_MIDDLEMAN(-30590, "只有推荐人可以增加和查看推荐备注"),
    RECOMMENDATION_SERVICE_STATUS_IS_ONT_OPEN_OR_TRY(-30591, "服务状态不是试用或者已经收费，不能推荐"),
    RECOMMENDATION_ALREADY_HANDLED(-30589, "推荐已经处理过"),
    RECOMMENDATION_HAS_CHECKED(-30588, "推荐已经审核过"),
    ADD_ST_RECOMMENDATION_SERVICE_PLUGINID_ERROR(-30587, "服务的pluginId 只能为 xw:siting 或者 xw:transfer"),
    NO_PERMISSIONS_RECOMMENDE_THIS_RESOURCE(-30586, "没有权限对此资源进行推荐"),
    MERCHANT_NO_PERMISSIONS_GET_RECOMMENDATION(-30585, "非商户自己推荐记录不能查看详情"),
    NO_PERMISSIONS_LIST_OF_THIS_RESOURCE_RECOMMENDATIONS(-30584, "没有权限对此服务的推荐列表"),
    NO_PERMISSIONS_GET_STATISTICS_OF_THIS_RESOURCE(-30583, "不是资源的所有者没有权限获得此资源的推荐统计信息"),
    OPPORTUNITY_LAST_MARK_TIME_EXPIRED(-30582, "商机最后确认时间超过15天，不能推荐"),
    RECOMMENDATION_RESOURCE_PLUGINID_NOT_SUITABLE(-30581, "资源类型和商机插件类型不匹配"),
    RESOOURCE_HAS_DEAL(-30580, "资源已经成交，不能推荐"),
    RESOOURCE_INVALID(-30579, "资源无效，不能推荐"),
    RESOURCE_HISTORY_ADD_NOT_OWNER_OR_SALES(-30578, "不是资源发布者或者业务员，不能发布确认记录"),
    RECOMMENDATION_STATUS_CHANGE_NOT_ALLOW(-30577, "当前推荐状态不允许变更为该状态"),
    RECOMMENDATION_ALREADY_CANCEL(-30576, "此推荐记录已经撤销"),
    RECOMMENDATION_ALREADY_REVOVER(-30575, "此推荐记录已经恢复"),

    OPPORTUNITY_EXPIRED(-31186, " 商机已过期"),


    //----PRICE_ERROR_CODE------报价的错误代码
    PRICE_NOT_STANDED_PRICE(-30992, "根据Key没有找到标准报价"),
    QUOTE_NOT_EXISTS(-30991,"报价不存在"),
    QUOTE_PAID(-30990,"报价已付款"),

    // ---- PRODUCT -----------产品错误代码
    PRODUCT_NOT_EXISTS(-30499, "商品不存在"),
    PRODUCT_CATEGORY_ALREADY_EXISTS(-30498, "此商品分类已经存在"),
    PRODUCT_CATEGORY_NOT_EXISTS(-30497, "此商品分类不存在"),
    THIS_DATA_BEING_USED(-30496, "此数据已经被引用，不能删除"),
    PRODUCT_SPECIAL_NOT_GREATER_ORGPRICE(-30495, "特价不能大于原价"),

    //---- RATING ----([-30199]-[-30100])----评价错误代码
    RATING_NOT_EXISTS(-30199, "评论不存在"),
    CONTENT_TOO_LONG(-30195, "评价内容超长"),
    ORDER_NOT_COST(-30197, "订单还未消费，不能评价"),
    ORDER_SHOP_NOT_MATCH(-30198, "订单与店铺Id不匹配"),
    RATING_EXISTS(-30196, "评论已经存在，不能重复评论"),
    RATING_NOT_SELF_ORDER(-30194, "不允许评价非自己的订单"),

    // ---- order status ------
    ORDER_NOT_EXISTS(-30299, "订单不存在"),
    ORDER_CHECK_CODE_NOT_EXISTS(-30298, "验证码无效"),
    ORDER_NOT_BOOK_STATUS(-30297, "订单状态不是已预定状态"),
    ORDER_CITY_NOT_EXISTS(-30296, "城市还未开通"),
    ORDER_ALREADY_USED(-30295, "已经消费"),
    ORDER_ID_IS_NOT_MY_SHOP(-30294, "非本店订单"),

    //----ACTIVITY_ERROR_CODE------活动的错误代码（-30399至-30300）
    ACTIVITY_NOT_EXISTS(-30399, "活动不存在"),
    ACTIVITY_HAS_OFFLINE(-30398, "活动已经过期"),
    ACTIVITY_OFFLINETIME_INVALID(-30397, "结束时间不能比当前时间小"),
    ACTIVITY_PRODUCT_NOT_EXISTS(-30390, "活动没有选择商品"),
    ACTIVITY_STATUS_INVALID(-30384, "活动状态有误"),
    ACTIVITY_STATUS_OFFLINE_INVALID(-30388, "活动已经下线"),
    ACTIVITY_STATUS_ONLINE_INVALID(-30387, "活动已经上线"),
    ACTIVITY_STATUS_ONLINE_ON_INVALID(-30386, "活动还未发布"),


    VERSION_TOO_LOW(-31901, "版本过低，请升级"),
    SERVER_UPGRADE(-31902, "服务器升级中"),
    DISTRICT_NOT_EXIST(-31903, "区域不存在"),
    BASIC_DATA_EXPIRED(-31904, "基础数据过期"),
    REPEAR_REQUEST_TIMES(-31905, "重复调用接口"),


    ROLE_NOT_EXISTS(-30099, "角色不存在"),
    NO_HAVING_PERMIT(-30098, "没有权限"),
    ROLE_NAME_EXISTS(-30097, "角色名称已经存在"),
    USER_STATUS_ERROR(-30096, "用户状态错误"),
    USER_NAME_EXISTS(-30095, "该用户名已经存在"),
    VIDEO_STATUS_ERROR(-30094, "视频服务状态错误"),
    VIDEO_REWARD_AGAIN(-30093, "不能重复发送奖励"),
    VIDEO_TYPE_ERROR(-30092, "服务类型错误"),
    VIDEO_CANCEL_REWARD_AGAIN(-30091, "重复取消奖励"),
    VIDEO_NOT_EXISTS(-30090, "视频不存在"),


    NOT_EXIST_SETTELD_REWARD(-29999, "不存在可结算的奖励"),

    SUPPLY_ACHIEVEMENT_NOT_EXISTS(-29899, "补充业绩记录不存在"),
    SUPPLY_ACHIEVEMENT_STATUS_ERROR(-29898, "补充业绩状态错误"),

    REFUND_DATA_EXISTS(-29799, "已经存在退款记录"),
    REFUND_BUSINESS_NOT_EXISTS(-29798, "没有对应的业务员"),
    REFUND_DATA_NOT_EXISTS(-29797, "退款记录不存在"),
    CONTRACT_REPEAT_AUDIT(-29796, "重复审核"),
    CONTRACT_NEED_CHIEF_AUDIT(-29800, "合同需要先总监审核"),
    CONTRACT_NEED_FINANCE_AUDIT(-29795, "合同需要先财务审核"),
    CONTRACT_STATUS_CANNOT_CANCLE(-29794, "合同状态不能取消审核"),

    REFUND_REPEAT_AUDIT(-29793, "退款重复审核"),
    REFUND_STATUS_ERROR(-29792, "退款状态错误"),
    REFUND_NEED_CHIEF_AUDIT(-29791, "退款需要先总监审核"),

    ACCUSE_DATA_NOT_EXISTS(-29699, "举报记录不存在"),
    ACCUSE_DATA_NO_AUDIT(-29698, "举报信息还未审核"),
    ACCUSE_DATA_REPEAT_AUDIT(-29697, "举报信息不能重复审核"),

    //招聘错误代码
    RECRUITMENT_POSITION_NOT_EXISTS(-29599, "招聘职位不存在"),
    RECRUITMENT_INFO_NOT_EXISTS(-29598, "招聘资料不存在"),
    RECRUITMENT_POSITION_STATUS_ERROR(-29597, "招聘职位需求状态错误"),
    RECRUITMENT_POSITION_REPEAT_REFRESH(-29596, "不能重复刷新"),
    RECRUITMENT_POSITION_OVER(-29595, "招聘职位已经结束"),

    DISCOUNT_NOT_EXISTS(-29701, "优惠单不存在"),
    DISCOUNT_INVALID(-29702, "优惠单无效"),
    DISCOUNT_NO_TAKE(-29703, "优惠单不能领取"),
    DISCOUNT_TAKE_NOT_EXISTS(-29704, "还没有领取优惠单"),
    DISCOUNT_NOT_SUIT(-29705, "优惠单不适用于当前店铺"),
    DISCOUNT_ALREADY_USE(-29706, "优惠单已经核销"),
    DISCOUNT_NO_CHECK(-29707, "优惠单不能核销"),
    DISCOUNT_TAKE_REPEAT(-29708, "优惠单不能重复领取"),
    DISCOUNT_NO_CHECK_RESAON_MINAMOUNT(-29709, "优惠单不能核销,未达到最低消费金额"),
    DISCOUNT_NO_CHECK_RESAON_TIMELIMIT(-29710, "优惠单不能核销,不在使用时间范围内"),


    //-----RESUMERESOURCE_ERROR_CODE ----------简历资源错误代码

    RESUMERESOURCE_OTHER_ALREADY_UPDATE(-29896, "修改失败，简历内容有变化"),
    RESUMERESOURCE_NOT_EXIST(-29895, "简历资源不存在"),
    RESUMERESOURCE_CANNOT_ADD(-29894, "添加简历失败"),

    //----招聘推荐-----
    RECRUITMENT_RECOMMENDATION_NOT_EXISTS(-31560, "推荐项Id不存在"),
    RECRUITMENT_ALREADY_RECOMMENDATION(-29849, "已经做过推荐，不能重复做"),
    RECRUITMENT_ALEADY_ABANDON(-29848, "已经放弃"),
    RECRUITMENT_STATUS_NOT_ALLOW(-29847, "处理状态异常，不能放弃"),
    RECRUITMENT_HANDLE_STATUS_WRONG(-29846, "状态不正确，不能处理"),
    RECRUITMENT_ALRADY_ABANDON(-29845, "简历已放弃，不能处理"),
    RECRUITMENT_NOT_RECOMMENDATION(-29844, "还未有推荐"),
    RECRUITMENT_ALREADY_SEND_SMS(-29843, "已经发送过短信"),
    RECRUITMENT_ERROR_INTERVIEW_TIME(-29842, "面试时间不能早于当前时间"),


    //----RESUME_ERROR_CODE------简历的错误代码
    RESUME_IS_EXISTS(-29869, "简历已经存在"),
    RESUME_NOT_EXISTS(-29868, "简历不存在"),
    RESUME_REFRESH_INTEVAL(-29867, "刷新的太快，请稍后再试"),
    RESUME_OTHER_LOCK_NOT_TO_UPDATE(-29866, "简历已锁定，不允许其他人修改"),
    RESUME_VERIFIED_NOT_TO_UPDATE_REALNAME(-29865, "身份已经认证，姓名不可修改"),
    RESUME_VERIFIED_REALNAME_NOT_RIGHT(-29864, "身份已经认证，姓名匹配错误"),
    BIRTHDAY_NOT_RIGHT(-29863, "出生年月不小于1949且不超过当前日期"),

    // ------------- 员工资料错误代码 -----------------
    EMPLOYEE_NOT_EXISTS(-29862, "员工不存在"),
    EMPLOYEE_STATUS_ERROR(-29860, "员工当前状态，不能进行操作"),
    EMPLOYEE_NOT_EXIST(-29870, "员工不存在"),
    SHOP_STAFF_NOT_EXIST(-29871, "员工资料不存在"),
    STAFF_STATUS_ERORR(-29872, "员工状态错误"),
    STAFF_CAN_NOT_OPERATE(-29873, "员工不能操作"),
    EMPLOYEE_POSITION_NOT_EXISTS(-29874, "员工职位不存在"),

    MESSAGE_SET_READ_DENY(-29859, "只允许设置自己接收的留言为已阅."),
    MESSAGE_NOT_EXISTS(-29856, "留言不存在"),
    CAN_NOT_LEAVE_MESSAGE_FOR_ONESELF(-29855, "不能留言给自己"),
    MESSAGE_FAILED(-29854, "留言失败"),
    DAILY_HAS_EXISTED(-29853, "日报已经存在了."),
    DAILY_NOT_EXISTED(-29852, "日报未提交."),
    DAILY_UPDATE_DENY(-29857, "修改日报失败"),
    STAFF_ACCOUNT_NOT_EXISTS(-29858, "员工账号不存在"),


    MERCHANT_POSITION_IS_EXISTS(-29851, "店铺职位已存在，不能重复添加"),
    MERCHANT_POSITION_NOT_EXISTS(-29850, "店铺职位不存在"),
    ONLY_CAN_DELETE_OWN_MERCHANT_POSITION(-29861, "只能删除自己创建的职位"),

    // ------------- 顾客相关错误代码 -----------------
    LABEL_HAS_EXISTED(-29839, "此标签已存在"),
    CUSTOMER_NOT_EXISTS(-29838, "顾客不存在"),

    SMS_PURCHASE_NOT_EXISTS(-29840, "短信套餐不存取"),
    SMS_TEMPLATE_NOT_EXISTS(-29873, "短信收藏或短信秘籍不存在"),
    SMS_PURCHASE_STATUS_ERROR(-29841, "短信套餐状态不可用"),
    SMS_NUM_NOT_ENOUGH(-29837, "短信数量不足"),
    SEND_SMS_USER_TOO_MUCH(-29836, "发送短信用户太多,请稍后再试"),
    FOUR_DAT_REPEAT_SEND(-29835, "四天内不能重复发送短信"),
    SMS_DATA_NOT_EXISTS(-29834, "短信记录不存在"),


    // ----------------广告相关错误代码 ---------------
    ADVERTISEMENT_NOT_EXISTS(-29829, "广告不存在"),
    STAFF_ACCOUNT_LIMIT(-29828, "员工数量达到上限"),
    STAFF_NOT_OPERATOR(-29827, "员工不允许操作"),
    SUPPLY_AD_NOT_EXISTS(-29826, "补充广告id不存在"),
    SUPPLY_AD_IS_EXISTS(-29825, "补充广告已存在"),


    // ----------------折扣相关错误代码 ---------------
    DISCOUNT_LIMIT(-29810, "优惠数量已达到上限"),
    DISCOUNT_STATUS_ERROR(-29808, "优惠状态非法操作"),
    DISCOUNT_NOT_GREATER_MIN_CONSUMPTION(-29807, "减免金额不能超过最低消费金额"),


    // ----------------案例视频相关错误代码 ---------------
    EXAMPLE_VIDEO_NOT_EXISTS(-29790, "案例视频不存在"),


    //--------招商与加盟 ----------------------------------
    BRAND_INFO_NOT_EXISTS(-29789, "招商信息不存在"),
    BRAND_INFO_NOT_RECEIVE(-29787, "不能领取别人的招商信息"),
    BRAND_INFO_ALREADY_EXISTS(-29781, "招商信息已经存在"),
    BRAND_INFO_GREATER_UPPER(-29780, "已经超出发布上限"),
    LEAGUE_INDUSTRY_LIMIT(-29777, "行业数据超出上限"),
    ID_ALREADY_DELETED(-29786, "该信息已经删除"),
    NOT_POWER_DELETE_INFO(-29785, "无权删除该信息"),
    NOT_POWER_UPDATE_INFO(-29783, "不能更新他人信息"),
    BRAND_INFO_ALREADY_RECEIVE(-29784, "该招商信息已经被领取"),
    LEAGUE_INFO_ALREADY_EXISTS(-29782, "此加盟信息已经存在"),
    LEAGUE_INFO_NOT_EXISTS(-29788, "加盟信息不存在"),
    BRAND_INFO_CONTRACT_NOT_EXISTS(-29778, "招商合同不存在"),
    DIAL_LEAGUE_INFO_GREATER_UPPER(-29779, "拨打加盟电话超出上限"),
    EXAMPLE_VIDEO_EXISTS(-29776, "案例视频已存在"),
    SUPPLY_NOT_EXISTS(-29775, "补充业绩Id不存在"),
    REFUND_NOT_EXISTS(-29774, "退款单Id不存在"),
    ALLOCTION_NOT_EXISTS(-29773, "业务分单Id不存在"),
    STOPSERVICE_NOT_EXISTS(-29772, "暂停服务申请单Id不存在"),
    DELAYSERVICE_NOT_EXISTS(-29771, "延期服务申请单Id不存在"),
    NOT_NEED_AUDIT_STATUS(-29770, "不是待审核状态，不能操作"),
    ALLOCTION_AMOUNT_GT_UPPER(-29769, "分单金额超出可分单金额"),
    DELAY_SERVICE_NOT_ALLOW(-29768, "按次服务不允许延期"),
    REFUND_AMOUNT_NOT_GT_TOTAL(-29767, "退款金额不能超过可退总额"),
    BRAND_INFO_NEWS_NOT_EXIST(-29766, "招商留言不存在"),
    BRAND_INFO_NEWS_ALREADY_DELETED(-29765, "招商留言已删除"),
    BRAND_INFO_NEWS_NOT_OWNER(-29764, "不是留言者"),
    INVITE_TODAY_FINISH(-29763, "今天已经邀约过"),
    MONEY_NOT_ENOUGH(-29762, "钱包旺豆不够"),
    SYSTEM_TOO_BUSIY(-29761, "系统太忙,稍后再试"),
    USER_WALLET_NOT_EXISTS(-29760, "用户钱包不存在"),
    USER_WALLET_RECORD_NOT_EXISTS(-29759, "用户消费记录不存在"),
    REFUND_AMOUNT_ERROR(-29758, "退款失败"),
    ALLOCATION_USER_IS_OPP_BUSINESS_USER(-29757, "业务员本人不能分单"),
    USER_STILL_HAS_CHECK_ALLOCATION(-29756, "该人员有待审核分单"),



    //--------投诉相关 ----------------------------------
    FEEDBACK_NOT_EXIST(-29740, "投诉记录不存在"),
    FEEDBACK_IS_FINISH(-29739, "投诉已处理完结"),


    //--------支出系统相关 ----------------------------------
    BUDGET_SUBJECT_CODE_IS_EXIST(-29640, "科目编号已存在"),
    BUDGET_SUBJECT_NAME_IS_EXIST(-29639, "科目名称已存在"),
    BUDGET_SUBJECT_CATEGORY_NOT_EXIST(-29638, "科目分类不存在"),
    BUDGET_SUBJECT_NOT_EXIST(-29637, "科目不存在"),
    BUDGET_SUBJECT_NAME_OR_CODE_IS_EXIST(-29636, "科目名称或编号已存在，不能恢复！"),
    BUDGET_SUBJECT_STATUS_ERROR(-29635, "科目状态不正确"),
    BUDGET_NOT_EXIST(-29634, "预算不存在"),
    BUDGET_STATUS_ERROR(-29633, "预算状态错误"),
    BUDGET_REPEAT_PAY(-29632, "预算重复支付"),
    BUDGET_NOT_NEEDBORROW(-29631,"预算单不需要借支"),
    BUDGET_BORROW_NOT_EXISTS(-29630,"借支单不存在"),
    ALREAD_DELETED(-29629,"已经被删除"),
    NOT_CANCEL_OTHER_AUDIT(-29628,"不能操作别人审核记录"),
    NEXT_STEP_ALREAD_AUDIT(-29627,"下行已经审核"),
    BUDGET_BORROW_EXISTS(-29626,"借支单已存在"),

    //-----------离职员工扣点管理-------------------------------------------
    DEDUCT_REPEAT(-29641,"扣点记录已存在"),
    DEDUCT_NO_OPPORTUNITY(-29642,"没有符合要求的商机信息"),

    ;


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    int code;
    String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
