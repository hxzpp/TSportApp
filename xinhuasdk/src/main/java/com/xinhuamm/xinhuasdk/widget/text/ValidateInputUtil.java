package com.xinhuamm.xinhuasdk.widget.text;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 对输入文件的验证
 *
 */
public class ValidateInputUtil {

	/**
	 * 对邮箱格式的验证
	 *
	 * @return
	 */
	public static boolean checkEmail(String email) {
		String strPattern = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * 对手机号码的验证
	 *
	 * @param mobile
	 * @return
	 */
	public static boolean checkMobile(String mobile) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[6,7,8]))\\d{8}$");
		Matcher m = p.matcher(mobile);
		boolean b = m.matches();
		return b;
	}

	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(14[0-9])|(15[^4,\\D])|(18[0-9])|(17[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);

		return m.matches();
	}

	/**
	 * 是否为国际手机号
	 * @param mobile
	 * @return
	 */
	public static boolean isInternaltionMobile(String mobile) {
		return !(mobile == null || mobile.length() < 5 || mobile.length() > 16);
	}

	public static boolean checkUserName(String userName) {
		String userNamePattern = "^[\u4E00-\u9FA5A-Za-z0-9_]+$";
		Pattern pattern = Pattern.compile(userNamePattern);

		Matcher matcher = pattern.matcher(userName);
		return matcher.find();
	}

	public static boolean checkInput(String userName) {
		String userNamePattern = "^[\u4E00-\u9FA5A-Za-z0-9_]+$";
		Pattern pattern = Pattern.compile(userNamePattern);

		Matcher matcher = pattern.matcher(userName);
		return matcher.find();
	}

	public static boolean StringFilter(String str) {
		// 只允许字母和数字
		String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}


	public static boolean isBiaoQingCheck(String str) {
		String reg = "^([a-z]|[A-Z]|[0-9]|[\u2E80-\u9FFF]){3,}|@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?|[wap.]{4}|[www.]{4}|[blog.]{5}|[bbs.]{4}|[.com]{4}|[.cn]{3}|[.net]{4}|[.org]{4}|[http://]{7}|[ftp://]{6}$";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(str.toString());
		return matcher.matches();
	}

	/**
	 * 密码限制为英文和数字
	 * @param str
	 * @return
     */
	public static  boolean checkPwd(String str){
		Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
		Matcher m = pattern.matcher(str);
		if( !m.matches() ){ //匹配不到,說明輸入的不符合條件
			return false;
		}
		return true;
	}
}
