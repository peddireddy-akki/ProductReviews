/**
* The ObjectionableKeyWord represents the details of the Objectionable keyword.
* @author  Akki
* @version 1.0
* @since   2018-09-24 
*/
package com.akki.productreviews.model;

public class ObjectionableKeyWord {

	private String keyWord;
	private String regExpression;

	public ObjectionableKeyWord(String ObjectionableKeyWord) {
		this.keyWord = ObjectionableKeyWord;
		if (keyWord != null && !keyWord.isEmpty()) {
			StringBuffer regExpressionBuilder = new StringBuffer();

			for (char ch : ObjectionableKeyWord.toCharArray()) {
				regExpressionBuilder.append("[" + ch + "]{1,}");
			}
			regExpression = regExpressionBuilder.toString();
		}
	}

	public String getKeyWord() {
		return keyWord;
	}

	public String getRegExpression() {
		return regExpression;
	}

	public String toString() {
		return "Key Word: " + keyWord + ",Reggular Expression: " + regExpression;
	}

}
