package jdk.jdk8.collection;

import com.sun.istack.internal.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package: jdk.jdk8.collection<br>
 * @ClassName: CompareTest.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class CompareTest {
  public static void main(String[] args) {
    List<WarnDeployVo> warnDeployVos = new ArrayList<>();
    warnDeployVos.add(new WarnDeployVo("0", "0t", false, 0L));
    warnDeployVos.add(new WarnDeployVo("1", "1t", true, 1L));
    warnDeployVos.add(new WarnDeployVo("2", "2t", false, 2L));
    warnDeployVos.add(new WarnDeployVo("3", "3t", true, 3L));
    warnDeployVos.add(new WarnDeployVo("4", "4t", false, 4L));
    warnDeployVos.add(new WarnDeployVo("5", "5t", true, 5L));
    warnDeployVos.add(new WarnDeployVo("6", "6t", false, 6L));
    warnDeployVos.sort(WarnDeployVo::compareTo);
    System.out.println(warnDeployVos.size());
  }
}
class WarnDeployVo implements Comparable<WarnDeployVo> {

  /**
   * 统计数
   */
  private Long warnCount;

  /**
   * 布控类型名称
   */
  private String name;

  /**
   * 布控类型id
   */
  private String deployType;

  /**
   * 是否是自定义类型
   */
  private boolean isDefault;

  public Long getWarnCount() {
    return warnCount;
  }

  public void setWarnCount(Long warnCount) {
    this.warnCount = warnCount;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDeployType() {
    return deployType;
  }

  public void setDeployType(String deployType) {
    this.deployType = deployType;
  }

  public boolean isDefault() {
    return isDefault;
  }

  public void setDefault(boolean aDefault) {
    isDefault = aDefault;
  }

  public WarnDeployVo(String name, String deployType, boolean isDefault, Long count) {
    this.warnCount = count;
    this.name = name;
    this.isDefault = isDefault;
    this.deployType = deployType;
  }

  /**
   * 若当前与传进来的值，判断为负数，则按正序排列
   * @param o
   * @return
   */
  @Override
  public int compareTo(@NotNull WarnDeployVo o) {
    if(o.isDefault() == this.isDefault) {
      return 0;
    }
    if(this.isDefault) {
      return -1;
    }
    return 1;
  }
}
