package jdk.jdk8.collection;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {
    public static void main(String[] args) {
        List<CameraLabel> cameraLabels = null;//new ArrayList<>();
        List<String> labels = cameraLabels.stream().map(CameraLabel::getLabelId).collect(Collectors.toList());
        System.out.println(labels.size());
    }
}
@Data
class CameraLabel implements Serializable {

    private String id;

    private String cameraId;

    private String labelId;

}
