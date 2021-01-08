package com.harrySeld0n.aws;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.harrySeld0n.aws.dto.UrlDto;
import com.harrySeld0n.aws.dto.UrlResponse;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;


public class LambdaApplication implements RequestHandler<UrlDto, String>
{
	DynamoDB dynamoDb;
	private String DYNAMODB_TABLE_NAME = "urls";
	private Regions REGION = Regions.EU_WEST_2;

	@Override
	public String handleRequest(UrlDto dto, Context context)
	{
		return queryUrl(dto.getShortUrl()).getUrl();
	}

	private UrlResponse queryUrl(final String shortUrl)
	{
		initDynamoDbClient();
		Table table = dynamoDb.getTable(DYNAMODB_TABLE_NAME);

		QuerySpec querySpec = new QuerySpec()
				.withKeyConditionExpression("shortUrl = :v_shortUrl")
				.withValueMap(new ValueMap()
						.withString(":v_shortUrl", shortUrl));

		ItemCollection<QueryOutcome> result = table.query(querySpec);

		Iterator<Item> iterator = result.iterator();
		Item item = null;
		while (iterator.hasNext())
		{
			item = iterator.next();
		}
		if (Objects.nonNull(item))
		{
			return buildDto(item);
		}
		throw new NoSuchElementException("item does not exist!");
	}

	private UrlResponse buildDto(Item item)
	{
		UrlResponse urlDto = new UrlResponse();
		urlDto.setUrl(item.getString("longUrl"));
		return urlDto;
	}

	private void initDynamoDbClient()
	{
		AmazonDynamoDBClient client = new AmazonDynamoDBClient();
		client.setRegion(Region.getRegion(REGION));
		this.dynamoDb = new DynamoDB(client);
	}
}
