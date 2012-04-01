package ar.com.seminario.corralon.services.impl;

import org.springframework.stereotype.Service;

import ar.com.seminario.corralon.services.GoogleAPIService;

import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessTokenRequest.GoogleAuthorizationCodeGrant;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;


@Service("googleAPIService")
public class GoogleAPIServiceImpl implements GoogleAPIService {

	private final String CLIENT_ID = "209353058543.apps.googleusercontent.com";
	private final String CLIENT_SECRET = "6tlv6CIjb_ZfYQrGAeQ8JCxJ";
	private final String CALLBACK_URL = "https://corralonpresupuestos.appspot.com/endLoginGoogleOauth.htm";
	
	private static final HttpTransport TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();

	@Override
	public String getTokens(String authorizationCode) {
		String responseMsg = "";
		String accessToken = "";
		try {
			GoogleAuthorizationCodeGrant authRequest = new GoogleAuthorizationCodeGrant(
					TRANSPORT, JSON_FACTORY, CLIENT_ID, CLIENT_SECRET,
					authorizationCode, CALLBACK_URL);
			authRequest.useBasicAuthorization = false;
			AccessTokenResponse authResponse = authRequest.execute();
			accessToken = authResponse.accessToken;
			return accessToken;
		} catch (Exception ex) {
			System.out.println("Exception cought:\n" + ex.toString());
			return ex.getMessage() + " // " + ex.toString()
					+ " // responseMsg " + responseMsg + " // code " + authorizationCode + " // access token" + accessToken;
		} finally {

		}

	}

}
