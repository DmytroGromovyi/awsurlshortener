package com.harryseld0n.aws.dto;

public class UrlRequest
{
	private String url;

	public UrlRequest(String url)
	{
		this.url = url;
	}

	public UrlRequest()
	{
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}
}
