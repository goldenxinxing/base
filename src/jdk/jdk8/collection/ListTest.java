package jdk.jdk8.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package: jdk.jdk8.collection<br>
 * @ClassName: ListTest.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class ListTest {

  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    list.add("1");
    list.add("2");
    list.add("3");
    list.add("4");
    list.add("5");
    list.add("6");
    list.add("7");
    list.add("8");
    list.add("9");
    List<String> tmp = list.stream().limit(6).collect(Collectors.toList());
    System.out.println(tmp.size());

    ListFaceCompareGroup listFaceCompareGroup = new ListFaceCompareGroup();

    List<FaceCompareGroup> faceCompareGroups = new ArrayList<>();
    faceCompareGroups.add(new FaceCompareGroup(0f, 80f) {{
      setCompareResults(new ArrayList<String>() {{
        add("1");
        add("2");
        add("3");
      }});
    }});
    faceCompareGroups.add(new FaceCompareGroup(80f, 85f) {{
      setCompareResults(new ArrayList<String>() {{
        add("81");
        add("82");
        add("83");
      }});
    }});
    faceCompareGroups.add(new FaceCompareGroup(85f, 90f) {{
      setCompareResults(new ArrayList<String>() {{
        add("86");
        add("87");
        add("88");
      }});
    }});
    faceCompareGroups.add(new FaceCompareGroup(90f, 100f) {{
      setCompareResults(new ArrayList<String>() {{
        add("91");
        add("92");
        add("93");
      }});
    }});

    listFaceCompareGroup.setRecords(faceCompareGroups);

    List<String> tmp2 = listFaceCompareGroup.getRecords()
        .parallelStream()
        .flatMap(group -> group.getCompareResults().stream())
        .limit(6)
        .collect(Collectors.toList());
    System.out.println(tmp2.size());
  }
}

class ListData<T> {

  public List<T> getRecords() {
    return records;
  }

  public void setRecords(List<T> records) {
    this.records = records;
  }

  private List<T> records;
}

class ListFaceCompareGroup extends ListData<FaceCompareGroup> {

  /**
   * 结果全部数量
   */
  private Integer total = 0;
}

class FaceCompareGroup {

  public FaceCompareGroup(Float min, Float max) {
    this.min = min;
    this.max = max;
    this.interval = String.format("%f-%f", min, max);
  }

  /**
   * 比分区间 0-80 80=<x<85 85=<x<90 90=<x<=100
   */
  private String interval;
  /**
   * 包含 >=
   */
  private Float min;
  /**
   * 上限，不包含 <
   */
  private Float max;
  private List<String> compareResults = new ArrayList<>();

  /**
   * 是否符合
   */
  public boolean match(float score) {
    return score >= min && score < max;
  }

  /**
   * 加入结果集
   */
  public void add(String result) {
    compareResults.add(result);
  }


  public String getInterval() {
    return interval;
  }

  public void setInterval(String interval) {
    this.interval = interval;
  }

  public Float getMin() {
    return min;
  }

  public void setMin(Float min) {
    this.min = min;
  }

  public Float getMax() {
    return max;
  }

  public void setMax(Float max) {
    this.max = max;
  }

  public List<String> getCompareResults() {
    return compareResults;
  }

  public void setCompareResults(List<String> compareResults) {
    this.compareResults = compareResults;
  }
}
