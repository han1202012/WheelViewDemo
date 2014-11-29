package cn.org.octopus.wheelview.widget;

/**
 * 显示数字的 WheelAdapter
 */
public class NumericWheelAdapter implements WheelAdapter {

    /** 默认最小值 */
    public static final int DEFAULT_MAX_VALUE = 9;

    /** 默认最大值 */
    private static final int DEFAULT_MIN_VALUE = 0;

    /** 设置的最小值 */
    private int minValue;
    /** 设置的最大值 */
    private int maxValue;

    /** 格式化字符串, 用于格式化 货币, 科学计数, 十六进制 等格式 */
    private String format;

    /**
     * 默认的构造方法, 使用默认的最大最小值
     */
    public NumericWheelAdapter() {
        this(DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE);
    }

    /**
     * 构造方法
     * 
     * @param minValue
     *            最小值
     * @param maxValue
     *            最大值
     */
    public NumericWheelAdapter(int minValue, int maxValue) {
        this(minValue, maxValue, null);
    }

    /**
     * 构造方法
     * 
     * @param minValue
     *            最小值
     * @param maxValue
     *            最大值
     * @param format
     *            格式化字符串
     */
    public NumericWheelAdapter(int minValue, int maxValue, String format) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.format = format;
    }

    @Override
    public String getItem(int index) {
    	String result = "";
        if (index >= 0 && index < getItemsCount()) {
            int value = minValue + index;
            //如果 format 不为 null, 那么格式化字符串, 如果为 null, 直接返回数字
            if(format != null){
            	result = String.format(format, value);
            }else{
            	result = Integer.toString(value);
            }
            return result;
        }
        return null;
    }

    @Override
    public int getItemsCount() {
    	//返回数字总个数
        return maxValue - minValue + 1;
    }

    @Override
    public int getMaximumLength() {
    	//获取 最大值 和 最小值 中的 较大的数字
        int max = Math.max(Math.abs(maxValue), Math.abs(minValue));
        //获取这个数字 的 字符串形式的 字符串长度
        int maxLen = Integer.toString(max).length();
        if (minValue < 0) {
            maxLen++;
        }
        return maxLen;
    }
}
