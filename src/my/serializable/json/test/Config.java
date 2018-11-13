package my.serializable.json.test;

import java.io.Serializable;
import java.util.List;

public class Config implements Serializable {
    /**
     * 默认文案
     */
    private String defaultText;

    /**
     * 版本控制
     */
    private List<Version> versionControl;

    public String getDefaultText() {
        return defaultText;
    }

    public void setDefaultText(String defaultText) {
        this.defaultText = defaultText;
    }

    public List<Version> getVersionControl() {
        return versionControl;
    }

    public void setVersionControl(List<Version> versionControl) {
        this.versionControl = versionControl;
    }

    /**
     * 根据版本获取其对应文案
     * @param version
     * @return 若无找到，返回默认值
     */
    public String obtainText(String version){
        for (Version v : versionControl) {
            if(v.versions.contains(version)){
                return v.text;
            }
        }
        return defaultText;
    }

    /**
     * 版本控制项
     */
    class Version{
        /**
         * 各版本号
         */
        private String versions;

        /**
         * 文案
         */
        private String text;

        public String getVersions() {
            return versions;
        }

        public void setVersions(String versions) {
            this.versions = versions;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
