package com.design;

/**
 * Created By chaozhou
 * Date 2019/12/15 10:26
 * Description
 */
public class Lesson28 {
    public static void main(String[] args) {
        // 生成一个服务范畴类型为开户的业务代表
        BusinessDelegate businessDelegate = new BusinessDelegate();
        businessDelegate.setServiceType("doCreateCount");

        // 指定客户的业务代表为businessDelegate
        NormalCustomer client = new NormalCustomer(businessDelegate);
        // 客户办理业务
        client.doTask();

        // 生成一个服务范畴类型为存钱的业务代表
        businessDelegate.setServiceType("doSaveMoney");
        client.doTask();
    }
}

/**
 * 抽象业务服务
 */
interface BusinessService {
    // 要进行的业务-开户
    void doCreateCount();
    // 要进行的业务-存钱
    void doSaveMoney();
}

/**
 * 服务窗口-张
 */
class BusinessConsultantZh implements BusinessService {
    @Override
    public void doCreateCount() {
        System.out.println("服务窗口-张：开户成功");
    }

    @Override
    public void doSaveMoney() {
        System.out.println("服务窗口-张：存钱成功");
    }
}

/**
 * 服务窗口-李
 */
class BusinessConsultantLi implements BusinessService {
    @Override
    public void doCreateCount() {
        System.out.println("服务窗口-李：开户成功");
    }

    @Override
    public void doSaveMoney() {
        System.out.println("服务窗口-李：存钱成功");
    }
}

/**
 * 业务查询服务
 */
class BusinessLookUp {
    /**
     * 根据具体的业务类型，查询具体的业务类型的办理人（服务）
     * @param serviceType
     * @return
     */
    public BusinessService getBusinessService(String serviceType) {
        // 开户业务暂由 BusinessConsultantZh 窗口办理
        if ("doCreateCount".equalsIgnoreCase(serviceType)) {
            return new BusinessConsultantZh();
        } else {
            // 存钱业务暂由 BusinessConsultantLi 窗口办理
            return new BusinessConsultantLi();
        }
    }
}

/**
 * 业务顾问或者大堂业务专员
 */
class BusinessDelegate {
    // 业务查询
    private BusinessLookUp lookUpService = new BusinessLookUp();
    // 业务服务
    private BusinessService businessService;
    // 服务类型
    private String serviceType;

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
    /**
     * 业务顾问的服务方法
     */
    public void doTask() {
        // 查找具体的服务窗口
        businessService = lookUpService.getBusinessService(serviceType);
        // 委托窗口进行业务处理
        if (serviceType.equalsIgnoreCase("doCreateCount")) {
            businessService.doCreateCount();
        } else {
            businessService.doSaveMoney();
        }
    }
}

/**
 * 普通客户
 */
class NormalCustomer {
    // 要提供服务的业务代表
    private BusinessDelegate businessDelegate;

    public NormalCustomer(BusinessDelegate businessDelegate) {
        this.businessDelegate = businessDelegate;
    }
    // 客户端的业务
    public void doTask() {
        // 委托业务代表处理
        businessDelegate.doTask();
    }
}
