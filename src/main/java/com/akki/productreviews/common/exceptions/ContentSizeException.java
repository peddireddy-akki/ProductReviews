/**
* The ApplicationException represents the details of the review comments validation failures.  

* @author  Akki
* @version 1.0
* @since   2018-09-24 
*/

package com.akki.productreviews.common.exceptions;

public class ContentSizeException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4171506005774975130L;
	private static final String errorCode = "1001";
	private static final String errorMessage = "Invalid content size";

	public ContentSizeException(String errorDetails) {
		super(errorCode, errorMessage, errorDetails);
	}
}
