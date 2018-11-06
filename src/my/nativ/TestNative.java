package my.nativ;

public class TestNative {

    static{
        System.loadLibrary("TestNative");
    }
    private native String addHeadString(String str);
    private native int sum(int a, int b);
    private native int[] improve(int[] data, int size);

    public String headString(String str){
        String head = this.addHeadString(str);
        // 后续还有别的操作,省略...
        return head;
    }

    public int numData(int a, int b){
        return this.sum(a, b);
    }

    public int[] improveData(int[] data){
        System.out.println("primitive data is:");
        for(int ii=0;ii<data.length;ii++){
            data[ii] = ii;
            System.out.print(data[ii]+" ");
        }
        System.out.println();

        int[] result = this.improve(data, data.length);
        System.out.println("improved data is:");
        for(int ii=0;ii<result.length;ii++){
            System.out.print(result[ii]+",");
        }
        return result;
    }
}
