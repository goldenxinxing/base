package jdk.jdk8.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {
    public static void main(String[] args) {
        Pattern VERSION_REGEX = Pattern
                .compile("^(\\d+)\\.(\\d+|x)\\.(\\d+|x)(?:[.|-]([^0-9]+)(\\d+)?)?$");
        Matcher matcher = VERSION_REGEX.matcher("2.0.0-SNAPSHOT".trim());
        if (!matcher.matches()) {
            System.out.println("不符合");
        }
        if (!matcher.matches()) {
            //throw new InvalidVersionException("Could not determine version based on '" + text + "': version format is Major.Minor.Patch.Qualifier (e.g. 1.0.5.RELEASE)");
        } else {
            Integer major = Integer.valueOf(matcher.group(1));
            String minor = matcher.group(2);
            String patch = matcher.group(3);
//            Qualifier qualifier = null;
            String qualifierId = matcher.group(4);
//            if (StringUtils.hasText(qualifierId)) {
//                qualifier = new Qualifier(qualifierId);
//                String o = matcher.group(5);
//                if (o != null) {
//                    qualifier.setVersion(Integer.valueOf(o));
//                }
//            }
//
//            if (!"x".equals(minor) && !"x".equals(patch)) {
//                return new Version(major, Integer.parseInt(minor), Integer.parseInt(patch), qualifier);
//            } else {
//                Integer minorInt = "x".equals(minor) ? null : Integer.parseInt(minor);
//                Version latest = this.findLatestVersion(major, minorInt, qualifier);
//                return latest == null ? new Version(major, "x".equals(minor) ? 999 : Integer.parseInt(minor), "x".equals(patch) ? 999 : Integer.parseInt(patch), qualifier) : new Version(major, latest.getMinor(), latest.getPatch(), latest.getQualifier());
//            }
            System.out.println(qualifierId);
        }
    }
}
