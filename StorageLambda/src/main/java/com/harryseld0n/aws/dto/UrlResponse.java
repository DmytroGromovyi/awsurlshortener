package com.harryseld0n.aws.dto;

public class UrlResponse
{
	private String id;
	private String shortUrl;
	private String longUrl;

	public UrlResponse(String id, String shortUrl, String longUrl)
	{
		this.id = id;
		this.shortUrl = shortUrl;
		this.longUrl = longUrl;
	}

	public String getShortUrl()
	{
		return shortUrl;
	}

	public void setShortUrl(String shortUrl)
	{
		this.shortUrl = shortUrl;
	}

	public String getLongUrl()
	{
		return longUrl;
	}

	public void setLongUrl(String longUrl)
	{
		this.longUrl = longUrl;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}
}
