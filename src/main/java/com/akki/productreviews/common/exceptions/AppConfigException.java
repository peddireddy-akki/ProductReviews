/**
* The AppConfigException represents the details of the application configurations related issues. 

* @author  Akki
* @version 1.0
* @since   2018-09-24 
*/

package com.akki.productreviews.common.exceptions;

public class AppConfigException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3672226799772989561L;
	private static final String errorCode = "1002";
	private static final String errorMessage = "Application Configuration Issues";

	public AppConfigException(String errorDetails) {
		super(errorCode, errorMessage, errorDetails);

	}

}
