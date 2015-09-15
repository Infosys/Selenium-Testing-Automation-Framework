package com.infosys.toolsgroup.selautofwk.license;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import util.MainTestNG;

public class LicenseValidator {

	Inet4Address inet = null;
	static boolean bValid = false;

	public boolean isValidLicense(String[] productkey) {

		String pKey = productkey[0];

		try {
			String LoginUser = System.getProperty("user.name").toString();
			String hostName = inet.getLocalHost().toString();
			StringTokenizer st = new StringTokenizer(hostName, "/");
			hostName = st.nextToken();
			String encrKey = pKey + LoginUser + hostName;

			String compareProductKey = readCert();

			byte[] textCrypt = compareProductKey.getBytes();
			byte cKey = 24;
			char[] result = new char[textCrypt.length];
			byte[] cKey1 = new byte[textCrypt.length];
			for (int i = 0; i < textCrypt.length; i++) {
				cKey1[i] = cKey;
				result[i] = (char) (textCrypt[i] ^ cKey1[i]);
			}
			String s = new String(result);

			if (encrKey.equalsIgnoreCase(s)) {
				bValid = true;
			}
		} catch (UnknownHostException e) {
			MainTestNG.LOGGER.severe(e.getMessage());
		}

		return bValid;
	}

	public String readCert() {
		String sLine;
		String fileextn = ".cert";
		String compareKey = null;
		try {

			String path = ".";
			String files = "";
			List alfiles = new ArrayList();
			File folder = new File(path);

			File[] listOfFiles = folder.listFiles();

			for (int i = 0; i < listOfFiles.length; i++) {

				if (listOfFiles[i].isFile()) {
					files = listOfFiles[i].getName();
					if (files.endsWith(fileextn)) {
						alfiles.add(files);
					}
				}
			}

			if (!(alfiles.size() > 1)) {

				BufferedReader br = new BufferedReader(new FileReader(alfiles
						.get(0).toString()));

				while ((sLine = br.readLine()) != null) {
					compareKey = sLine;

				}
			} else if (alfiles.size() > 1) {
				JOptionPane
						.showMessageDialog(null,
								"There cannot be two certificate files in the same folder");
			} else if (alfiles.size() < 1) {
				JOptionPane
						.showMessageDialog(null,
								"The certificate file is not found in the tool executable folder.");
			}

		} catch (FileNotFoundException e) {

			JOptionPane
					.showMessageDialog(null,
							"The certificate file is not found in the tool executable folder.");

		} catch (IOException e) {

			MainTestNG.LOGGER.severe("Exception in license generation");
		} catch (IndexOutOfBoundsException e) {

			JOptionPane
					.showMessageDialog(null,
							"The certificate file is not found in the tool executable folder.");
		}

		return compareKey;

	}

}
