package org.topixoft.top_stack_overflow;

public class HtmlBodyUtils {
	
	public static String getBodyHtml(String body) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				"<html>\n" +
				"<head>\n" +
				"</head>\n" +
				"<body>\n");
		
		sb.append(
				body
				.replaceAll("#", "%23")
				.replaceAll("%", "%25")
				.replaceAll("\\\\", "%27")
				.replaceAll("\\?", "%3f")
				.replaceAll("\\n", "&#10;")
				.replaceAll("\\r", "&#13;")
				);
		
		sb.append("\n" +
				"</body>\n" +
				"</html>\n");
		return sb.toString();
	}

}
