/**
* The ObjectionableContentFoundException represents the details of the objectionable words found from review comments.  

* @author  Akki
* @version 1.0
* @since   2018-09-24 
*/

package com.akki.productreviews.common.exceptions;

import java.util.List;

public class ObjectionableContentFoundException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5772403030028508718L;
	/**
	 * 
	 */

	private static final String errorCode = "1000";
	private static final String errorMessage = "Objectionable Content Found";

	List<String> objectionableWordsList = null;

	public ObjectionableContentFoundException(String objectionableContentDetails) {
		super(errorCode, errorMessage, objectionableContentDetails);
	}
}
