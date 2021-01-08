package com.harryseld0n.aws.url;

import com.harryseld0n.aws.converter.BaseConverter;
import io.seruco.encoding.base62.Base62;


public class UrlShortenerService
{
	private UrlShortenerService() {
	}

	public static String encode(final Long id)
	{
		return new BaseConverter().encode(Math.abs(id));
	}

	public static String encode(final String url) {
		final byte[] encodedUrl = Base62.createInstance().encode(url.getBytes());
		return new String(encodedUrl);
	}

	private static String decode(final String shortUrl) {
		final byte[] decodeUrl = Base62.createInstance().decode(shortUrl.getBytes());
		return new String(decodeUrl);
	}
}
