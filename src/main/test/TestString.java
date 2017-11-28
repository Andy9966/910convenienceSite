/**
 * Created by ozc on 2017/11/8.
 */
public class TestString {


    public static void main(String[] args) {


        String no = getCurNo(93, 1, "百度");
        System.out.println(no);

    }
    public static String getCurNo(int curno, int length, String fillStr) {
        int temp = curno;
        StringBuffer sb = new StringBuffer(length);
        int count = 0;
        while (curno / 10 != 0) {
            curno = curno / 10;
            count++;
        }
        int size = length - count - 1;
        for (int i = 0; i < size; i++) {
            sb.append(fillStr);
        }
        sb.append(temp);
        return sb.toString();
    }
}
