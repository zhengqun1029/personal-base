package controllerTest;

import com.personal.biz.Application;
import com.personal.biz.controller.TestController;
import com.personal.biz.service.TestService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * project: com.personal.ssm
 *
 * @author zhenghanbin time: 2018/10/25 14:46
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class Test {

    @Autowired
    private TestService testService;

    @Autowired
    private TestController testController;

    @org.junit.Test
    public void test() {
        System.out.println(testController.heartbeat());
    }


}
