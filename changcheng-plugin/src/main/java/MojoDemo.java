import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 *
 * @author 王鑫
 *
 *         该类就是maven自定义插件类
 *
 */
// mojo注解就是maven插件的注解，具体什么我忘记了。name就是后面使用该插件的时候excuation里面的，后面配置的是生命周期，我这里配置了install，即默认是安装时候执行本插件
@Mojo(name = "mojoDemo1", defaultPhase = LifecyclePhase.INSTALL)
public class MojoDemo extends AbstractMojo {

  // 配置的是本maven插件的配置，在pom使用configration标签进行配置 property就是名字，在配置里面的标签名字。在调用该插件的时候会看到
  @Parameter(property = "name")
  private String name;

  @Parameter(property = "modules")
  private List<String> modules;// 执行的相关代码，即插件执行的语句。
  @Override
  public void execute() throws MojoExecutionException, MojoFailureException {
    System.out.println("!!!!!!!!!!!!!!!!!!!!");

    System.out.println(name);

    System.out.println("~~~~~~~~~~~~~~~~~~~~");

    System.out.println(modules);

    for (String string : modules) {
      System.out.println(string);
    }

    System.out.println("!!!!!!!!!!!!!!!!!!!!");
  }

}