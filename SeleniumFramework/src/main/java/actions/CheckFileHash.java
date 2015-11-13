package main.java.actions;
/**
 * The CheckFileHash class is used to perform the hash check operations on the 
 * given file
 *
 * @author Shilpashree_V
 * @version 1.0
 * @since February 2015
 * 
 */

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CheckFileHash {

	private static final Logger LOG = Logger.getLogger(CheckFileHash.class);
	private HashType typeOfHash = null;
	private String expectedFileHash = null;
	private File fileToCheck = null;

	/**
	 * The File to perform a Hash check upon
	 * 
	 * @param fileToCheck
	 * @throws FileNotFoundException
	 */
	public void fileToCheck(File fileToCheck) throws FileNotFoundException {
		if (!fileToCheck.exists())
			throw new FileNotFoundException(fileToCheck + " does not exist!");

		this.fileToCheck = fileToCheck;
	}

	/**
	 * Hash details used to perform the Hash check
	 * 
	 * @param hash
	 * @param hashType
	 */
	public void hashDetails(String hash, HashType hashType) {
		this.expectedFileHash = hash;
		this.typeOfHash = hashType;
	}

	/**
	 * Performs a expectedFileHash check on a File.
	 * 
	 * @return
	 * @throws IOException
	 */
	public boolean hasAValidHash() throws IOException {

		if (this.fileToCheck == null)
			throw new FileNotFoundException("File to check has not been set!");
		if (this.expectedFileHash == null || this.typeOfHash == null)
			throw new NullPointerException("Hash details have not been set!");

		String actualFileHash = "";
		boolean isHashValid = false;

		switch (this.typeOfHash) {
		case MD5:
			actualFileHash = DigestUtils.md5Hex(new FileInputStream(
					this.fileToCheck));
			if (this.expectedFileHash.equals(actualFileHash))
				isHashValid = true;
			break;
		case SHA1:
			actualFileHash = DigestUtils.shaHex(new FileInputStream(
					this.fileToCheck));
			if (this.expectedFileHash.equals(actualFileHash))
				isHashValid = true;
			break;
		}

		

		return isHashValid;
	}

}