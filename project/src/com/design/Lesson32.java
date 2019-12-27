package com.design;

import java.util.LinkedList;
import java.util.List;

/**
 * Created By caiya
 * Date 2019/12/27 19:17
 * Description
 */
public class Lesson32 {
    public static void main(String[] args) {
        FilterManager filterManager = new FilterManager(new TargetResource() {
            @Override
            public void execute() {
                System.out.println("业务方法 doing...");
            }
        });
        Filter logFilter = new LogFilter();
        Filter authFilter = new AuthFilter();
        Filter ipFilter = new IPFilter();
        filterManager.addFilter(logFilter);
        filterManager.addFilter(ipFilter);
        filterManager.addFilter(authFilter);
        filterManager.filterRequest();
    }
}
// 过滤器接口
interface Filter {
    void doFilter(FilterChain filterChain);
}
// 过滤器链的接口
interface FilterChain {
    // 继续执行链上的下一个过滤器
    void execute();
    // 设置当前过滤器链过滤的目标对象
    void setTarget(TargetResource target);
    // 给过滤器链上添加过滤器
    void addFilter(Filter filter);
}

// 权限拦截过滤器
class AuthFilter implements Filter {
    @Override
    public void doFilter(FilterChain filterChain) {
        System.out.println("权限过滤器");
        // 这里指示是否继续执行下一个过滤器，如果选择不执行 execute，则请求截止，后续过滤器及目标对象不会执行
        filterChain.execute();
    }
}
// 日志过滤器
class LogFilter implements Filter {
    @Override
    public void doFilter(FilterChain filterChain) {
        System.out.println("日志过滤器");
        filterChain.execute();
    }
}
// ip过滤器
class IPFilter implements Filter {
    @Override
    public void doFilter(FilterChain filterChain) {
        System.out.println("IP过滤器");
//        filterChain.execute();
    }
}

// 目标对象
interface TargetResource {
    // 目标对象的业务方法
    void execute();
}

// 默认的过滤器链
class DefaultFilterChain implements FilterChain {
    // 定义一个过滤器链
    private List<Filter> filters = new LinkedList<>();
    // 该链对应的目标对象
    private TargetResource target;

    // 当前正在执行的过滤器索引
    private int currentFilterIndex = 0;

    // 设置目标对象
    @Override
    public void setTarget(TargetResource target) {
        this.target = target;
    }
    // 追加过滤器
    @Override
    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    @Override
    public void execute() {
        // 如果过滤器链为空或者当前已经执行到了最后一个过滤器，则继续执行业务方法，否则获取下一个过滤器，继续遍历执行
        if (filters.isEmpty() || filters.size() == currentFilterIndex) {
            target.execute();
        } else {
            Filter filter = filters.get(currentFilterIndex);
            currentFilterIndex++;
            filter.doFilter(this);
        }
    }
}

// 过滤管理器
class FilterManager {
    // 默认的过滤器链
    private FilterChain filterChain = new DefaultFilterChain();

    // 设置目标对象
    public FilterManager(TargetResource target) {
        this.filterChain.setTarget(target);
    }
    // 添加过滤器
    public void addFilter(Filter filter) {
        this.filterChain.addFilter(filter);
    }
    // 过滤器的入口
    public void filterRequest() {
        this.filterChain.execute();
    }
}