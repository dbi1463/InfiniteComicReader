/* StringUtils.java created on 2014/9/8
 *
 * This file is a part of the Infinite Comic Reader plug-in.
 */
package tw.comicsurfer.readers.infinite;

import java.util.Map;
import java.util.Set;

/**
 * This class provides some helper methods for the string manipulation.
 * 
 * @author Spirit
 * @version 1.0
 */
public class StringUtils {

	/**
	 * Get the substring that is surrounded by a pair of parentheses.
	 * 
	 * @param string the string to parse
	 * @return the parenthesized content
	 */
	public static String getParenthesizedContent(String string) {
		int beginIndex = string.indexOf("(") + 1;
		int endIndex = string.indexOf(")");
		return string.substring(beginIndex, endIndex);
	}

	/**
	 * Replace the placeholder in the string with the actual value. The placeholder
	 * is a key surrounded by a pair of braces, e.g., {key}. For example, a pattern
	 * like "Hello, {name}!" with a pair of parameters <name, John>, the replaced
	 * result will be "Hello, John!"
	 * 
	 * @param pattern the string that contains the placeholder
	 * @param parameters the actual values
	 * @return the replaced string
	 */
	public static String replacePlaceholders(String pattern, Map<String, String> parameters) {
		String result = pattern;
		Set<String> keys = parameters.keySet();
		for (String key : keys) {
			String placeholder = String.format("{%1s}", key);
			String value = parameters.get(key);
			result = result.replace(placeholder, value);
		}
		return result;
	}
}
