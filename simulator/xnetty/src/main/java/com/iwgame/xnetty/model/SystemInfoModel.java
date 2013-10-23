package com.iwgame.xnetty.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class SystemInfoModel implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 4786339868532605628L;

	private final String ipAddress;
	private final String macAddress;
	private final String hostName;

	public SystemInfoModel() {
		this.hostName = getHostNameInfo();
		this.ipAddress = getIpAddressInfo();
		this.macAddress = getMACAddressInfo();
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public String getHostName() {
		return hostName;
	}

	private String getIpAddressInfo() {
		try {
			InetAddress address = InetAddress.getLocalHost();
			return address.getHostAddress();
		} catch (UnknownHostException e) {
		} catch (Throwable t) {
		}
		return "0.0.0.0";
	}

	private String getHostNameInfo() {
		String hostname = "";
		String osname = System.getProperty("os.name");
		if (osname != null) {
			if (osname.toLowerCase().startsWith("linux")) {
				try {
					ProcessBuilder pb = new ProcessBuilder("hostname");
					Process p = pb.start();
					BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
					String line;
					while ((line = br.readLine()) != null) {
						hostname = line.toLowerCase().trim();
						break;
					}
					br.close();
				} catch (IOException ex) {
				}
			} else if (osname.toLowerCase().startsWith("mac")) {
				try {
					ProcessBuilder pb = new ProcessBuilder("hostname");
					Process p = pb.start();
					BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
					String line;
					while ((line = br.readLine()) != null) {
						hostname = line.toLowerCase().trim();
						break;
					}
					br.close();
				} catch (IOException ex) {
				}

			}
		}
		return hostname;

	}

	private String getMACAddressInfo() {
		String address = "";
		String os = System.getProperty("os.name");
		if (os != null) {
			if (os.startsWith("Windows")) {
				try {
					ProcessBuilder pb = new ProcessBuilder("ipconfig", "/all");
					Process p = pb.start();
					BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
					String line;
					while ((line = br.readLine()) != null) {
						if (line.indexOf("Physical Address") != -1) {
							int index = line.indexOf(":");
							address = line.substring(index + 1);
							break;
						}
					}
					br.close();
					return address.trim();
				} catch (IOException e) {

				}
			} else if (os.startsWith("Linux")) {
				try {
					ProcessBuilder pb = new ProcessBuilder("ifconfig");
					Process p = pb.start();
					BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
					String line;
					while ((line = br.readLine()) != null) {
						int index = line.indexOf("hwaddr");
						if (index != -1) {
							address = line.substring(index + "hwaddr".length() + 1).trim();
							break;
						}
					}
					br.close();
					return address.trim();
				} catch (IOException ex) {
				}
			} else if (os.startsWith("Mac")) {
				try {
					ProcessBuilder pb = new ProcessBuilder("ifconfig");
					Process p = pb.start();
					BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
					String line;
					while ((line = br.readLine()) != null) {
						int index = line.indexOf("ether");
						if (index != -1) {
							address = line.substring(index + "ether".length() + 1).trim();
							break;
						}
					}
					br.close();
					return address.trim();
				} catch (IOException ex) {
				}

			}
		}
		return address;
	}

}
