package com.harrySeld0n.aws.dto;

public class UrlDto
{
	private String shortUrl;

	public UrlDto()
	{
	}

	public UrlDto(String shortUrl)
	{
		this.shortUrl = shortUrl;
	}

	public String getShortUrl()
	{
		return shortUrl;
	}

	public void setShortUrl(String shortUrl)
	{
		this.shortUrl = shortUrl;
	}

}
