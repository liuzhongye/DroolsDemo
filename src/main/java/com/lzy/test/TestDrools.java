package com.lzy.test;

import com.lzy.entity.ComparisonOperatorEntity;
import com.lzy.entity.Order;
import com.lzy.entity.Student;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

/**
 * step1:获取KieServices
 * step2:获取kieContainer
 * step3：kieSession
 * step4:Insert fact
 * step5:触发规则
 * step6：关闭KieSession
 */
public class TestDrools {

    @Test
    public void testDemo() {
        //step1: 拿到kieServices
        KieServices kieServices = KieServices.Factory.get();

        //step2：通过服务获取kieContainer
        KieContainer container = kieServices.getKieClasspathContainer();

        //step3：kieSession
        KieSession kieSession = container.newKieSession();

        //step4:Insert fact
        Order order = new Order();
        order.setAmout(320);
        kieSession.insert(order);

        //step5:触发规则
        kieSession.fireAllRules();

        //step6：关闭KieSession
        kieSession.dispose();
    }


    //测试比较操作符
    @Test
    public void test3() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieClasspathContainer.newKieSession();

        ComparisonOperatorEntity comparisonOperatorEntity = new ComparisonOperatorEntity();
        comparisonOperatorEntity.setNames("张三");
        List<String> list = new ArrayList<String>();
        list.add("张三");
        list.add("李四");
        comparisonOperatorEntity.setList(list);

        //将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配，如果规则匹配成功则执行规则
        kieSession.insert(comparisonOperatorEntity);

        //通过规则过滤器实现只执行指定规则
        kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("rule_comparison_memberOf"));

        //执行所有的规则
//        kieSession.fireAllRules();
        kieSession.dispose();
    }

    @Test
    public void test4() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieClasspathContainer.newKieSession();

        Student student = new Student();
        student.setAge(5);

        //将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配，如果规则匹配成功则执行规则
        kieSession.insert(student);

        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
