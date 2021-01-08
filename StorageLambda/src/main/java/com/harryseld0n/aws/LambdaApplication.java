package com.harryseld0n.aws;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.harryseld0n.aws.dto.UrlRequest;
import com.harryseld0n.aws.dto.UrlResponse;
import com.harryseld0n.aws.url.UrlShortenerService;


public class LambdaApplication implements RequestHandler<UrlRequest, UrlResponse>
{
	DynamoDB dynamoDb;
	private String DYNAMODB_TABLE_NAME = "urls";
	private Regions REGION = Regions.EU_WEST_2;

	@Override
	public UrlResponse handleRequest(UrlRequest urlRequest, Context context)
	{
		context.getLogger().log("Incoming request is: " + urlRequest.getUrl());
		final String url = urlRequest.getUrl();
		final String shortUrl = UrlShortenerService.encode((long) url.hashCode());
		return handleUrl(new UrlResponse(String.valueOf(url.hashCode()), shortUrl, url));
	}

	private UrlResponse handleUrl(final UrlResponse urlObject)
	{
		this.initDynamoDbClient();
		persistData(urlObject);
		return urlObject;
	}

	private PutItemOutcome persistData(final UrlResponse urlObject)
			throws ConditionalCheckFailedException
	{
		return this.dynamoDb.getTable(DYNAMODB_TABLE_NAME)
				.putItem(
						new PutItemSpec().withItem(new Item()
								.withString("id", urlObject.getId())
								.withString("shortUrl", urlObject.getShortUrl())
								.withString("longUrl", urlObject.getLongUrl())));
	}

	private void initDynamoDbClient() {
		AmazonDynamoDBClient client = new AmazonDynamoDBClient();
		client.setRegion(Region.getRegion(REGION));
		this.dynamoDb = new DynamoDB(client);
	}
}
