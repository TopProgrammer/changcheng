package site.changcheng.admin;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
@Component
public class TestApplicationRunner implements ApplicationRunner {

  @Override public void run(ApplicationArguments args) throws Exception {
    System.out.println("服务启动，TestCommandLineRunner执行启动加载任务.start..");
    if (null != args) {
      System.out.println(args.getOptionNames());
    }
    System.out.println("服务启动，TestCommandLineRunner执行启动加载任务.end..");
  }
}