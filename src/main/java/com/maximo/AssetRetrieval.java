package com.maximo;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import psdi.mbo.MboRemote;
import psdi.mbo.MboSetRemote;
import psdi.security.UserInfo;
import psdi.server.MXServer;
import psdi.util.MXException;

public class AssetRetrieval {

	private static final String USERNAME = "";
	private static final String PASSWORD = "";

	public static void main(String[] args) {

		try {
			InitialContext ctx = new InitialContext();
			MXServer mxServer = (MXServer) ctx.lookup("URL");

			// Authenticate user
			UserInfo userInfo = mxServer.authenticate(USERNAME, PASSWORD);
			if (userInfo == null) {
				System.out.println("Authentication failed");
				return;
			}

			MboSetRemote assetSet = mxServer.getMboSet("ASSET", userInfo);
			
			for (MboRemote asset : assetSet) {
				System.out.println("Asset Number: " + asset.getString("ASSETNUM"));
				System.out.println("Description: " + asset.getString("DESCRIPTION"));
				System.out.println("Location: " + asset.getString("LOCATION"));
				System.out.println("-------------------------");
			}

			assetSet.close();
		} catch (NamingException e) {
			System.out.println("Error connecting to Maximo: " + e.getMessage());
		} catch (MXException | RemoteException e) {
			System.out.println("Error retrieving asset information: " + e.getMessage());
		}
	}

}
